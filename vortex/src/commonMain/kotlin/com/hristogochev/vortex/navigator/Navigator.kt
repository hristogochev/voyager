package com.hristogochev.vortex.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import com.hristogochev.vortex.model.ScreenModelStore
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.ScreenDisposableEffect
import com.hristogochev.vortex.screen.ScreenDisposableEffectStore
import com.hristogochev.vortex.stack.Stack
import com.hristogochev.vortex.stack.toMutableStateStack
import com.hristogochev.vortex.util.DisposableEffectUnlessChangingConfiguration
import com.hristogochev.vortex.util.Serializable
import com.hristogochev.vortex.util.ThreadSafeSet
import com.hristogochev.vortex.util.randomUuid


internal val LocalNavigatorStateHolder: ProvidableCompositionLocal<SaveableStateHolder?> =
    staticCompositionLocalOf { null }

@Composable
private fun LocalNavigatorStateHolderProvider(content: @Composable () -> Unit) {
    val navigatorStateHolder = LocalNavigatorStateHolder.current

    if (navigatorStateHolder == null) {
        CompositionLocalProvider(
            LocalNavigatorStateHolder provides rememberSaveableStateHolder(),
        ) {
            content()
        }
    } else {
        content()
    }
}

public val LocalNavigator: ProvidableCompositionLocal<Navigator?> =
    staticCompositionLocalOf { null }

public val LocalScreenIdentifier: ProvidableCompositionLocal<String?> =
    staticCompositionLocalOf { null }

@Composable
public fun Navigator(
    screen: Screen,
    content: @Composable (navigator: Navigator) -> Unit = { CurrentScreen(it) },
) {
    Navigator(
        screens = listOf(screen),
        content = content
    )
}

@Composable
public fun Navigator(
    screens: List<Screen>,
    useBackHandler: Boolean = true,
    content: @Composable (navigator: Navigator) -> Unit = { CurrentScreen(it) },
) {
    require(screens.isNotEmpty()) { "Navigator must have at least one screen" }

    LocalNavigatorStateHolderProvider {
        val parent = LocalNavigator.current
        val parentUpdatedState by rememberUpdatedState(parent)

        val stateHolder =
            LocalNavigatorStateHolder.current ?: error("LocalNavigatorStateHolder not initialized")

        val navigatorSaver: Saver<Navigator, NavigatorSavable> = remember {
            Saver(
                save = { navigator ->
                    NavigatorSavable(
                        navigator.key,
                        navigator.items.toList(),
                        navigator.getAllScreenIdentifiers().toList()
                    )
                },
                restore = { saved ->
                    Navigator(
                        saved.screens,
                        saved.key,
                        parentUpdatedState,
                        screenIdentifiers = ThreadSafeSet(saved.savedStates)
                    )
                }
            )
        }

        val navigator = rememberSaveable(saver = navigatorSaver) {
            val key = randomUuid()
            Navigator(screens, key, parentUpdatedState, ThreadSafeSet(emptySet()))
        }

        val navigatorUpdatedState by rememberUpdatedState(navigator)

        if (LocalScreenIdentifier.current != null) {
            ScreenDisposableEffect {
                onDispose {
                    val screenIdentifiers = navigatorUpdatedState.getAllScreenIdentifiers()

                    for (screenIdentifier in screenIdentifiers) {

                        ScreenModelStore.dispose(screenIdentifier)

                        ScreenDisposableEffectStore.dispose(screenIdentifier)

                        stateHolder.removeState(screenIdentifier)

                        navigatorUpdatedState.disassociateStateKey(screenIdentifier)
                    }
                    ScreenModelStore.dispose(navigatorUpdatedState.key)

                    navigatorUpdatedState.clearEvent()
                }
            }
        } else {
            DisposableEffectUnlessChangingConfiguration(Unit) {
                onDispose {
                    val screenIdentifiers = navigatorUpdatedState.getAllScreenIdentifiers()

                    for (screenIdentifier in screenIdentifiers) {

                        ScreenModelStore.dispose(screenIdentifier)

                        ScreenDisposableEffectStore.dispose(screenIdentifier)

                        stateHolder.removeState(screenIdentifier)

                        navigatorUpdatedState.disassociateStateKey(screenIdentifier)
                    }
                    ScreenModelStore.dispose(navigatorUpdatedState.key)

                    navigatorUpdatedState.clearEvent()
                }
            }
        }

        if (useBackHandler) {
            NavigatorBackHandler(navigator)
        }

        CompositionLocalProvider(
            LocalNavigator provides navigator
        ) {

            content(navigator)
        }
    }
}

public class Navigator internal constructor(
    initialScreens: List<Screen>,
    public val key: String,
    public val parent: Navigator? = null,
    private val screenIdentifiers: ThreadSafeSet<String>,
) : Stack<Screen> by initialScreens.toMutableStateStack(minSize = 1) {

    public fun level(): Int = parent?.level()?.inc() ?: 0

    public fun associateStateKey(screenIdentifier: String) {
        screenIdentifiers += screenIdentifier
    }

    public fun disassociateStateKey(screenIdentifier: String) {
        screenIdentifiers -= screenIdentifier
    }

    public fun getAllScreenIdentifiers(): Set<String> {
        return this.screenIdentifiers.toSet()
    }

    public fun popUntilRoot() {
        popUntilRoot(this)
    }

    private tailrec fun popUntilRoot(navigator: Navigator) {
        navigator.popAll()

        if (navigator.parent != null) {
            popUntilRoot(navigator.parent)
        }
    }
}

public class NavigatorSavable(
    public val key: String,
    public val screens: List<Screen>,
    public val savedStates: List<String>,
) : Serializable
