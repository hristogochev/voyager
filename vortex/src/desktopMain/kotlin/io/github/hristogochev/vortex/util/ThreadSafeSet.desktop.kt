package io.github.hristogochev.vortex.util

import java.util.concurrent.CopyOnWriteArraySet

internal class DesktopThreadSafeSet<T> : MutableSet<T> by CopyOnWriteArraySet(), ThreadSafeSet<T>

internal actual fun <T> getThreadSafeSet(): ThreadSafeSet<T> {
    return DesktopThreadSafeSet()
}