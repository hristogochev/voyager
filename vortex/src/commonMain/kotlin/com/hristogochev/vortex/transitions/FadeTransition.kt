package com.hristogochev.vortex.transitions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.ScreenTransition

private val animationSpec: FiniteAnimationSpec<Float> =
    spring(stiffness = Spring.StiffnessMediumLow)

public fun <T : Screen> fadeTransition(): AnimatedContentTransitionScope<T>.() -> ContentTransform {
    return { FadeTransition.transform() }
}

public data object FadeTransition : ScreenTransition {
    override fun enter(): EnterTransition {
        return fadeIn(animationSpec = animationSpec)
    }

    override fun exit(): ExitTransition {
        return fadeOut(animationSpec = animationSpec)
    }
}