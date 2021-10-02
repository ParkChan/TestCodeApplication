package com.example.lib.mockk

import io.mockk.*
import org.junit.jupiter.api.Test

/*
    참조 URL : https://leveloper.tistory.com/199
 */
class MockkTest {

    class SampleViewModel(private val repository: SampleRepository) {
        fun insert(sample: Sample) {
            repository.insert(sample)
        }
    }

    interface SampleRepository {
        fun insert(sample: Sample)
    }

    data class Sample(val value: String)

    @Test
    fun `sampleViewModelTest01 every블록을 사용하지 않으면 오류 발생`() {
        val sample = mockk<Sample>()
        val repository: SampleRepository = mockk()
        val viewModel = SampleViewModel(repository)

        every { repository.insert(sample) } just Runs

        viewModel.insert(sample)
        verify { repository.insert(sample) }
    }

    @Test
    fun `sampleViewModelTest every 대신 relaxed = true 를 사용`() {
        val sample = mockk<Sample>()
        val repository: SampleRepository = mockk(relaxed = true)
        val viewModel = SampleViewModel(repository)

        viewModel.insert(sample)
        verify { repository.insert(sample) }
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
    fun orderServiceTest() {
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


