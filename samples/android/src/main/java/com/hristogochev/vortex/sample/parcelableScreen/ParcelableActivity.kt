package com.hristogochev.vortex.sample.parcelableScreen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hristogochev.vortex.navigator.Navigator

class ParcelableActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(
                screen = SampleParcelableScreen(parcelable = ParcelableContent(index = 0)),
            )
        }
    }
}
