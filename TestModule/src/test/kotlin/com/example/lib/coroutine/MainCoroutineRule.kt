package com.example.lib.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * https://wooooooak.github.io/android/2020/12/04/android-testing/
 * InstantTaskExecutorRule 설명에서도 나왔듯이 viewModelScope.launch가 사용하는
 * Dispatcher.main은 Android main looper를 사용하기 때문에 local test에서는 사용할 수 없다.
 * 이럴땐 kotlinx-coroutines-test에서 제공하는 TestCoroutineDispatcher를 사용하여 해결할 수 있다.
 * kotlinx-coroutines-test는 coroutine testing 전용으로 만들어진 라이브러리이다.
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher(),
) : BeforeEachCallback, AfterEachCallback,
    TestCoroutineScope by TestCoroutineScope(testDispatcher) {

    fun runBlockingTest(
        block: suspend TestCoroutineScope.() -> Unit
    ) = testDispatcher.runBlockingTest(block)

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

//@ExperimentalCoroutinesApi
//class MainCoroutineRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()):
//    TestWatcher(),
//    TestCoroutineScope by TestCoroutineScope(dispatcher) {
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(dispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        cleanupTestCoroutines()
//        Dispatchers.resetMain()
//    }
//}
//
//@ExperimentalCoroutinesApi
//fun MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
//    this.dispatcher.runBlockingTest {
//        block()
//    }