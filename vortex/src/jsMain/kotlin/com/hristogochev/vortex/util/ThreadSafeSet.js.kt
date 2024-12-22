package com.hristogochev.vortex.util

import kotlinx.atomicfu.locks.synchronized
import kotlinx.atomicfu.locks.SynchronizedObject

internal actual fun <T> getThreadSafeSet(): ThreadSafeSet<T> {
    return JSThreadSafeSet()
}

internal class JSThreadSafeSet<T>(
    private val delegate: MutableSet<T>,
) : ThreadSafeSet<T>, MutableSet<T> {
    constructor() : this(delegate = mutableSetOf())

    private val syncObject = SynchronizedObject()

    override val size: Int
        get() = delegate.size

    override fun contains(element: T): Boolean {
        return synchronized(syncObject) { delegate.contains(element) }
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return synchronized(syncObject) { delegate.containsAll(elements) }
    }

    override fun isEmpty(): Boolean {
        return synchronized(syncObject) { delegate.isEmpty() }
    }

    override fun iterator(): MutableIterator<T> {
        return synchronized(syncObject) { delegate.iterator() }
    }

    override fun add(element: T): Boolean {
        return synchronized(syncObject) { delegate.add(element) }
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return synchronized(syncObject) { delegate.addAll(elements) }
    }

    override fun clear() {
        return synchronized(syncObject) { delegate.clear() }
    }

    override fun remove(element: T): Boolean {
        return synchronized(syncObject) { delegate.remove(element) }
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return synchronized(syncObject) { delegate.removeAll(elements) }
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return synchronized(syncObject) { delegate.retainAll(elements) }
    }
}