package com.example.charles.olympus;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.*;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public HttpSingleton http = new HttpSingleton();
    public String url = "http://olympus-cci219706483.codeanyapp.com:8000/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HttpSingleton http = new HttpSingleton();

        String result = null;
        try {
            result = new HttpSingleton().execute(url).get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        setText(result);
    }
    public void setText(String text)
    {
        TextView textView = findViewById(R.id.maintext);
        textView.setText(text);
    }



}
