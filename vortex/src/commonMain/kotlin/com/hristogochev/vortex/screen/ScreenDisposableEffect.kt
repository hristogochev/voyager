package com.hristogochev.vortex.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.hristogochev.vortex.navigator.LocalScreenKey
import com.hristogochev.vortex.util.currentOrThrow
import com.hristogochev.vortex.util.randomUuid


/**
 * Creates a disposable effect for the current screen.
 *
 * The body executes when the screen is first rendered or any of the keys change.
 *
 * The `onDispose` body gets executed when any of the keys change or the screen is destroyed.
 */
@Composable
public fun ScreenDisposableEffect(
    key1: Any? = null,
    effect: ScreenDisposableEffectScope.() -> ScreenDisposableEffectResult,
) {
    val screenKey = LocalScreenKey.currentOrThrow

    val effectKey = rememberSaveable { randomUuid() }

    val set by remember(key1) {
        derivedStateOf {
            setOfNotNull(key1)
        }
    }

    LaunchedEffect(set) {
        ScreenDisposableEffectStore.store(
            screenKey = screenKey,
            effectKey = effectKey,
            effect = effect,
            keys = set,
            key1Changed = {
                it != set
            }
        )
    }
}

/**
 * Creates a disposable effect for the current screen.
 *
 * The body executes when the screen is first rendered or any of the keys change.
 *
 * The `onDispose` body gets executed when any of the keys change or the screen is destroyed.
 */
@Composable
public fun ScreenDisposableEffect(
    vararg keys: Any?,
    effect: ScreenDisposableEffectScope.() -> ScreenDisposableEffectResult,
) {
    val screenKey = LocalScreenKey.currentOrThrow

    val effectKey = rememberSaveable { randomUuid() }

    val set by remember(keys) {
        derivedStateOf {
            keys.filterNotNull().toSet()
        }
    }

    LaunchedEffect(set) {
        ScreenDisposableEffectStore.store(
            screenKey = screenKey,
            effectKey = effectKey,
            effect = effect,
            keys = set,
            key1Changed = {
                it != set
            }
        )
    }
}

public interface ScreenDisposableEffectResult {
    public fun dispose()
}

public class ScreenDisposableEffectScope {
    public inline fun onDispose(
        crossinline onDisposeEffect: () -> Unit,
    ): ScreenDisposableEffectResult {
        return object : ScreenDisposableEffectResult {
            override fun dispose() {
                onDisposeEffect()
            }
        }
    }
}
