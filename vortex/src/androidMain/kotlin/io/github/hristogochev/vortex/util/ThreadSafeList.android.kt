package io.github.hristogochev.vortex.util

import java.util.concurrent.CopyOnWriteArrayList

internal class AndroidThreadSafeList<T> : MutableList<T> by CopyOnWriteArrayList(), ThreadSafeList<T>

internal actual fun <T> getThreadSafeList(): ThreadSafeList<T> {
    return AndroidThreadSafeList()
}
