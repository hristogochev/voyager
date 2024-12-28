package io.github.hristogochev.vortex.util

internal interface ThreadSafeMap<K, V> : MutableMap<K, V> {
    companion object {
        operator fun <K, V> invoke(): ThreadSafeMap<K, V> {
            return getThreadSafeMap()
        }
    }
}

internal expect fun <K, V> getThreadSafeMap(): ThreadSafeMap<K, V>
