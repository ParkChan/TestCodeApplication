package com.example.testcodeapplication.ui.intent

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.*
import android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.BuildConfig
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityIntentInstallApkBinding
import com.google.android.material.snackbar.Snackbar
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import java.io.File


/**
 * https://developer.android.com/training/basics/intents/result?hl=ko
 * https://codechacha.com/ko/how-to-install-and-uninstall-app-in-android/
 */
class IntentInstallActivity : AppCompatActivity() {

    private companion object {
        // onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용됩니다.
        const val PERMISSIONS_REQUEST_CODE = 100

        // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
        val REQUIRED_PERMISSIONS = arrayOf(
                WRITE_EXTERNAL_STORAGE
        ) // 외부 저장소
    }

    private lateinit var binding: ActivityIntentInstallApkBinding
    private val registerForActivityResult = registerForActivityResult()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intent_install_apk)
        binding.lifecycleOwner = this
        checkPermission()
        //DownloadManager(applicationContext)

        binding.btnInstall.setOnClickListener {

            //시스템(privileged)앱 또는 플랫폼 key로 서명된 경우 바로 수행됨
            if (packageManager.canRequestPackageInstalls()) {
                downloadWebLink()
            } else {
                val intent = Intent(
                        ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                        Uri.parse("package:$packageName")
                )
                registerForActivityResult.launch(intent)
            }
        }
    }

    private fun uninstallApp(packageName: String) {
        try {
            val packageURI = Uri.parse("package:$packageName")
            val uninstallIntent = Intent(Intent.ACTION_DELETE, packageURI)
            uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(uninstallIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun downloadWebLink() {
        val url = "http://j2enty.tistory.com/attachment/cfile24.uf@154AFA254CC9242B3CF889.apk"
        val outputPath = filesDir.path + "/download"
        val fileName = "SoundTutorial.apk"

        val callbackToDownloadFile = CallbackToDownloadFile(outputPath, fileName)
        callbackToDownloadFile.setApkDownLoadListener(object :
                CallbackToDownloadFile.ApkDownLoadListener {
            override fun start() {
                runOnUiThread {
                    Toast.makeText(applicationContext, "apk download start", Toast.LENGTH_SHORT)
                            .show()
                }
            }

            override fun success() {
                MediaScannerConnection.scanFile(
                        applicationContext, arrayOf("$outputPath/$fileName"),
                        null,
                        null
                )
                runOnUiThread {
                    installApk(outputPath.plus("/").plus(fileName))
                    Timber.d("insatll path ${outputPath.plus("/").plus(fileName)}")
                    Toast.makeText(applicationContext, "apk download success", Toast.LENGTH_SHORT)
                            .show()
                }
            }

            override fun fail(message: String?) {
                runOnUiThread {
                    Toast.makeText(applicationContext, "apk download fail", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })

        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()
        client.newCall(request).enqueue(callbackToDownloadFile)
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
            Timber.d("registerForActivityResult data $result")
//            if (result.resultCode == Activity.RESULT_OK) {
//
//            }
        }
    }

    private fun checkPermission() {
        /**
         * 앱이 Android 11을 타겟팅하는 경우 WRITE_EXTERNAL_STORAGE 권한 및 WRITE_MEDIA_STORAGE
         * 독점 권한은 더 이상 추가 액세스를 제공하지 않습니다.
         * Android 10(API 수준 29) 이상을 실행하는 기기에서 앱은 저장소 관련 권한을 요청하지 않고도
         * MediaStore.Downloads와 같은 잘 정의된 미디어 컬렉션에 기여할 수 있습니다.
         * 앱에서 미디어 파일로 작업할 때 필요한 권한만 요청하는 방법을 자세히 알아보세요.
         */
        val writeExternalStoragePermission = ContextCompat.checkSelfPermission(
                this,
                WRITE_EXTERNAL_STORAGE
        )

        if (writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //start fun
            Timber.d("checkPermission WRITE_EXTERNAL_STORAGE granted")
        } else {
            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this, REQUIRED_PERMISSIONS[0]
                    )
            ) {
                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Snackbar.make(
                        binding.root,
                        "앱을 다운로드하여 설치하려면 외부 저장소 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE
                ).setAction("확인") { // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                    ActivityCompat.requestPermissions(
                            this@IntentInstallActivity, REQUIRED_PERMISSIONS,
                            PERMISSIONS_REQUEST_CODE
                    )
                }.show()
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(
                        this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            var checkResult = true

            // 모든 퍼미션을 허용했는지 체크합니다.
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }
            if (checkResult) {
                // 모든 퍼미션을 허용했다면 시작합니다.

            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                REQUIRED_PERMISSIONS[0]
                        )
                        || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                REQUIRED_PERMISSIONS[1]
                        )
                ) {
                    // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(
                            binding.root, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE
                    ).setAction(
                            "확인"
                    ) { finish() }.show()
                } else {
                    // “다시 묻지 않음”을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(
                            binding.root, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE
                    ).setAction(
                            "확인"
                    ) { finish() }.show()
                }
            }
        }
    }
}

