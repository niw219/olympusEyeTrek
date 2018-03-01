package com.example.charles.olympus;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.*;


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
        uploadFile();
        return response;
    }

    @Nullable
    private String request() {
        String response = null;
        String url = "http://olympus-cci219706483.codeanyapp.com:8000/test";
        try {
            response = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void uploadFile() {
        File image = new File("drawable/one.jpg");
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "one.jpg",
                        RequestBody.create(MediaType.parse("image/jpg"), image))
                .build();
        String url = "http://olympus-cci219706483.codeanyapp.com:8000/test";

        Request request = new Request.Builder().url(url).post(formBody).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) try {
            throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
