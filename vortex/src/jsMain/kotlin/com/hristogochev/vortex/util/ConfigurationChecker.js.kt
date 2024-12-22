package com.hristogochev.vortex.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
internal class JSConfigurationChecker : ConfigurationChecker {
    override fun isChangingConfigurations(): Boolean {
        return false
    }
}

@Composable
@NonRestartableComposable
internal actual fun rememberConfigurationChecker(): ConfigurationChecker {
    return remember { JSConfigurationChecker() }
}