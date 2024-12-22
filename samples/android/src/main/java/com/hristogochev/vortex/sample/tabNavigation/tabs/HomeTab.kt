package com.hristogochev.vortex.sample.tabNavigation.tabs

import androidx.compose.runtime.Composable
import com.hristogochev.vortex.screen.uniqueScreenKey
import com.hristogochev.vortex.tab.Tab

data object HomeTab : Tab {

    override val key: String = uniqueScreenKey()

    override val index: UShort = 0u

    @Composable
    override fun Content() {
        TabContent()
    }
}
