package com.hristogochev.vortex.navigator

import androidx.compose.runtime.Composable
import com.hristogochev.vortex.util.BackHandler


/**
 * The default back handler, only works on Android.
 *
 * Pops the current navigator on back press.
 * If the current navigator is at its root, pop it's parent.
 */
@Composable
public fun NavigatorBackHandler(
    navigator: Navigator,
) {
    BackHandler(
        enabled = navigator.canPop || navigator.parent?.canPop ?: false,
        onBack = {
            val popped = navigator.pop()
            if (!popped) {
                navigator.parent?.pop()
            }
        }
    )
}
