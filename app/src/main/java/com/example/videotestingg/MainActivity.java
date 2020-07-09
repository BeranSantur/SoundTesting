package com.example.videotestingg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    //declaration
    SeekBar valueBar;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    SeekBar volumeBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //initilazation
        valueBar=findViewById(R.id.volumeBar);
        audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.sound);
        volumeBar2=findViewById(R.id.volumeBar2);

        mediaPlayer.start();
        //get the volume from the devices
        int maxValueOnDevice=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentValueOnDevice=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        valueBar.setMax(maxValueOnDevice);
        valueBar.setProgress(currentValueOnDevice);

        valueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        int maxVolumeOnMusic=mediaPlayer.getDuration();
        volumeBar2.setMax(maxValueOnDevice);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                volumeBar2.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,100);




        volumeBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

}
