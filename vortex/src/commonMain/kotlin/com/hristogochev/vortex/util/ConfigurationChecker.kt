package com.hristogochev.vortex.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable

@Composable
@NonRestartableComposable
internal expect fun rememberConfigurationChecker(): ConfigurationChecker

@Stable
internal interface ConfigurationChecker {
    fun isChangingConfigurations(): Boolean
}
