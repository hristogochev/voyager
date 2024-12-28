package io.github.hristogochev.vortex.sample.multiplatform.navigation.basic

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.navigator.Navigator
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.util.currentOrThrow

@Composable
fun BasicNavigation() {
    Navigator(HomeScreen)
}

data object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Button(
            onClick = {
                navigator.push(DetailsScreen(1))
                // navigator.replace(DetailsScreen(1)) is also possible
                // "replace" removes the current screen and replaces it with the one specified.
            }
        ) {
            Text(text = "Show details")
        }
    }
}

data class DetailsScreen(val id: Long) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Button(
            onClick = {
                navigator.pop()
                // If we invoked "replace" on the current screen with this one instead of pushing, "pop" wouldn't do anything.
            }
        ) {
            Text(text = "Go back")
        }
    }
}