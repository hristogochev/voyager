package com.hristogochev.vortex.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember

@Composable
@NonRestartableComposable
public fun DisposableEffectUnlessChangingConfiguration(
    key1: Any?,
    effect: DisposableEffectScope.() -> DisposableEffectResult
) {
    val configurationChecker = rememberConfigurationChecker()
    remember(configurationChecker, key1) { DisposableEffectIgnoringConfigurationImpl(configurationChecker, effect) }
}

@Composable
@NonRestartableComposable
public fun DisposableEffectUnlessChangingConfiguration(
    key1: Any?,
    key2: Any?,
    effect: DisposableEffectScope.() -> DisposableEffectResult
) {
    val configurationChecker = rememberConfigurationChecker()
    remember(configurationChecker, key1, key2) {
        DisposableEffectIgnoringConfigurationImpl(configurationChecker, effect)
    }
}

@Composable
@NonRestartableComposable
public fun DisposableEffectUnlessChangingConfiguration(
    key1: Any?,
    key2: Any?,
    key3: Any?,
    effect: DisposableEffectScope.() -> DisposableEffectResult
) {
    val configurationChecker = rememberConfigurationChecker()
    remember(configurationChecker, key1, key2, key3) {
        DisposableEffectIgnoringConfigurationImpl(configurationChecker, effect)
    }
}

@Composable
@NonRestartableComposable
@Suppress("ArrayReturn")
public fun DisposableEffectUnlessChangingConfiguration(
    vararg keys: Any?,
    effect: DisposableEffectScope.() -> DisposableEffectResult
) {
    val configurationChecker = rememberConfigurationChecker()
    remember(configurationChecker, *keys) { DisposableEffectIgnoringConfigurationImpl(configurationChecker, effect) }
}

private val InternalDisposableEffectScope = DisposableEffectScope()

private class DisposableEffectIgnoringConfigurationImpl(
    private val configurationChecker: ConfigurationChecker,
    private val effect: DisposableEffectScope.() -> DisposableEffectResult
) : RememberObserver {
    private var onDispose: DisposableEffectResult? = null

    override fun onRemembered() {
        onDispose = InternalDisposableEffectScope.effect()
    }

    override fun onForgotten() {
        onDispose?.takeUnless { configurationChecker.isChangingConfigurations() }?.dispose()
        onDispose = null
    }

    override fun onAbandoned() {
        // Nothing to do as [onRemembered] was not called.
    }
}
