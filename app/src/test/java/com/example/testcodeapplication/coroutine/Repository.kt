package com.example.testcodeapplication.coroutine

import androidx.lifecycle.liveData

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Repository {
    val liveData = liveData{
        emit(1)
        kotlinx.coroutines.delay(1_000)
        emit(2)
    }
}