package io.github.hristogochev.vortex.sample.parcelableScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.hristogochev.vortex.navigator.Navigator

class ParcelableActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(
                screen = io.github.hristogochev.vortex.sample.parcelableScreen.SampleParcelableScreen(
                    parcelable = io.github.hristogochev.vortex.sample.parcelableScreen.ParcelableContent(
                        index = 0
                    )
                ),
            )
        }
    }
}
