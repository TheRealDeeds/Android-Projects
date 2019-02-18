package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

// # 2019

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarTimer;
    TextView textViewTimer;
    Button buttonControlTimer;
    Boolean isCounterActive = false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textViewTimer = findViewById(R.id.textViewTimer);
        seekBarTimer = findViewById(R.id.seekBarTimer);
        buttonControlTimer = findViewById(R.id.buttonControlTimer);

        buttonControlTimer.setText("Start");
        seekBarTimer.setMax(600);
        seekBarTimer.setProgress(30);

        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress, textViewTimer);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void updateTimer(int secondsLeft, TextView textViewTimer) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;

        fixTimeText(minutes, seconds, textViewTimer);
    }

    private void fixTimeText(int minutes, int seconds, TextView textViewTimer) {
        if (minutes < 10) {
            if (seconds < 10) {
                textViewTimer.setText("0" + String.valueOf(minutes) + ":0" + String.valueOf(seconds));
            } else {
                textViewTimer.setText("0" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
            }
        } else if (seconds < 10) {
            textViewTimer.setText(String.valueOf(minutes) + ":0" + String.valueOf(seconds));
        }
    }

    public void clickControlTimer(View view) {

        if (!isCounterActive) {

            isCounterActive = true;
            seekBarTimer.setEnabled(false);
            buttonControlTimer.setText("Stop");

            countDownTimer = new CountDownTimer(seekBarTimer.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000, textViewTimer);
                }

                @Override
                public void onFinish() {
                    seekBarTimer.setEnabled(true);
                    buttonControlTimer.setText("Start");
                    isCounterActive = false;
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bell01);
                    mediaPlayer.start();
                }
            }.start();

        } else {

            textViewTimer.setText("00:30");
            seekBarTimer.setProgress(30);
            seekBarTimer.setEnabled(true);
            countDownTimer.cancel();
            buttonControlTimer.setText("Start");
            isCounterActive = false;

        }
    }
}
