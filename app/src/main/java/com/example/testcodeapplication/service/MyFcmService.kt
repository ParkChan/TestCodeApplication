package com.example.testcodeapplication.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFcmService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("onNewToken >>> $token")
        //sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remotemessage: RemoteMessage) {
        super.onMessageReceived(remotemessage)
        Timber.d("onMessageReceived >>> $remotemessage")
    }

    override fun onMessageSent(p0: String) {
        super.onMessageSent(p0)
    }

    override fun onSendError(p0: String, p1: Exception) {
        super.onSendError(p0, p1)
    }
}