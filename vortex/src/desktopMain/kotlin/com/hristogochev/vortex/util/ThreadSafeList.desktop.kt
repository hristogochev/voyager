package com.hristogochev.vortex.util

import java.util.concurrent.CopyOnWriteArrayList

internal class DesktopThreadSafeList<T> : MutableList<T> by CopyOnWriteArrayList(), ThreadSafeList<T>


internal actual fun <T> getThreadSafeList(): ThreadSafeList<T> {
    return DesktopThreadSafeList()
}