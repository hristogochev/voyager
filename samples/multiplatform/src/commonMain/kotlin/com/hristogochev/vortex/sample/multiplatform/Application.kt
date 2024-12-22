package com.hristogochev.vortex.sample.multiplatform

import androidx.compose.runtime.Composable
import com.hristogochev.vortex.navigator.Navigator

@Composable
fun SampleApplication() {
    Navigator(
        screen = BasicNavigationScreen(index = 0),
    )
}
