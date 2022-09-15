package com.example.lib.basic

import org.junit.jupiter.api.Test

sealed class NetworkState
object Success : NetworkState()
object Failure : NetworkState()
object Error : NetworkState()
//object InProgress : NetworkState()   //Loading 을 추가하면 컴파일러가 에러 또는 Warning 표시를 하여줌

abstract class NetworkState2
object Success2 : NetworkState2()
object Failure2 : NetworkState2()
object Error2 : NetworkState2()

/**
 * Sealed Class 에 대해서 알아봅니다.
 * Sealed class 의 특징
 * 클래스 앞에 sealed keyword를 붙여 정의합니다.
 * Sealed class 는 abstract 클래스로, 객체로 생성할 수 없습니다.
 * Sealed class 의 생성자는 private 입니다. public으로 설정할 수 없습니다.
 * Sealed class 와 그 하위 클래스는 동일한 파일에 정의되어야 합니다. 서로 다른 파일에서 정의할 수 없습니다.
 * 하위 클래스는 class, data class, object class 로 정의할 수 있습니다.
 */
class SealedClassTest {
    @Test
    fun `sealed class 를 사용하면 컴파일러가 NetworkState 자식클래스 항목들을 알고 있어서 else 문이 불필요`() {
        fun action(state: NetworkState): String {
            return when (state) {
                is Success -> "Success"
                is Failure -> "Failure"
                is Error -> "Error"
            }
        }

        fun action2(state: NetworkState) {
            when (state) {
                is Success -> "Success"
                is Failure -> "Failure"
                is Error -> "Error"
            }
        }
    }

    @Test
    fun `abstract class 를 사용하면 컴파일러가 NetworkState 자식클래스 항목들을 알 수 없으므로 else 문이 필요`() {
        fun action(state: NetworkState2): String {
            return when (state) {
                is Success2 -> "Success2"
                is Error2 -> "Error2"
                is Failure2 -> "Failure2"
                else -> "내가 필요하단 말이지..."
            }
        }
    }
}