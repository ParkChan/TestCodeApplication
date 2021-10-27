package com.example.testcodeapplication.coroutine

import com.example.testcodeapplication.getOrAwaitValue
import com.example.testcodeapplication.viewmodel.MyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * https://modelmaker.tistory.com/16
 */
@ExtendWith(InstantTaskExecutorExtension::class)
class TestCoroutineDispatcherExam1 {

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineRule = MainCoroutineRule()
        val viewModel: MyViewModel = MyViewModel()
    }

    @ExperimentalCoroutinesApi
    @BeforeEach
    fun setUp() {

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `라이브 데이터 테스트`() = mainCoroutineRule.runBlockingTest {
        println("Start")
        viewModel.setNewValue("foo")
        // Pass:
        assertEquals(viewModel.liveData1.getOrAwaitValue(), "foo")
        assertEquals(viewModel.liveData2.getOrAwaitValue(), "FOO")
        println("End")
    }

    @Test
    fun `StateFlow test`() = mainCoroutineRule.runBlockingTest {
        println("Start")
        viewModel.userName.collect { name ->
            println("receive >>> $name")
        }
        viewModel.setName("찬군입니다.")
        println("End")
    }

}