package com.hristogochev.vortex.sample.screenModel

import androidx.compose.runtime.Composable
import com.hristogochev.vortex.model.rememberScreenModel
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.uniqueScreenKey
import com.hristogochev.vortex.navigator.LocalNavigator
import com.hristogochev.vortex.sample.ListContent
import com.hristogochev.vortex.util.currentOrThrow

class ListScreen(override val key: String = uniqueScreenKey()) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { ListScreenModel() }

        ListContent(screenModel.items, onClick = { index -> navigator.push(DetailsScreen(index)) })
    }
}
