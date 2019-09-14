package com.photogallery.camil.photo_gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

public class Main2Activity extends AppCompatActivity {

    private VideoView videoView;
    private String intent_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.videoView = (VideoView) findViewById(R.id.video_record);
        String extra = getIntent().getStringExtra("video_url");
        intent_data = extra;
        this.videoView.setVideoPath(extra);
        this.videoView.start();
    }

    public void onReplayClick(View view){
        if(this.intent_data != null) {
            this.videoView.stopPlayback();
            this.videoView.setVideoPath(this.intent_data);
            this.videoView.start();
        }
    }

}
