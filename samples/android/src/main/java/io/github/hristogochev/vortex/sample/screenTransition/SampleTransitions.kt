package io.github.hristogochev.vortex.sample.screenTransition

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset
import io.github.hristogochev.vortex.screen.ScreenTransition

data object FadeTransition : ScreenTransition {

    override fun enter(): EnterTransition {
        return fadeIn(tween(500, delayMillis = 500))
    }

    override fun exit(): ExitTransition {
        return fadeOut(tween(500))
    }
}

data object PushSlideTransition : ScreenTransition {

    override fun enter(): EnterTransition {
        return slideIn { size ->
            IntOffset(x = size.width, y = 0)
        }
    }

    override fun exit(): ExitTransition {
        return slideOut { size ->
            IntOffset(x = -size.width, y = 0)
        }
    }
}

data object PopPushSlideTransition : ScreenTransition {

    override fun enter(): EnterTransition {
        return slideIn { size ->
            IntOffset(x = -size.width, y = 0)
        }
    }

    override fun exit(): ExitTransition {
        return slideOut { size ->
            IntOffset(x = size.width, y = 0)
        }
    }
}


data class PushSlideInVerticallyTransition(private val index: Int) : ScreenTransition {

    override fun enter(): EnterTransition {
        return slideInVertically { it }
    }

    override fun exit(): ExitTransition {
        return fadeOut(targetAlpha = 0.9f)
    }

    override val zIndex: Float = (index + 1).toFloat()
}


data class PopSlideInVerticallyTransition(private val index: Int) : ScreenTransition {

    override fun enter(): EnterTransition {
        return fadeIn(initialAlpha = 0.9f)
    }

    override fun exit(): ExitTransition {
        return slideOutVertically { it }
    }

    override val zIndex: Float = index.toFloat()
}
