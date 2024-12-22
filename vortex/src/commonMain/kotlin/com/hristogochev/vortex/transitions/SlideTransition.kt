package com.hristogochev.vortex.transitions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset
import com.hristogochev.vortex.navigator.Navigator
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.ScreenTransition
import com.hristogochev.vortex.stack.StackEvent

public enum class SlideOrientation {
    Horizontal,
    Vertical
}


public enum class SlideDirection(
    public val initial: (size: Int) -> Int,
    public val target: (size: Int) -> Int,
) {
    Backward({ size: Int -> -size }, { size: Int -> size }),
    Forward({ size: Int -> size }, { size: Int -> -size })
}


private val animationSpec: FiniteAnimationSpec<IntOffset> = spring(
    stiffness = Spring.StiffnessMediumLow,
    visibilityThreshold = IntOffset.VisibilityThreshold
)

public fun <T : Screen> slideTransition(
    navigator: Navigator,
    orientation: SlideOrientation = SlideOrientation.Horizontal,
): AnimatedContentTransitionScope<T>.() -> ContentTransform {
    return {
        when (orientation) {
            SlideOrientation.Horizontal -> {
                when (navigator.lastEvent) {
                    StackEvent.Pop -> SlideTransition.Horizontal.Pop.transform()
                    else -> SlideTransition.Horizontal.Push.transform()
                }
            }

            SlideOrientation.Vertical -> {
                when (navigator.lastEvent) {
                    StackEvent.Pop -> SlideTransition.Vertical.Pop.transform()
                    else -> SlideTransition.Vertical.Push.transform()
                }
            }
        }
    }
}


public sealed interface SlideTransition : ScreenTransition {

    public sealed interface Horizontal : SlideTransition {
        public data object Push : Horizontal {

            private val offsets = SlideDirection.Forward

            override fun enter(): EnterTransition =
                slideInHorizontally(animationSpec, offsets.initial)

            override fun exit(): ExitTransition =
                slideOutHorizontally(animationSpec, offsets.target)
        }

        public data object Pop : Horizontal {

            private val offsets = SlideDirection.Backward

            override fun enter(): EnterTransition =
                slideInHorizontally(animationSpec, offsets.initial)

            override fun exit(): ExitTransition =
                slideOutHorizontally(animationSpec, offsets.target)
        }
    }

    public sealed interface Vertical : SlideTransition {
        public data object Push : Horizontal {
            private val offsets = SlideDirection.Forward
            override fun enter(): EnterTransition =
                slideInVertically(animationSpec, offsets.initial)

            override fun exit(): ExitTransition =
                slideOutVertically(animationSpec, offsets.target)
        }

        public data object Pop : Horizontal {
            private val offsets = SlideDirection.Backward
            override fun enter(): EnterTransition =
                slideInVertically(animationSpec, offsets.initial)

            override fun exit(): ExitTransition =
                slideOutVertically(animationSpec, offsets.target)
        }
    }
}
