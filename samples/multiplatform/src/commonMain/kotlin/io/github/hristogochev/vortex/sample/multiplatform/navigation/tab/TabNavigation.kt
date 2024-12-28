package io.github.hristogochev.vortex.sample.multiplatform.navigation.tab

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.navigator.Navigator
import io.github.hristogochev.vortex.tab.CurrentTab
import io.github.hristogochev.vortex.tab.Tab
import io.github.hristogochev.vortex.util.currentOrThrow

@Composable
fun TabNavigation() {
    Navigator(HomeTab) { navigator ->
        Scaffold(
            content = {
                CurrentTab(navigator)
            },
            bottomBar = {
                NavigationBar {
                    TabNavigationItem(HomeTab)
                    TabNavigationItem(ProfileTab)
                }
            }
        )
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val navigator = LocalNavigator.currentOrThrow

    val icon = when (tab.index) {
        0u -> rememberVectorPainter(Icons.Default.Home)
        1u -> rememberVectorPainter(Icons.Default.Person)
        else -> return
    }

    val title = when (tab.index) {
        0u -> "Home"
        2u -> "Profile"
        else -> return
    }

    NavigationBarItem(
        selected = navigator.current == tab,
        onClick = { navigator.current = tab },
        icon = { Icon(painter = icon, contentDescription = title) }
    )
}

data object HomeTab : Tab {
    override val index: UInt = 0u

    @Composable
    override fun Content() {
        // ...
    }
}

data object ProfileTab : Tab {
    override val index: UInt = 1u

    @Composable
    override fun Content() {
        // ...
    }
}