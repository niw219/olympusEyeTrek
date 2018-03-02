package com.example.charles.olympus;

import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.*;

import static android.content.ContentValues.TAG;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;


/**
 * Created by Charles Inwald on 2/22/18.
 */

public class HttpSingleton extends AsyncTask {
    private OkHttpClient client = new OkHttpClient();


    @Override
    protected Object doInBackground(Object[] objects) {
        String response = request();
        Log.d("http", response);
        Log.d("http", response);
//        uploadFile();

        return response;
    }

    @Nullable
    private String request() {
        String response = null;
        String url = "http://olympus-cci219706483.codeanyapp.com:1337/admin";
        try {
            response = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

//    public static void uploadFile() {
////        Uri uri = Uri.parse("android.resource://"  + +  "/"+R.raw.videoplayback);
////        File image = new File("fileres/drawable/one.jpg");
////        File image = new File();
//
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody formBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", "sampleFile",
//                        RequestBody.create(MediaType.parse("image/jpg"), image))
//                .build();
//        String url = "http://olympus-cci219706483.codeanyapp.com:1337/upload";
//
//        Request request = new Request.Builder().url(url).post(formBody).build();
//
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (!response.isSuccessful()) try {
//            Log.d("FILE", String.valueOf(response));
//            throw new IOException("Unexpected code " + response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
