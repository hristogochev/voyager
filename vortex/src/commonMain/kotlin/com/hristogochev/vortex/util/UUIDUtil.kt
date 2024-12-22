package com.hristogochev.vortex.util

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
internal fun randomUuid(): String = Uuid.random().toString()
