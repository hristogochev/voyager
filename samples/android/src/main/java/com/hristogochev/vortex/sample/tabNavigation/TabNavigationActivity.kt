@file:SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

package com.hristogochev.vortex.sample.tabNavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.hristogochev.vortex.navigator.CurrentTab
import com.hristogochev.vortex.navigator.LocalNavigator
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.sample.tabNavigation.tabs.FavoritesTab
import com.hristogochev.vortex.sample.tabNavigation.tabs.HomeTab
import com.hristogochev.vortex.sample.tabNavigation.tabs.ProfileTab
import com.hristogochev.vortex.tab.Tab
import com.hristogochev.vortex.util.currentOrThrow

private val zero = 0u.toUShort()
private val one = 1u.toUShort()
private val two = 2u.toUShort()

class TabNavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Content()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content() {
        Navigator(screen = HomeTab) { navigator ->
            Scaffold(
                topBar = {
                    val title = when ((navigator.lastItem as Tab).index) {
                        zero -> "Home"
                        one -> "Favorites"
                        two -> "Profile"
                        else -> "Unknown"
                    }
                    TopAppBar(
                        title = {
                            Text(text = title)
                        }
                    )
                },
                content = { innerPadding ->
                    CurrentTab(navigator)
                },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(FavoritesTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val navigator = LocalNavigator.currentOrThrow

        val painter = when (tab.index) {
            zero -> rememberVectorPainter(Icons.Default.Home)
            one -> rememberVectorPainter(Icons.Default.Favorite)
            two -> rememberVectorPainter(Icons.Default.Person)
            else -> return
        }

        val title = when (tab.index) {
            zero -> "Home"
            one -> "Favorites"
            two -> "Profile"
            else -> return
        }

        NavigationBarItem(
            selected = navigator.lastItem.key == tab.key,
            onClick = {
                navigator.replaceAll(tab)
            },
            icon = {
                Icon(
                    painter = painter,
                    contentDescription = title
                )
            }
        )
    }
}
