package io.github.hristogochev.vortex.sample.multiplatform.screenModel.koin

import androidx.compose.runtime.Composable
import io.github.hristogochev.vortex.koin.koinNavigatorScreenModel
import io.github.hristogochev.vortex.koin.koinScreenModel
import io.github.hristogochev.vortex.model.ScreenModel
import io.github.hristogochev.vortex.model.screenModelScope
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.util.currentOrThrow
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeModule = module {
    factory { HomeScreenModel() }
}

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
        val screenModel = koinScreenModel<HomeScreenModel>()
        // ...
        val navigator = LocalNavigator.currentOrThrow
        val navigatorScreenModel = navigator.koinNavigatorScreenModel<HomeScreenModel>()
        // ...
    }
}