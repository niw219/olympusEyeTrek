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
 * Created by charlesinwald on 2/22/18.
 */

public class HttpSingleton extends AsyncTask {
    OkHttpClient client = new OkHttpClient();
    String lastvalue = "empty";
    public String url = "http://olympus-cci219706483.codeanyapp.com:8000/test";

    @Override
    protected Object doInBackground(Object[] objects) {
        String response = request();
        Log.d("okhttp", response);
        this.lastvalue = response;
        Log.d("okhttp",lastvalue);
        return response;
    }
    @Nullable
    public String request() {
        String response = null;
        try {
            response = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try  {
            Call call = client.newCall(request);
            Response response = call.execute();
            return response.body().string();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
