package com.hristogochev.vortex.sample.koinIntegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hristogochev.vortex.navigator.Navigator

class KoinIntegrationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(KoinScreen())
        }
    }
}