package com.example.testcodeapplication.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcodeapplication.ui.intent.IntentInstallActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainViewModel : ViewModel() {

    private val fcmTokenLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getToken(): LiveData<String> = fcmTokenLiveData

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
}