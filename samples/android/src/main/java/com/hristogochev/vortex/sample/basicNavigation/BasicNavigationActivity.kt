package com.hristogochev.vortex.sample.basicNavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hristogochev.vortex.navigator.Navigator

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
