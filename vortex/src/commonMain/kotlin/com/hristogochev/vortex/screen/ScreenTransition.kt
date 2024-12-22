package com.hristogochev.vortex.screen

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import com.hristogochev.vortex.util.Serializable

public interface ScreenTransition : Serializable {
    public val zIndex: Float?
        get() = null

    public fun enter(): EnterTransition
    public fun exit(): ExitTransition
    public fun transform(): ContentTransform {
        return enter() togetherWith exit()
    }
}