package com.example.testcodeapplication.coroutine

import com.example.testcodeapplication.getOrAwaitValue
import com.example.testcodeapplication.viewmodel.MyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
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
    @DisplayName("라이브 데이터 테스트 입니다")
    fun `라이브 데이터 테스트`() = mainCoroutineRule.runBlockingTest {
        println("Start")
        viewModel.setNewValue("foo")
        // Pass:
        assertEquals(viewModel.liveData1.getOrAwaitValue(), "foo")
        assertEquals(viewModel.liveData2.getOrAwaitValue(), "FOO")
        println("End")
    }
}