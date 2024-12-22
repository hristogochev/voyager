package com.hristogochev.vortex.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember
import com.hristogochev.vortex.navigator.LocalScreenStateKey
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.util.multiplatformName
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * Get the screen model scope.
 *
 * Currently implemented using a screen model dependency to allow screen models to be interfaces instead of abstract classes.
 */
public val ScreenModel.screenModelScope: CoroutineScope
    get() = ScreenModelStore.getOrPutScreenModelDependency(
        screenModel = this,
        name = "ScreenModelCoroutineScope",
        factory = { key ->
            CoroutineScope(
                SupervisorJob() + Dispatchers.Main.immediate + CoroutineName(
                    key
                )
            )
        },
        onDispose = { scope -> scope.cancel() }
    )


/**
 * Our template for a viewmodel, bound entirely to the lifecycle of a [Screen].
 */
public interface ScreenModel {
    public fun onDispose() {}
}

/**
 * After this is called once in the screen, it does not get recreated until the screen is disposed.
 */
@Composable
public inline fun <reified T : ScreenModel> rememberScreenModel(
    tag: String? = null,
    crossinline factory: @DisallowComposableCalls () -> T,
): T {
    val screenStateKey =
        LocalScreenStateKey.current ?: "rememberScreenModel called outside of a screen scope"
    return rememberScreenModel(screenStateKey, tag, factory)
}

/**
 * After this is called once in the navigator, it does not get recreated until the navigator is disposed.
 */
@Composable
public inline fun <reified T : ScreenModel> Navigator.rememberNavigatorScreenModel(
    tag: String? = null,
    crossinline factory: @DisallowComposableCalls () -> T,
): T {
    return rememberScreenModel(key, tag, factory)
}

/**
 * Base function for persisting screen models based on a key
 */
@Composable
public inline fun <reified T : ScreenModel> rememberScreenModel(
    holderKey: String,
    tag: String? = null,
    crossinline factory: @DisallowComposableCalls () -> T,
): T {
    val screenModelKey = "$holderKey:${T::class.multiplatformName}:${tag ?: "default"}"

    return remember(screenModelKey) {
        ScreenModelStore.getOrPut(screenModelKey) {
            factory()
        }
    }
}
