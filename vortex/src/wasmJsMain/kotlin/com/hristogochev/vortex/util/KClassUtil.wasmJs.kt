package com.hristogochev.vortex.util

import kotlin.reflect.KClass

public actual val KClass<*>.multiplatformName: String? get() = simpleName