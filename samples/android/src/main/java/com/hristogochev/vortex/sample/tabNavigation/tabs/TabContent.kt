package com.hristogochev.vortex.sample.tabNavigation.tabs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hristogochev.vortex.navigator.LocalNavigator
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.sample.basicNavigation.BasicNavigationScreen
import com.hristogochev.vortex.screen.CurrentScreen
import com.hristogochev.vortex.screen.ScreenDisposableEffect
import com.hristogochev.vortex.tab.Tab
import com.hristogochev.vortex.transitions.SlideTransition
import com.hristogochev.vortex.util.currentOrThrow


private val zero = 0u.toUShort()
private val one = 1u.toUShort()
private val two = 2u.toUShort()

@Composable
fun Tab.TabContent() {
    val tabTitle = when (index) {
        zero -> "Home"
        one -> "Favorites"
        two -> "Profile"
        else -> "Unknown"
    }

    ScreenDisposableEffect {
        Log.d("Navigator", "Start tab $tabTitle")
        onDispose {
            Log.d("Navigator", "Dispose tab $tabTitle")
        }
    }
    Navigator(BasicNavigationScreen(index = 0)) { navigator ->
        CurrentScreen(
            navigator = navigator,
            onScreenAppear = SlideTransition.Horizontal.Appear,
            onScreenDisappear = SlideTransition.Horizontal.Disappear,
        ) { screen ->
            Column {
                InnerTabNavigation()
                screen.Content()
                Log.d("Navigator", "Last Event: ${navigator.lastEvent}")
            }
        }
    }
}

@Composable
private fun InnerTabNavigation() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        TabNavigationButton(HomeTab)

        Spacer(modifier = Modifier.weight(.05f))

        TabNavigationButton(FavoritesTab)

        Spacer(modifier = Modifier.weight(.05f))

        TabNavigationButton(ProfileTab)
    }
}

@Composable
private fun RowScope.TabNavigationButton(
    tab: Tab,
) {
    val tabNavigator = LocalNavigator.currentOrThrow
    val tabTitle = when (tab.index) {
        zero -> "Home"
        one -> "Favorites"
        two -> "Profile"
        else -> "Unknown"
    }
    Button(
        enabled = tabNavigator.lastItem.key != tab.key,
        onClick = { tabNavigator.replaceAll(tab) },
        modifier = Modifier.weight(1f)
    ) {
        Text(text = tabTitle)
    }
}
