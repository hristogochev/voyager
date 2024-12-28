package io.github.hristogochev.vortex.tab

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.hristogochev.vortex.navigator.Navigator
import io.github.hristogochev.vortex.screen.ScreenTransition
import io.github.hristogochev.vortex.screen.render
import io.github.hristogochev.vortex.stack.StackEvent

/**
 *  Displays the current tab of a [Navigator].
 *
 *  Takes in a default transition for when a tab enters and leaves the visible area.
 *
 *  By default there is no transition.
 *
 *  Each [Tab] can have it's own transition for when it enters and leaves the visible area.
 */
@Composable
public fun CurrentTab(
    navigator: Navigator,
    defaultOnScreenAppearTransition: ScreenTransition? = null,
    defaultOnScreenDisappearTransition: ScreenTransition? = null,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.(Tab) -> Unit = { it.Content() },
) {
    AnimatedContent(
        targetState = navigator.current as Tab,
        transitionSpec = {

            val transition = when (navigator.lastEvent) {
                StackEvent.Pop -> initialState.onDisappearTransition ?: defaultOnScreenDisappearTransition
                else -> targetState.onAppearTransition ?: defaultOnScreenAppearTransition
            }

            ContentTransform(
                targetContentEnter = transition?.enter() ?: EnterTransition.None,
                initialContentExit = transition?.exit() ?: ExitTransition.None,
                targetContentZIndex = transition?.zIndex ?: 0f,
                sizeTransform = transition?.sizeTransform() ?: SizeTransform()
            )
        },
        modifier = modifier
    ) { tab ->
        tab.render {
            content(it)
        }
    }
}

/**
 *  Displays the current tab of a [Navigator].
 *
 *  Forbids any transitions from showing.
 *
 *  Can be more efficient if you don't plan to use transitions.
 */
@Composable
public fun CurrentTabNoTransitions(
    navigator: Navigator,
    content: @Composable (Tab) -> Unit = { it.Content() },
) {
    navigator.current.render { tab ->
        content(tab as Tab)
    }
}

/**
 *  Displays the current tab of a [Navigator] with a crossfade transition.
 *
 *  Forbids any transitions overrides from showing.
 */
@Composable
public fun CurrentTabCrossfadeTransition(
    navigator: Navigator,
    animationSpec: FiniteAnimationSpec<Float> = tween(),
    label: String = "Crossfade",
    modifier: Modifier = Modifier,
    content: @Composable (Tab) -> Unit = { it.Content() },
) {
    Crossfade(
        targetState = navigator.current as Tab,
        animationSpec = animationSpec,
        modifier = modifier,
        label = label
    ) { tab ->
        tab.render {
            content(it)
        }
    }
}
