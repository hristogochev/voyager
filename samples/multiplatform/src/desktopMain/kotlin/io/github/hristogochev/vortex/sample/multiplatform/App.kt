package io.github.hristogochev.vortex.sample.multiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.hristogochev.vortex.sample.multiplatform.SampleApplication

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            SampleApplication()
        }
    }
}
