package com.xwh.vlcdemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

public class MainActivity extends Activity {

    private EditText mTextUrl;
    private SurfaceView mSurfaceView;

    private MediaPlayer mMediaPlayer;
    private LibVLC mLibVLC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextUrl = this.findViewById(R.id.text_url);
        mSurfaceView = this.findViewById(R.id.surfaceView);

        mSurfaceView.getHolder().setKeepScreenOn(true);

        initVLC();

        this.findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlay();
            }
        });
    }

    private void initVLC() {

        mLibVLC = new LibVLC(this);
        mMediaPlayer = new MediaPlayer(mLibVLC);
        IVLCVout ivlcVout = mMediaPlayer.getVLCVout();
        ivlcVout.setVideoView(mSurfaceView);
        ivlcVout.attachViews();

    }


    private void startPlay() {
        String url = mTextUrl.getText().toString();

        Media media = new Media(mLibVLC, Uri.parse(url));
        mMediaPlayer.setMedia(media);
        mMediaPlayer.play();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mMediaPlayer.stop();
    }
}
