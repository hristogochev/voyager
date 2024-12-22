package com.hristogochev.vortex.sample.kodeinIntegration

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.hristogochev.vortex.kodein.rememberScreenModel
import com.hristogochev.vortex.sample.ListContent
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.uniqueScreenKey

class KodeinScreen(override val key: String = uniqueScreenKey()) : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<KodeinScreenModel>()

        Column {
            Text("Hello kodein!")
            ListContent(screenModel.items)
        }
    }
}
