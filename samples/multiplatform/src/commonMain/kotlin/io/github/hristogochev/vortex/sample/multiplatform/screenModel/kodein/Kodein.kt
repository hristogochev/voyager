package io.github.hristogochev.vortex.sample.multiplatform.screenModel.kodein

import androidx.compose.runtime.Composable
import io.github.hristogochev.vortex.kodein.kodeinNavigatorScreenModel
import io.github.hristogochev.vortex.kodein.kodeinScreenModel
import io.github.hristogochev.vortex.model.ScreenModel
import io.github.hristogochev.vortex.model.screenModelScope
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.sample.multiplatform.screenModel.screenModel.HomeScreenModel
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.util.currentOrThrow
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.bindProvider

val homeModule = DI.Module(name = "home") {
    bindProvider { HomeScreenModel() }
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
        val screenModel = kodeinScreenModel<HomeScreenModel>()
        // ...
        val navigator = LocalNavigator.currentOrThrow
        val navigatorScreenModel = navigator.kodeinNavigatorScreenModel<HomeScreenModel>()
        // ...
    }
}