package com.example.charles.olympus;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by haitongli on 3/30/18.
 */

public class VideoActivity2 extends Activity {

    private boolean mResumed = false;
    private boolean mFocused = false;
    private boolean mControlResumed = false;
    private VideoView videoView = null;
    private int stopPosition = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        setContentView(R.layout.activity_video);

        videoView =(VideoView)findViewById(R.id.videoView2);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"+R.raw.videoplayback);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mResumed = false;
        if (mControlResumed) {
            if (null != videoView)
                videoView.pause();
            stopPosition = videoView.getCurrentPosition();
            mControlResumed = false;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mResumed = true;
        if (mFocused && mResumed && !mControlResumed) {
            if(null != videoView) {
                //videoView.resume();
                videoView.seekTo(stopPosition);
                videoView.start();
            }
            mControlResumed = true;
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mFocused = hasFocus;
        if (mFocused && mResumed && !mControlResumed) {
            if (null != videoView) {
                //videoView.resume();
                videoView.seekTo(stopPosition);
                videoView.start();
            }
            mControlResumed = true;
        }
    }
}