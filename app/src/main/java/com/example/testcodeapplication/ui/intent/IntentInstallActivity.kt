package com.example.testcodeapplication.ui.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.BuildConfig
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityIntentInstallApkBinding
import com.orhanobut.logger.Logger
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL


/**
 * https://developer.android.com/training/basics/intents/result?hl=ko
 * https://codechacha.com/ko/how-to-install-and-uninstall-app-in-android/
 */
class IntentInstallActivity : AppCompatActivity() {

    companion object {
        const val TAG = "IntentInstallActivity"
        const val ACTIVITY_RESULT_REQ_KEY = "REQUEST_INSTALL_PERMISSION"
        const val REQUEST_INSTALL_PERMISSION = 10
    }

    private lateinit var binding: ActivityIntentInstallApkBinding
    private val registerForActivityResult = registerForActivityResult()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intent_install_apk)
        binding.lifecycleOwner = this
        binding.btnInstall.setOnClickListener {
            if (packageManager.canRequestPackageInstalls()) {
                downloadWebLink()
            } else {
                val intent = Intent(
                    Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                    Uri.parse("package:$packageName")
                )
                registerForActivityResult.launch(intent)
            }
        }
    }

    private fun uninstallApp(packageName: String) {
        val packageURI = Uri.parse(packageName)
        val uninstallIntent = Intent(Intent.ACTION_DELETE, packageURI)
        startActivity(uninstallIntent)
    }

    private fun downloadWebLink() {
        object : Thread() {
            override fun run() {
                val url =
                    URL("http://j2enty.tistory.com/attachment/cfile24.uf@154AFA254CC9242B3CF889.apk")
                val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.doOutput = true
                httpURLConnection.connect()
                val outputPath = filesDir.absolutePath + "/download.apk"
                val fileOutputStream = FileOutputStream(outputPath)

                val inputStream = httpURLConnection.inputStream
                val totalSize = httpURLConnection.contentLength
                var downloadSize = 0

                val buffer = ByteArray(1024)
                var bufferLength = 0
                while ((inputStream.read(buffer).also { bufferLength = it }) > 0) {
                    fileOutputStream.write(buffer, 0, bufferLength)
                    downloadSize += bufferLength
                    //mProgressBar.setProgress(downloadedSize);
                }
                fileOutputStream.close()
                installApk(filesDir.absolutePath + "/" + "download.apk")
            }
        }.start()
    }

    private fun installApk(apkPath: String) {
        //val apkPath = filesDir.absolutePath + "/app.apk"
        val apkUri = FileProvider.getUriForFile(
            applicationContext,
            BuildConfig.APPLICATION_ID + ".fileprovider", File(apkPath)
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
        startActivity(intent)
    }

    private fun registerForActivityResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Logger.d("registerForActivityResult data $result")
            if (result.resultCode == Activity.RESULT_OK) {

            }
        }
    }
}

