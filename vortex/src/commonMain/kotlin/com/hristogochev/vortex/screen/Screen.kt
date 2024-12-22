package com.hristogochev.vortex.screen

import androidx.compose.runtime.Composable
import com.hristogochev.vortex.util.Serializable
import com.hristogochev.vortex.util.multiplatformName
import com.hristogochev.vortex.util.randomUuid

public interface Screen : Serializable {

    public val key: String
        get() = name()

    public val onAppear: ScreenTransition?
        get() = null

    public val onDisappear: ScreenTransition?
        get() = null

    @Composable
    public fun Content()
}

public fun Screen.name(): String {
    return this::class.multiplatformName
        ?: error("Attempted to get the name of a local or anonymous screen")
}

public fun uniqueScreenKey(): String {
    return "Screen#${randomUuid()}"
}


