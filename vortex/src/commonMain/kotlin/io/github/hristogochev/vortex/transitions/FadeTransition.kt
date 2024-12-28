package io.github.hristogochev.vortex.transitions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import io.github.hristogochev.vortex.screen.ScreenTransition

private val animationSpec: FiniteAnimationSpec<Float> =
    spring(stiffness = Spring.StiffnessMediumLow)

public data object FadeTransition : ScreenTransition {
    override fun enter(): EnterTransition {
        return fadeIn(animationSpec = animationSpec)
    }

    override fun exit(): ExitTransition {
        return fadeOut(animationSpec = animationSpec)
    }
}
