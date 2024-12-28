package io.github.hristogochev.vortex.transitions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import io.github.hristogochev.vortex.screen.ScreenTransition

public enum class Scales(
    public val initial: Float,
    public val target: Float,
) {
    Enter(1.1f, 0.95f),
    Exit(0.95f, 1.1f)
}

private val animationSpec: FiniteAnimationSpec<Float> =
    spring(stiffness = Spring.StiffnessMediumLow)

public sealed interface ScaleTransition : ScreenTransition {

    public data object Appear : ScaleTransition {
        private val scales = Scales.Enter
        override fun enter(): EnterTransition {
            return scaleIn(initialScale = scales.initial, animationSpec = animationSpec)
        }

        override fun exit(): ExitTransition {
            return scaleOut(targetScale = scales.target, animationSpec = animationSpec)
        }
    }

    public data object Disappear : ScaleTransition {
        private val scales = Scales.Exit

        override fun enter(): EnterTransition {
            return scaleIn(initialScale = scales.initial, animationSpec = animationSpec)
        }

        override fun exit(): ExitTransition {
            return scaleOut(targetScale = scales.target, animationSpec = animationSpec)
        }
    }
}
