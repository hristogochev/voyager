package io.github.hristogochev.vortex.sample.multiplatform.transitions

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.navigator.Navigator
import io.github.hristogochev.vortex.screen.CurrentScreen
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.screen.ScreenTransition
import io.github.hristogochev.vortex.transitions.FadeTransition
import io.github.hristogochev.vortex.transitions.SlideTransition
import io.github.hristogochev.vortex.util.currentOrThrow

@Composable
fun Transitions() {
    Navigator(HomeScreen) { navigator ->
        CurrentScreen(
            navigator = navigator,
            defaultOnScreenAppearTransition = FadeTransition,
            defaultOnScreenDisappearTransition = FadeTransition
        )
    }
}

data object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Button(
            onClick = {
                navigator.push(
                    DetailsScreen(
                        1,
                        onAppearTransition = SlideTransition.Horizontal.Appear
                    )
                )
            }
        ) {
            Text(text = "Show details")
        }
    }
}

data class DetailsScreen(val id: Long, override val onAppearTransition: ScreenTransition?) :
    Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Button(
            onClick = {
                navigator.pop()
            }
        ) {
            Text(text = "Go back")
        }
    }
}