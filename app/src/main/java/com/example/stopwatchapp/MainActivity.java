package com.example.stopwatchapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;

    private final Handler handler = new Handler();
    private int seconds = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        tvTimer = findViewById(R.id.tv_timer);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnPause = findViewById(R.id.btn_pause);
        Button btnStop = findViewById(R.id.btn_stop);

        // Start button listener
        btnStart.setOnClickListener(v -> {
            isRunning = true;
            runTimer();
        });

        // Pause button listener
        btnPause.setOnClickListener(v -> isRunning = false);

        // Stop button listener
        btnStop.setOnClickListener(v -> {
            isRunning = false;
            seconds = 0;
            updateTimerDisplay();
        });
    }

    private void runTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    seconds++;
                    updateTimerDisplay();
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void updateTimerDisplay() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        tvTimer.setText(time);
    }
}
