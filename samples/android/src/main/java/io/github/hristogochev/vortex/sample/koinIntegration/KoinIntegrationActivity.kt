package io.github.hristogochev.vortex.sample.koinIntegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.hristogochev.vortex.navigator.Navigator

class KoinIntegrationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(io.github.hristogochev.vortex.sample.koinIntegration.KoinScreen())
        }
    }
}
