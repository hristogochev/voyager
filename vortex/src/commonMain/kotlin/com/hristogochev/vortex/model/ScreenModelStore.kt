package com.hristogochev.vortex.model

import androidx.compose.runtime.DisallowComposableCalls
import com.hristogochev.vortex.util.ThreadSafeMap

@PublishedApi
internal object ScreenModelStore {

    val screenModels: MutableMap<String, ScreenModel> = ThreadSafeMap()

    /**
     * Saves a for a screen model by key, the key is a screen/navigator key + something else
     */
    inline fun <reified T : ScreenModel> getOrPut(
        key: String,
        factory: @DisallowComposableCalls () -> T,
    ): T {
        return screenModels.getOrPut(key, factory) as T
    }

    /**
     * Disposes of all screen models for a screen/navigator
     */
    fun dispose(holderKey: String) {
        // Creates a copy of all screen model keys
        // Finds the ones starting with the holder key
        // Then disposes of all screen models with the keys
        screenModels.keys.toSet()
            .asSequence()
            .filter { it.startsWith(holderKey) }
            .forEach { key ->
                screenModels[key]?.onDispose()
                screenModels -= key
            }
    }
}

