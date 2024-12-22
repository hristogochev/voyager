package com.hristogochev.vortex.sample.multiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            SampleApplication()
        }
    }
}
