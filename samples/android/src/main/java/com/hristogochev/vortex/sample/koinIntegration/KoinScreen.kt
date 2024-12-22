package com.hristogochev.vortex.sample.koinIntegration

import androidx.compose.runtime.Composable
import com.hristogochev.vortex.koin.koinScreenModel
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.uniqueScreenKey
import com.hristogochev.vortex.sample.ListContent

class KoinScreen : Screen {

    override val key: String = uniqueScreenKey()

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<KoinScreenModel>()

        ListContent(screenModel.items)
    }
}
