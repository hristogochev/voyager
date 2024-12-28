package io.github.hristogochev.vortex.sample.multiplatform.screenModel.screenModel

import androidx.compose.runtime.Composable
import io.github.hristogochev.vortex.model.ScreenModel
import io.github.hristogochev.vortex.model.rememberNavigatorScreenModel
import io.github.hristogochev.vortex.model.rememberScreenModel
import io.github.hristogochev.vortex.model.screenModelScope
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.util.currentOrThrow
import kotlinx.coroutines.launch

class HomeScreenModel : ScreenModel {

    init {
        screenModelScope.launch {
            // ..
        }
    }

    // Optional
    override fun onDispose() {
        // ...
    }
}

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { HomeScreenModel() }
        // ...
        val navigator = LocalNavigator.currentOrThrow
        val navigatorScreenModel = navigator.rememberNavigatorScreenModel { HomeScreenModel() }
        // ...
    }
}