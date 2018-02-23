package com.example.charles.olympus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public HttpSingleton http = new HttpSingleton();
    public String url = "http://olympus-cci219706483.codeanyapp.com:8000/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = findViewById(R.id.one);
        one.setOnClickListener(this); // calling onClick() method

        getRequest();
    }

    private void getRequest() {
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

    public void setText(String text) {
        TextView textView = findViewById(R.id.maintext);
        textView.setText(text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                Intent i = new Intent(this, VideoActivity.class);
                i.putExtra("Video","1");
                startActivity(i);
                break;


            default:
                break;
        }
    }
}
