package com.example.testcodeapplication.ui.intent;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.example.testcodeapplication.BuildConfig;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 프로세스 명령어를 통한 접근은 루트 권한을 필요로 한다.
 * Error >>> setgid: Operation not permitted
 * Cannot run program "su": error=13, Permission denied
 * <uses-permission android:name="android.permission.ACCESS_SUPERUSER" />
 */
public class InstallUtil {


    /**
     * 正常安装
     *
     * @param file
     * @param context
     */
    public static void installApk(Context context, File file) {
        //更新包文件
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= 24) { // Android7.0及以上版本 Log.d("-->最新apk下载完毕","Android N及以上版本");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            //参数二:应用包名+".fileProvider"(和步骤二中的Manifest文件中的provider节点下的authorities对应)
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            // Android7.0以下版本 Log.d("-->最新apk下载完毕","Android N以下版本");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static boolean installSilent1(String apkPath) {
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        try {
//            process = new ProcessBuilder("pm", "install", "-i", "com.iflytek.huiyisystem", "-r", apkPath).start();
            //process = new ProcessBuilder("pm", "install", "-i", "com.example.testcodeapplication", "-r", apkPath).start();
            process = new ProcessBuilder("pm", "install", "-r", apkPath).start();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        Logger.e("error msg >>>", "" + errorMsg.toString());
        Logger.d("success msg >>>"  + " " + successMsg.toString());
        //如果含有“success”单词则认为安装成功
        return successMsg.toString().equalsIgnoreCase("success");

    }

        /**
         * install slient
         *
         * @param filePath
         * @return 0 means normal, 1 means file not exist, 2 means other exception error
         */
    public static int installSilent2(String filePath) {
        Log.d("installSilent", "installSilent " + "start");
        File file = new File(filePath);
        if (filePath == null || filePath.length() == 0 || file == null || file.length() <= 0 || !file.exists() || !file.isFile()) {
            Log.d("installSilent", "installSilent " + "return 1");
            return 1;
        }

        String[] args = {"pm", "install", "-r", filePath};
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        int result;
        try {
            process = processBuilder.start();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }

        // TODO should add memory is not enough here
        if (successMsg.toString().contains("Success") || successMsg.toString().contains("success")) {
            result = 0;
        } else {
            result = 2;
        }
        Log.d("test-test", "successMsg:" + successMsg + ", ErrorMsg:" + errorMsg);
        return result;
    }

    /**
     * 静默卸载
     *
     * @param context
     * @param pkg
     */
    public static void unInstall(Context context, String pkg) {

        Intent intent = new Intent();

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent sender = PendingIntent.getActivity(context, 0, intent, 0);

        PackageInstaller mPackageInstaller = context.getPackageManager().getPackageInstaller();

        mPackageInstaller.uninstall(pkg, sender.getIntentSender());// 卸载APK

    }

    public static boolean rootInstall(String strApkPath) {
        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorReader = null;
        try {
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            String command = "pm install -r " + strApkPath + "\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = errorReader.readLine()) != null) {
                sb.append(line);
            }
            String output = sb.toString();
            Log.d("Installer", "install msg is " + output);
            if (!output.contains("Failure")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (errorReader != null) {
                    errorReader.close();
                }
            } catch (IOException e) {

            }
        }
        return false;
    }

}

