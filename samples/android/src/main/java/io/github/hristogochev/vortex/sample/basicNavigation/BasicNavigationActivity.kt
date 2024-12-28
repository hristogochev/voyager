package io.github.hristogochev.vortex.sample.basicNavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.hristogochev.vortex.navigator.Navigator

class BasicNavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(
                screen = BasicNavigationScreen(index = 0),
            )
        }
    }
}
