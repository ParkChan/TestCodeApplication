package com.example.testcodeapplication.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcodeapplication.ui.intent.IntentInstallActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val fcmTokenLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getToken(): LiveData<String> = fcmTokenLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.d("CHAN >>> $exception")
    }

    //PlayStore가 설치되어 있지 않으면 Google Play services missing or without correct permission.
    //에러 발생
    fun checkFcmToken() {
        Thread {
            try {
                FirebaseMessaging.getInstance().token.addOnCompleteListener {
                    if (it.isComplete) {
                        try {
                            it.result?.run {
                                fcmTokenLiveData.value = this
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    fun startIntentInsatllActivity(context: Context) {
        val intent = Intent(context, IntentInstallActivity::class.java)
        context.startActivity(intent)
    }

    fun testViewModelScope() = viewModelScope.launch(exceptionHandler) {
        testViewModelScope1()
        testViewModelScope2()
        testViewModelScope3()
    }

    fun testViewModelScope1() = viewModelScope.launch(exceptionHandler) {
        delay(2000)
        println("CHAN >>> viewmodelScope 1")
    }

    fun testViewModelScope2() = viewModelScope.launch(exceptionHandler) {
        throw Exception()

//        앱정지
//        CoroutineScope(Dispatchers.IO).launch {
//            throw Exception()
//        }
    }
    fun testViewModelScope3() = viewModelScope.launch(exceptionHandler) {
        delay(2500)
        println("CHAN >>> viewmodelScope 3")
    }

}