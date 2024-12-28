package io.github.hristogochev.vortex.util

import androidx.compose.runtime.Composable

@Composable
public actual fun BackHandler(enabled: Boolean, onBack: () -> Unit): Unit =
    androidx.activity.compose.BackHandler(enabled, onBack)