package io.github.hristogochev.vortex.kodein

import androidx.compose.runtime.Composable
import io.github.hristogochev.vortex.model.ScreenModel
import io.github.hristogochev.vortex.model.rememberNavigatorScreenModel
import io.github.hristogochev.vortex.model.rememberScreenModel
import io.github.hristogochev.vortex.navigator.Navigator
import org.kodein.di.compose.localDI
import org.kodein.di.direct
import org.kodein.di.provider

@Composable
public inline fun <reified T : ScreenModel> kodeinScreenModel(
    tag: Any? = null,
): T = with(localDI()) {
    rememberScreenModel(tag = tag?.toString()) { direct.provider<T>(tag)() }
}

@Composable
public inline fun <reified A : Any, reified T : ScreenModel> kodeinScreenModel(
    tag: Any? = null,
    arg: A,
): T = with(localDI()) {
    rememberScreenModel(tag = tag?.toString()) { direct.provider<A, T>(tag, arg)() }
}

@Composable
public inline fun <reified T : ScreenModel> Navigator.kodeinNavigatorScreenModel(
    tag: Any? = null,
): T = with(localDI()) {
    rememberNavigatorScreenModel(tag = tag?.toString()) { direct.provider<T>(tag)() }
}

@Composable
public inline fun <reified A : Any, reified T : ScreenModel> Navigator.kodeinNavigatorScreenModel(
    tag: Any? = null,
    arg: A,
): T = with(localDI()) {
    rememberNavigatorScreenModel(tag = tag?.toString()) { direct.provider<A, T>(tag, arg)() }
}
