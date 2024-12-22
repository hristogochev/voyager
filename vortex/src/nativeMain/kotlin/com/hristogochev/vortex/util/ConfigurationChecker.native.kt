package com.hristogochev.vortex.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
private class NativeConfigurationChecker : ConfigurationChecker {
    override fun isChangingConfigurations(): Boolean {
        return false
    }
}

@NonRestartableComposable
@Composable
internal actual fun rememberConfigurationChecker(): ConfigurationChecker {
    return remember { NativeConfigurationChecker() }
}