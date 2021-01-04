package com.example.testcodeapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.databinding.ActivityMainBinding
import com.example.testcodeapplication.ui.intent.IntentInstallActivity
import com.example.testcodeapplication.ui.rx.RxJavaMainActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.btnRxkotlinTest.setOnClickListener {
            val intent = Intent(this, RxJavaMainActivity::class.java)
            startActivity(intent)
        }
        binding.btnIntentInstallTest.setOnClickListener{
            val intent = Intent(this, IntentInstallActivity::class.java)
            startActivity(intent)
        }
    }
}