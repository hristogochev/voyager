package io.github.hristogochev.vortex.sample.screenModel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.hristogochev.vortex.navigator.Navigator

class ScreenModelActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(ListScreen())
        }
    }
}
