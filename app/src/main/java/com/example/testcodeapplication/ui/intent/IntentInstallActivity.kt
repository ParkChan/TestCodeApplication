package com.example.testcodeapplication.ui.intent

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.BuildConfig
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityIntentInstallApkBinding
import java.io.File


//참조 : https://codechacha.com/ko/how-to-install-and-uninstall-app-in-android/
class IntentInstallActivity : AppCompatActivity() {

    companion object {
        const val TAG = "IntentInstallActivity"
        const val REQUEST_INSTALL_PERMISSION = 10
    }

    private lateinit var binding: ActivityIntentInstallApkBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intent_install_apk)
        binding.lifecycleOwner = this
        binding.btnInstall.setOnClickListener {
            if (packageManager.canRequestPackageInstalls()) {
                val filePath: String = Environment.getExternalStorageDirectory()
                    .toString() + "/Meeting/huiyi/download/"
                val fileName = "SoundTutorial.apk"
                val apkPath = filePath + fileName
                installApk(apkPath)
            } else {
                val intent = Intent(
                    Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                    Uri.parse("package:$packageName"))
                startActivityForResult(intent, REQUEST_INSTALL_PERMISSION)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_INSTALL_PERMISSION) {
        }
    }

    private fun installApk(apkPath: String) {
        //val apkPath = filesDir.absolutePath + "/app.apk"
        val apkUri =
            FileProvider.getUriForFile(
                applicationContext,
                BuildConfig.APPLICATION_ID + ".fileprovider", File(apkPath)
            )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
        startActivity(intent)
    }

    private fun uninstallApp() {
        val packageURI = Uri.parse("package:com.komorebi.memo")
        val uninstallIntent = Intent(Intent.ACTION_DELETE, packageURI)
        startActivity(uninstallIntent)
    }
}