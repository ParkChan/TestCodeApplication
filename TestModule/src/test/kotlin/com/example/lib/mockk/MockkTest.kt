package com.example.lib.mockk

import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class MockkTest {

    data class Sample(val value: String) {
        fun getString() = value + "Test"
    }

    @Test
    fun test() {
        val sample = mockk<Sample> {
            every { value } returns "sample"
        }
        val result = sample.getString()
        assert (result == "sampleTest")
    }
}

