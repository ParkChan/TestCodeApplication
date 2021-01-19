package com.example.testcodeapplication.ui.intent

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class BaseIntentActivity : AppCompatActivity() {

    inline fun <reified I : Activity> launch(resultLauncher: ActivityResultLauncher<Intent>) {
        resultLauncher.launch(Intent(applicationContext, I::class.java).putExtra("input", 100))
    }

    fun registerForActivityResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            // 아래는 결과 처리에 따라 원하는 대로...
            if (result?.resultCode == Activity.RESULT_OK) {
//                val result = result.data?.getStringExtra("result")
            }
        }
    }

}