package com.hristogochev.vortex.util

import androidx.compose.runtime.Composable

@Composable
public expect fun BackHandler(enabled: Boolean, onBack: () -> Unit)
