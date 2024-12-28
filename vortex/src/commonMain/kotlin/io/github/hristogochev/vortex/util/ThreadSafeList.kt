package io.github.hristogochev.vortex.util

internal interface ThreadSafeList<T> : MutableList<T> {
    companion object {
        operator fun <T> invoke(): ThreadSafeList<T> {
            return getThreadSafeList()
        }
    }
}

internal expect fun <T> getThreadSafeList(): ThreadSafeList<T>
