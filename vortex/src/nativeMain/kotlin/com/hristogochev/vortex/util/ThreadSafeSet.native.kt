package com.hristogochev.vortex.util

import kotlinx.atomicfu.locks.SynchronizedObject

internal actual fun <T> getThreadSafeSet(): ThreadSafeSet<T> {
    return NativeThreadSafeSet()
}

internal class NativeThreadSafeSet<T>(
    syncObject: SynchronizedObject,
    delegate: MutableSet<T>,
) : MutableSet<T>, ThreadSafeSet<T>, ThreadSafeMutableCollection<T>(syncObject, delegate) {
    constructor() : this(delegate = mutableSetOf())
    constructor(delegate: MutableSet<T>) : this(SynchronizedObject(), delegate)
}
