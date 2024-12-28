package io.github.hristogochev.vortex.util

internal fun <T> List<T>.takeUntilInclusive(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        result.add(item)
        if (predicate(item)) break
    }
    return result
}