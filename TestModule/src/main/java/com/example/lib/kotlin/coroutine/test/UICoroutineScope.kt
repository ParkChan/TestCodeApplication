package com.example.lib.kotlin.coroutine.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class UICoroutineScope(
        private val dispatchers: CoroutineContext = Dispatchers.Main
) : BaseCoroutineScope {

    override val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers + job

    override fun releaseCoroutine() {
        println("UICoroutineScope onRelease coroutine")
        job.cancel()
    }
}