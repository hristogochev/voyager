package io.github.hristogochev.vortex.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import io.github.hristogochev.vortex.navigator.LocalScreenStateKey
import io.github.hristogochev.vortex.util.currentOrThrow
import io.github.hristogochev.vortex.util.randomUuid


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
    val screenStateKey = LocalScreenStateKey.currentOrThrow

    val effectKey = rememberSaveable { randomUuid() }

    val set by remember(key1) {
        derivedStateOf {
            setOfNotNull(key1)
        }
    }

    LaunchedEffect(set) {
        ScreenDisposableEffectStore.store(
            screenStateKey = screenStateKey,
            effectKey = effectKey,
            effect = effect,
            keys = set,
            keysChanged = {
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
    val screenStateKey = LocalScreenStateKey.currentOrThrow

    val effectKey = rememberSaveable { randomUuid() }

    val set by remember(keys) {
        derivedStateOf {
            keys.filterNotNull().toSet()
        }
    }

    LaunchedEffect(set) {
        ScreenDisposableEffectStore.store(
            screenStateKey = screenStateKey,
            effectKey = effectKey,
            effect = effect,
            keys = set,
            keysChanged = {
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
