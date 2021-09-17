package com.example.lib.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * https://wooooooak.github.io/android/2020/12/04/android-testing/
 * InstantTaskExecutorRule 설명에서도 나왔듯이 viewModelScope.launch가 사용하는
 * Dispatcher.main은 Android main looper를 사용하기 때문에 local test에서는 사용할 수 없다.
 * 이럴땐 kotlinx-coroutines-test에서 제공하는 TestCoroutineDispatcher를 사용하여 해결할 수 있다.
 * kotlinx-coroutines-test는 coroutine testing 전용으로 만들어진 라이브러리이다.
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()):
    TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}