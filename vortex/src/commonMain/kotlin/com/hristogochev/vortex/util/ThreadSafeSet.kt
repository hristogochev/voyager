package com.hristogochev.vortex.util

internal interface ThreadSafeSet<T> : MutableSet<T> {
    companion object {

        operator fun <T> invoke(initial: Collection<T>): ThreadSafeSet<T> {
            return getThreadSafeSet<T>().apply {
                if (initial.isNotEmpty()) addAll(initial)
            }
        }
    }
}

internal expect fun <T> getThreadSafeSet(): ThreadSafeSet<T>
