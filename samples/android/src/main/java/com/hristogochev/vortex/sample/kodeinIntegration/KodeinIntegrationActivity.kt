package com.hristogochev.vortex.sample.kodeinIntegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hristogochev.vortex.navigator.Navigator

class KodeinIntegrationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(KodeinScreen())
        }
    }
}
