package com.hristogochev.vortex.transitions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.ScreenTransition
import com.hristogochev.vortex.stack.StackEvent

public enum class Scales(
    public val initial: Float,
    public val target: Float,
) {
    Enter(1.1f, 0.95f),
    Exit(0.95f, 1.1f)
}

private val animationSpec: FiniteAnimationSpec<Float> =
    spring(stiffness = Spring.StiffnessMediumLow)

public fun <T : Screen> scaleTransition(navigator: Navigator): AnimatedContentTransitionScope<T>.() -> ContentTransform {
    return {
        when (navigator.lastEvent) {
            StackEvent.Pop -> PopScaleTransition.transform()
            else -> PushScaleTransition.transform()
        }
    }
}

public data object PushScaleTransition : ScreenTransition {
    private val scales = Scales.Enter
    override fun enter(): EnterTransition {
        return scaleIn(initialScale = scales.initial, animationSpec = animationSpec)
    }

    override fun exit(): ExitTransition {
        return scaleOut(targetScale = scales.target, animationSpec = animationSpec)
    }
}

public data object PopScaleTransition : ScreenTransition {
    private val animationSpec: FiniteAnimationSpec<Float> =
        spring(stiffness = Spring.StiffnessMediumLow)

    private val scales = Scales.Exit
    override fun enter(): EnterTransition {
        return scaleIn(initialScale = scales.initial, animationSpec = animationSpec)
    }

    override fun exit(): ExitTransition {
        return scaleOut(targetScale = scales.target, animationSpec = animationSpec)
    }
}
