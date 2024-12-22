package com.hristogochev.vortex.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.util.multiplatformName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * Our implementation of a viewmodel, bound entirely to the lifecycle of a [Screen].
 */
public abstract class ScreenModel {
    protected val screenModelScope: CoroutineScope =
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    public open fun onDispose() {
        screenModelScope.cancel()
    }
}

/**
 * After this is called once in the screen, it does not get recreated until the screen is disposed.
 */
@Composable
public inline fun <reified T : ScreenModel> Screen.rememberScreenModel(
    tag: String? = null,
    crossinline factory: @DisallowComposableCalls () -> T,
): T {
    return rememberScreenModel(this.key, tag, factory)
}

/**
 * After this is called once in the navigator, it does not get recreated until the navigator is disposed.
 */
@Composable
public inline fun <reified T : ScreenModel> Navigator.rememberNavigatorScreenModel(
    tag: String? = null,
    crossinline factory: @DisallowComposableCalls () -> T,
): T {
    return rememberScreenModel(this.key, tag, factory)
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
        ScreenModelStore.getOrPut(screenModelKey, factory)
    }
}
