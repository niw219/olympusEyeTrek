package com.example.charles.olympus;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Charles Inwald on 2/22/18.
 */

public class HttpSingleton extends AsyncTask {
    private OkHttpClient client = new OkHttpClient();


    @Override
    protected Object doInBackground(Object[] objects) {
        String response = request();
        Log.d("okhttp", response);
        Log.d("okhttp", response);
        return response;
    }

    @Nullable
    private String request() {
        String response = null;
        try {
            String url = "http://olympus-cci219706483.codeanyapp.com:8000/test";
            response = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
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
