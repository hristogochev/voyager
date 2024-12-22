package com.hristogochev.vortex.navigator

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hristogochev.vortex.model.ScreenModelStore
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.ScreenDisposableEffectStore
import com.hristogochev.vortex.stack.StackEvent
import com.hristogochev.vortex.stack.isDisposableEvent
import com.hristogochev.vortex.util.currentOrThrow

/**
 *  Displays the current screen of a [Navigator].
 *
 *  Takes in a default transition for when a screen enters and leaves the visible area.
 *
 *  By default there is no transition.
 *
 *  Each [Screen] can have it's own transition for when it enters and leaves the visible area.
 */
@Composable
public fun CurrentScreen(
    navigator: Navigator,
    defaultTransition: AnimatedContentTransitionScope<Screen>.() -> ContentTransform = {
        (EnterTransition.None togetherWith ExitTransition.None)
    },
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    contentKey: (Screen) -> Any = { it.key },
    content: @Composable AnimatedVisibilityScope.(Screen) -> Unit = { it.Content() },
) {
    val screenCandidatesToDispose = rememberSaveable(saver = screenCandidatesToDisposeSaver()) {
        mutableStateOf(emptySet())
    }

    val currentScreens = navigator.items

    DisposableEffect(currentScreens) {
        onDispose {
            val newScreenKeys = navigator.items.map { it.key }
            screenCandidatesToDispose.value += currentScreens.filter { it.key !in newScreenKeys }
        }
    }

    AnimatedContent(
        targetState = navigator.lastItem,
        transitionSpec = {
            val contentTransform = defaultTransition()

            val customTransition = when (navigator.lastEvent) {
                StackEvent.Pop -> initialState.onDisappear
                else -> targetState.onAppear
            }

            ContentTransform(
                targetContentEnter = customTransition?.enter()
                    ?: contentTransform.targetContentEnter,
                initialContentExit = customTransition?.exit()
                    ?: contentTransform.initialContentExit,
                targetContentZIndex = customTransition?.zIndex
                    ?: contentTransform.targetContentZIndex,
                sizeTransform = contentTransform.sizeTransform
            )
        },
        contentAlignment = contentAlignment,
        contentKey = contentKey,
        modifier = modifier
    ) { screen ->
        if (this.transition.targetState == this.transition.currentState) {
            val stateHolder = LocalNavigatorStateHolder.currentOrThrow

            LaunchedEffect(Unit) {
                val newScreens = navigator.items.map { it.key }

                val deletedScreenIdentifiers = screenCandidatesToDispose.value
                    .filterNot { it.key in newScreens }
                    .map { it.key }

                if (deletedScreenIdentifiers.isNotEmpty()) {

                    for (screenIdentifier in deletedScreenIdentifiers) {
                        ScreenModelStore.dispose(screenIdentifier)

                        ScreenDisposableEffectStore.dispose(screenIdentifier)

                        stateHolder.removeState(screenIdentifier)

                        navigator.disassociateStateKey(screenIdentifier)
                    }

                    navigator.clearEvent()
                }

                screenCandidatesToDispose.value = emptySet()
            }
        }

        screen.render {
            content(it)
        }
    }
}


/**
 *  Displays the current screen of a [Navigator].
 *
 *  Forbids any transitions from showing.
 *
 *  Can be more efficient if you don't plan to use transitions.
 */
@Composable
public fun CurrentScreenNoTransitions(
    navigator: Navigator,
    content: @Composable (Screen) -> Unit = { it.Content() },
) {
    CurrentScreenNoTransitionsDisposable(navigator)

    navigator.lastItem.render {
        content(it)
    }
}

/**
 * Creates a listener that listens for screen stack changes and disposes of the screens that are no longer in the stack.
 */
@Composable
public fun CurrentScreenNoTransitionsDisposable(navigator: Navigator) {
    val stateHolder = LocalNavigatorStateHolder.currentOrThrow

    val currentScreens = navigator.items.map { it.key }

    DisposableEffect(currentScreens) {
        onDispose {
            val newScreenIdentifiers = navigator.items.map { it.key }

            if (!navigator.lastEvent.isDisposableEvent()) {
                return@onDispose
            }

            val destroyedScreenIdentifiers =
                currentScreens.filter { it !in newScreenIdentifiers }

            for (screenIdentifier in destroyedScreenIdentifiers) {
                ScreenModelStore.dispose(screenIdentifier)

                ScreenDisposableEffectStore.dispose(screenIdentifier)

                stateHolder.removeState(screenIdentifier)

                navigator.disassociateStateKey(screenIdentifier)
            }

            navigator.clearEvent()
        }
    }
}

/**
 * Renders the current screen, saving its state even if its no longer displayed.
 *
 * This function should only be used externally if you want to create a totally custom transition, e.g. iOS swipe.
 */
@Composable
public fun <T : Screen> T.render(content: @Composable (T) -> Unit = { it.Content() }) {

    val stateHolder = LocalNavigatorStateHolder.currentOrThrow
    val navigator = LocalNavigator.currentOrThrow

    navigator.associateStateKey(key)

    stateHolder.SaveableStateProvider(key) {
        CompositionLocalProvider(
            LocalScreenIdentifier provides key
        ) {
            content(this)
        }
    }
}

/**
 * Just an utility saver for the screens that should be disposed during a transition.
 */
private fun screenCandidatesToDisposeSaver(): Saver<MutableState<Set<Screen>>, List<Screen>> {
    return Saver(
        save = { it.value.toList() },
        restore = { mutableStateOf(it.toSet()) }
    )
}