package com.example.lib.kotlin.coroutine.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface BaseCoroutineScope : CoroutineScope {
    val job: Job

    /**
     * Coroutine job cancel
     */
    fun releaseCoroutine()
}