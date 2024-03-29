package com.example.testcodeapplication.ui.intent;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import timber.log.Timber;

/**
 * 코틀린 코드로 이관 필요
 */
public class CallbackToDownloadFile implements Callback {

    private final File directory;
    private final File fileToBeDownloaded;

    private ApkDownLoadListener apkDownLoadListener;

    public CallbackToDownloadFile(String directory, String fileName) {
        this.directory = new File(directory);
        this.fileToBeDownloaded = new File(this.directory.getAbsolutePath() + "/" + fileName);
        downloadStart();
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        downloadFail("파일을 다운로드할 수 없습니다. 인터넷 연결을 확인하세요.");
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (!directory.exists()) {
            boolean isMakeDir = directory.mkdirs();
            if(isMakeDir){
                Timber.d("make directory success" + directory.getAbsolutePath());
            }
        }

        if (fileToBeDownloaded.exists()) {
            boolean isDelete = fileToBeDownloaded.delete();
            if(isDelete){
                Timber.d("delete file success " + fileToBeDownloaded.getAbsolutePath());
            }
        }

        try {
            fileToBeDownloaded.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            downloadFail("다운로드 파일을 생성할 수 없습니다.");
            return;
        }

        InputStream is = response.body().byteStream();
        OutputStream os = new FileOutputStream(this.fileToBeDownloaded);

        final int BUFFER_SIZE = 2048;
        byte[] data = new byte[BUFFER_SIZE];

        int count;

        while ((count = is.read(data)) != -1) {
            os.write(data, 0, count);
        }

        os.flush();
        os.close();
        is.close();
        downloadSuccess();
    }

    private void downloadStart() {
        if (apkDownLoadListener != null) {
            apkDownLoadListener.start();
        }
    }

    private void downloadSuccess() {
        if (apkDownLoadListener != null) {
            apkDownLoadListener.success();
        }
    }

    private void downloadFail(String message) {
        if (apkDownLoadListener != null) {
            apkDownLoadListener.fail(message);
        }
    }

    public interface ApkDownLoadListener {
        void start();
        void success();
        void fail(String message);
    }

    public void setApkDownLoadListener(ApkDownLoadListener apkDownLoadListener) {
        this.apkDownLoadListener = apkDownLoadListener;
    }
}


