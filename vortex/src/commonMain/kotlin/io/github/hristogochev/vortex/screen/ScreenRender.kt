package io.github.hristogochev.vortex.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.navigator.LocalNavigatorStateHolder
import io.github.hristogochev.vortex.navigator.LocalScreenStateKey
import io.github.hristogochev.vortex.util.currentOrThrow


/**
 * Renders the current screen, saving its state even if its no longer displayed.
 *
 * This function should only be externally used  if you want to create a totally custom transition, e.g. iOS swipe.
 */
@Composable
public fun <T : Screen> T.render(content: @Composable (T) -> Unit = { it.Content() }) {

    val stateHolder = LocalNavigatorStateHolder.currentOrThrow
    val navigator = LocalNavigator.currentOrThrow

    val screenStateKey = remember(key, navigator.key) {
        "${key}:${navigator.key}"
    }

    navigator.associateScreenStateKey(screenStateKey)

    stateHolder.SaveableStateProvider(screenStateKey) {
        CompositionLocalProvider(
            LocalScreenStateKey provides screenStateKey
        ) {
            content(this)
        }
    }
}
