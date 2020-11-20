package com.example.recorderbbr;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private Button play, stop, record;
    private MediaRecorder myRecorderBBR3;
    private String outputFile;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       play = (Button) findViewById(R.id.play);
       stop = (Button) findViewById(R.id.stop);
       record = (Button) findViewById(R.id.record);
       stop.setEnabled(false);
       play.setEnabled(true);
       outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
       myRecorderBBR3 = new MediaRecorder();
       myRecorderBBR3.setAudioSource(MediaRecorder.AudioSource.MIC);
       myRecorderBBR3.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
       myRecorderBBR3.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
       myRecorderBBR3.setOutputFile(outputFile);
       record.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   myRecorderBBR3.prepare();
                   myRecorderBBR3.start();
               } catch (IllegalStateException ise) {
                   // make something ...
               } catch (IOException ioe) {
                   // make something
               }
               record.setEnabled(true);
               stop.setEnabled(false);
               Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
           }
       });
       stop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myRecorderBBR3.stop();
               myRecorderBBR3.release();
               myRecorderBBR3 = null;
               record.setEnabled(true);
               stop.setEnabled(false);
               play.setEnabled(true);
               Toast.makeText(getApplicationContext(), "Audio Recorder successfully", Toast.LENGTH_LONG).show();
           }
       });

       play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               MediaPlayer mediaPlayer = new MediaPlayer();
               try {
                   mediaPlayer.setDataSource(outputFile);
                   mediaPlayer.prepare();
                   mediaPlayer.start();
                   Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
               } catch (Exception e)
               {
                   // make something
               }
           }
       });
           }
}