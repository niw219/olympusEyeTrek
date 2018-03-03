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
import static android.media.MediaCodec.MetricsConstants.MIME_TYPE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;


/**
 * Created by Charles Inwald on 2/22/18.
 */

public class HttpSingleton extends AsyncTask {
    private OkHttpClient client = new OkHttpClient();


    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d("RUN","doInBackground method");
//        String response = request();
//        Log.d("http", response);
//        Log.d("http", response);
//        uploadFile();
        String result = uploadFile((File) objects[0]);
        return result;
    }

    @Nullable
    private String request() {
        Log.d("RUN","Request method");

        String response = null;
        String url = "http://olympus-cci219706483.codeanyapp.com:1337/admin";
        try {
            response = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    public static String uploadFile(File image) {
//        Uri uri = Uri.parse("android.resource://"  + +  "/"+R.raw.videoplayback);
//        File image = new File("fileres/drawable/one.jpg");
//        File image = new File();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        //        RequestBody formBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", "sampleFile",
//                        RequestBody.create(MediaType.parse("image/jpg"),image))
//                .build();
//        MediaType MEDIA_TYPE = MediaType.parse(MIME_TYPE);
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "sampleFile", RequestBody.create(mediaType, image));
        RequestBody formBody = builder.build();

        String url = "http://olympus-cci219706483.codeanyapp.com:1337/upload";

//        Request request = new Request.Builder().url(url).post(formBody).build();
//        Log.d("IMAGE",image.toString());
//        Log.d("REQUEST", String.valueOf(request));
//        Log.d("REQUEST BODY", String.valueOf(formBody));
//        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
//        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sampleFile\"; filename=\"IMG_0178.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("http://olympus-cci219706483.codeanyapp.com:1337/upload")
                .post(formBody)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "9bf6a0ef-87f2-def9-0ebb-7a0ff4e6732d")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = null;
        try {
            response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) try {
            Log.d("FILE", String.valueOf(response));
            throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unreadable";
    }



    private String run(String url) throws IOException {
        Log.d("RUN","Run method");
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
