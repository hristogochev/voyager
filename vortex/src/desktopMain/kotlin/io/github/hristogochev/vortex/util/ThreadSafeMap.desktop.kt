package io.github.hristogochev.vortex.util

import java.util.concurrent.ConcurrentHashMap

internal class DesktopThreadSafeMap<K,V>: MutableMap<K, V> by ConcurrentHashMap(), ThreadSafeMap<K, V>

internal actual fun <K, V> getThreadSafeMap(): ThreadSafeMap<K, V> {
    return DesktopThreadSafeMap()
}