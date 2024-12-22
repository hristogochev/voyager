package com.hristogochev.vortex.sample.screenTransition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hristogochev.vortex.navigator.CurrentScreen
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.transitions.slideTransition
import kotlin.random.Random

class ScreenTransitionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        Navigator(
            screen = NoCustomAnimationSampleScreen(0),
        ) { navigator ->
            Box(modifier = Modifier.fillMaxSize()) {
                CurrentScreen(navigator = navigator, defaultTransition = slideTransition(navigator))

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Button(
                            onClick = { navigator.push(FadeAnimationSampleScreen(navigator.items.size)) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Fade")
                        }

                        Button(
                            onClick = {
                                navigator.push(
                                    SlideInVerticallyAnimationSampleScreen(
                                        navigator.items.size
                                    )
                                )
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "SlideInVertically")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Button(
                            onClick = { navigator.push(NoCustomAnimationSampleScreen(navigator.items.size)) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Default")
                        }

                        Button(
                            onClick = { navigator.pop() },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Pop")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Button(
                            onClick = { navigator.replace(NoCustomAnimationSampleScreen(Random.nextInt())) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Replace")
                        }

                        Button(
                            onClick = { navigator.replaceAll(NoCustomAnimationSampleScreen(Random.nextInt())) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "ReplaceAll")
                        }
                    }
                }
            }
        }
    }
}
