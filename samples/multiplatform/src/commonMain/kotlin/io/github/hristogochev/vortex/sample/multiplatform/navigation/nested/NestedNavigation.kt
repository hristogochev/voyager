package io.github.hristogochev.vortex.sample.multiplatform.navigation.nested

import androidx.compose.runtime.Composable
import io.github.hristogochev.vortex.navigator.Navigator
import io.github.hristogochev.vortex.screen.Screen

@Composable
fun NestedNavigation() {
    Navigator(ScreenA)
}

data object ScreenA : Screen {

    @Composable
    override fun Content() {
        Navigator(ScreenB)
    }
}

data object ScreenB : Screen {

    @Composable
    override fun Content() {
        // ...
    }
}