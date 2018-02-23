package com.example.charles.olympus;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.ResultReceiver;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {
    MediaSessionCompat mMediaSession;
    PlaybackStateCompat.Builder mStateBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String url = "https://www.rmp-streaming.com/media/bbb-360p.mp4";
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

//        // Create a MediaSessionCompat
//        mMediaSession = new MediaSessionCompat(this, "Video Player");
//
//        // Enable callbacks from MediaButtons and TransportControls
//        mMediaSession.setFlags(
//                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
//                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
//
//        // Do not let MediaButtons restart the player when the app is not visible
//        mMediaSession.setMediaButtonReceiver(null);
//
//        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player
//        mStateBuilder = new PlaybackStateCompat.Builder()
//                .setActions(
//                        PlaybackStateCompat.ACTION_PLAY |
//                                PlaybackStateCompat.ACTION_PLAY_PAUSE);
//        mMediaSession.setPlaybackState(mStateBuilder.build());
//
//        // MySessionCallback has methods that handle callbacks from a media controller
//        mMediaSession.setCallback(new MediaSessionCompat.Callback() {
//            @Override
//            public void onCommand(String command, Bundle extras, ResultReceiver cb) {
//                super.onCommand(command, extras, cb);
//                switch (command)
//                {
//
//                }
//            }
//        });
//
//        // Create a MediaControllerCompat
//        MediaControllerCompat mediaController =
//                new MediaControllerCompat(this, mMediaSession);
//
//        MediaControllerCompat.setMediaController(this, mediaController);
    }
}

