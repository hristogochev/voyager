package io.github.hristogochev.vortex.screen

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.togetherWith
import io.github.hristogochev.vortex.util.Serializable

public interface ScreenTransition : Serializable {
    public val zIndex: Float?
        get() = null

    public fun sizeTransform(): SizeTransform? = null
    public fun enter(): EnterTransition
    public fun exit(): ExitTransition
    public fun transform(): ContentTransform {
        return enter() togetherWith exit()
    }
}
