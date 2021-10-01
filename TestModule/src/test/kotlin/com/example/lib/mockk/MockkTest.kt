package com.example.lib.mockk

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class MockkTest {

    data class Sample(val value: String) {
        fun getString() = value
    }

    @Test
    fun test() {
        val sample = mockk<Sample> {
            every { value } returns "sample"
        }
        val result = sample.getString()
        assert(result === "sampleTest")
    }

    interface PaymentService {
        fun pay(): PayResult
    }

    data class PayResult(
        val codes: List<String>
    )

    class OrderService(
        private val paymentService: PaymentService
    ) {
        fun order() {
            paymentService.pay()
        }
    }

    @Test
    fun mockTest2() {
        // paymentService mock 객체 생성
        val paymentService = mockk<PaymentService>()
        // 생성된 mock을 이용해서 orderService 객체 생성
        val orderService = OrderService(paymentService)

        // pay() 실행시 mock 객체 return
        every { paymentService.pay() } returns mockk()

        // order 실행
        orderService.order()

        // pay() 메소드를 실행 했는지 호출(행위) 검증
        verify { paymentService.pay() }
    }

}

