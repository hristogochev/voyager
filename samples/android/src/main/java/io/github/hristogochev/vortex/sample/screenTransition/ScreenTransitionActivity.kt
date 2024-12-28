package io.github.hristogochev.vortex.sample.screenTransition

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
import io.github.hristogochev.vortex.navigator.Navigator
import io.github.hristogochev.vortex.screen.CurrentScreen
import io.github.hristogochev.vortex.transitions.SlideTransition
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
            screen = io.github.hristogochev.vortex.sample.screenTransition.NoCustomAnimationSampleScreen(0),
        ) { navigator ->
            Box(modifier = Modifier.fillMaxSize()) {
                CurrentScreen(
                    navigator = navigator,
                    defaultOnScreenAppearTransition = SlideTransition.Horizontal.Appear,
                    defaultOnScreenDisappearTransition = SlideTransition.Horizontal.Disappear,
                )

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
                            onClick = { navigator.push(
                                io.github.hristogochev.vortex.sample.screenTransition.FadeAnimationSampleScreen(
                                    navigator.items.size
                                )
                            ) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Fade")
                        }

                        Button(
                            onClick = {
                                navigator.push(
                                    io.github.hristogochev.vortex.sample.screenTransition.SlideInVerticallyAnimationSampleScreen(
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
                            onClick = { navigator.push(
                                io.github.hristogochev.vortex.sample.screenTransition.NoCustomAnimationSampleScreen(
                                    navigator.items.size
                                )
                            ) },
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
                            onClick = { navigator.replace(
                                io.github.hristogochev.vortex.sample.screenTransition.NoCustomAnimationSampleScreen(
                                    Random.nextInt()
                                )
                            ) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Replace")
                        }

                        Button(
                            onClick = { navigator.replaceAll(
                                io.github.hristogochev.vortex.sample.screenTransition.NoCustomAnimationSampleScreen(
                                    Random.nextInt()
                                )
                            ) },
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
