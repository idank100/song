package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    Switch sw;
    ImageView iv;
    SeekBar sb;
    MediaPlayer mp;
    AudioManager am;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(this);
        iv = findViewById(R.id.imageView2);
        sb = findViewById(R.id.seekbar);
        sb.setProgress(100);
        sb.setOnSeekBarChangeListener(this);
        mp = MediaPlayer.create(this,R.raw.song);
        mp.start();
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(max);
        sb.setProgress(max/2);
        am.setStreamVolume(AudioManager.STREAM_MUSIC,max/2,0);
        sb.setOnSeekBarChangeListener(this);

    }

    @Override
    protected void onPause(){
        super.onPause();
        mp.release();
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(b)
        {
            iv.setVisibility(View.VISIBLE);
            mp.start();
        }
        else
        {
            iv.setVisibility(View.GONE);
            mp.pause();
        }
        

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        float alpha = (float)i/10;
        iv.setAlpha(alpha);
        am.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}