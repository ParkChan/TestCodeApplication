package com.example.testcodeapplication.ui.intent

import androidx.appcompat.app.AppCompatActivity
import com.example.lib.kotlin.coroutine.test.BaseCoroutineScope
import com.example.lib.kotlin.coroutine.test.UICoroutineScope

//Delegation을 이용한 코루틴 생명주기 관리 테스트 코드
class CoroutineScopeActivity(
        scope: BaseCoroutineScope = UICoroutineScope()
) : AppCompatActivity(), BaseCoroutineScope by scope {

    override fun onDestroy() {
        super.onDestroy()
        releaseCoroutine()
    }

}