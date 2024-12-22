package com.hristogochev.vortex.kodein

import androidx.compose.runtime.Composable
import com.hristogochev.vortex.model.ScreenModel
import com.hristogochev.vortex.model.rememberNavigatorScreenModel
import com.hristogochev.vortex.model.rememberScreenModel
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.screen.Screen
import org.kodein.di.compose.localDI
import org.kodein.di.direct
import org.kodein.di.provider

@Composable
public inline fun <reified T : ScreenModel> Screen.rememberScreenModel(
    tag: Any? = null,
): T = with(localDI()) {
    rememberScreenModel(tag = tag?.toString()) { direct.provider<T>(tag)() }
}

@Composable
public inline fun <reified A : Any, reified T : ScreenModel> Screen.rememberScreenModel(
    tag: Any? = null,
    arg: A,
): T = with(localDI()) {
    rememberScreenModel(tag = tag?.toString()) { direct.provider<A, T>(tag, arg)() }
}

@Composable
public inline fun <reified T : ScreenModel> Navigator.rememberNavigatorScreenModel(
    tag: Any? = null,
): T = with(localDI()) {
    rememberNavigatorScreenModel(tag = tag?.toString()) { direct.provider<T>(tag)() }
}

@Composable
public inline fun <reified A : Any, reified T : ScreenModel> Navigator.rememberNavigatorScreenModel(
    tag: Any? = null,
    arg: A,
): T = with(localDI()) {
    rememberNavigatorScreenModel(tag = tag?.toString()) { direct.provider<A, T>(tag, arg)() }
}
