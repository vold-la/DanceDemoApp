package com.noori.tes;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public  ArrayList<String> arrayList = new ArrayList<String>();
    public int size;
    public int isPlaying;
    MediaController mediaControls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView d1 = findViewById(R.id.d1);
        ImageView d2 = findViewById(R.id.d2);
        ImageView d3 = findViewById(R.id.d3);
        ImageView d4 = findViewById(R.id.d4);


        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add("android.resource://" + getPackageName() + "/" + R.raw.d1);
                size++;
                play();
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add("android.resource://" + getPackageName() + "/" + R.raw.d2);
                size++;play();
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add("android.resource://" + getPackageName() + "/" + R.raw.d3);
                size++;play();
            }
        });
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add("android.resource://" + getPackageName() + "/" + R.raw.d4);
                size++;play();
            }
        });
       // d1.setOnClickListener(this);
       // d2.setOnClickListener(this);
       // d3.setOnClickListener(this);
        // d4.setOnClickListener(this);
        }

    public void play() {
        if (isPlaying == 0) {
            isPlaying=1;
            final VideoView simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);
            mediaControls = new MediaController(MainActivity.this);
            mediaControls.setAnchorView(simpleVideoView);
            mediaControls.setVisibility(View.GONE);
            simpleVideoView.setVideoURI(Uri.parse(arrayList.get(0)));
            simpleVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    simpleVideoView.start();
                }
            });

            simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    size--;
                    if(size>0){
                        arrayList.remove(0);
                        simpleVideoView.setVideoURI(Uri.parse(arrayList.get(0)));
                        simpleVideoView.start();
                    }else{
                        if(isPlaying==1)
                            arrayList.remove(0);
                        isPlaying=0;
                        size=0;
                        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.standing));
                        simpleVideoView.start();
                    }
                }
            });
        }
    }
}
