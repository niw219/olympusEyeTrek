package com.example.charles.olympus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    public HttpSingleton http = new HttpSingleton();
    public String url = "http://olympus-cci219706483.codeanyapp.com:8000/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
