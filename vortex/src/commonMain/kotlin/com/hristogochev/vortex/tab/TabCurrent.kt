package com.hristogochev.vortex.tab

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.screen.render

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
    defaultTransition: AnimatedContentTransitionScope<Tab>.() -> ContentTransform = {
        (EnterTransition.None togetherWith ExitTransition.None)
    },
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.(Tab) -> Unit = { it.Content() },
) {
    AnimatedContent(
        targetState = navigator.lastItem as Tab,
        transitionSpec = defaultTransition,
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
    navigator.lastItem.render { tab ->
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
        targetState = navigator.lastItem as Tab,
        animationSpec = animationSpec,
        modifier = modifier,
        label = label
    ) { tab ->
        tab.render {
            content(it)
        }
    }
}
