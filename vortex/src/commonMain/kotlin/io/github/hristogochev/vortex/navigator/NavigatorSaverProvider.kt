package io.github.hristogochev.vortex.navigator

import androidx.compose.runtime.saveable.Saver
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.util.ThreadSafeSet

/**
 * The navigator saver template.
 *
 * Use this to create your own navigator saver provider
 */
public interface NavigatorSaverProvider<Saveable : Any> {
    public fun provide(parent: Navigator?): Saver<Navigator, Saveable>
}

/**
 * The default navigator saver provider.
 *
 * Requires all parameters and properties of every screen to be Serializable or Parcelable.
 */
public data object SerializableNavigatorSaverProvider : NavigatorSaverProvider<Map<String, Any>> {
    override fun provide(parent: Navigator?): Saver<Navigator, Map<String, Any>> {
        return Saver(
            save = { navigator ->
                // We need to use `.toList()` to create a copy of the items and screen state keys,
                // otherwise their reference will be used, which will not work

                // We use map to save the current navigator
                // because maps are inherently serializable based on their contents
                mapOf(
                    "key" to navigator.key,
                    "items" to navigator.items.toList(),
                    "screenStateKeys" to navigator.getAllScreenStateKeys().toList()
                )
            },
            restore = restore@{ saved ->
                // If any of the core components of a navigator are missing,
                // forget the current saved one and recreate the navigator
                val savedKey = saved["key"] as? String ?: return@restore null

                @Suppress("UNCHECKED_CAST")
                val savedScreens =
                    saved["items"] as? List<Screen> ?: return@restore null

                @Suppress("UNCHECKED_CAST")
                val savedScreenStateKeys =
                    saved["screenStateKeys"] as? List<String> ?: return@restore null

                Navigator(
                    initialScreens = savedScreens,
                    key = savedKey,
                    // We pass in the parent reference (it's kept up to date by Vortex)
                    parent = parent,
                    screenStateKeys = ThreadSafeSet(savedScreenStateKeys)
                )
            }
        )
    }
}

