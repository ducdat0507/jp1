package com.hello;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML Label labelTimerMajor;
    @FXML Label labelTimerMinor;
    @FXML Button buttonPrimary;

    boolean isRunning = true;
    double timer;
    Instant now;
    AnimationTimer updateLoop;

    public void updateTime() {
        Instant n = Instant.now();
        timer += Duration.between(now, n).toNanos() / 1e9;
        now = n;
        updateTimerLabels();
    }

    public void updateTimerLabels() {
        String majorText = "";
        if (timer >= 3600) majorText += String.format("%02.0f:", Math.floor(timer / 3600));
        if (timer >= 60) majorText += String.format("%02.0f:", Math.floor(timer / 60 % 60));
        majorText += String.format("%02.0f.", Math.floor(timer % 60));
        labelTimerMajor.setText(majorText); 

        String minorText = String.format("%03.0f", Math.floor(timer * 1000 % 1000));
        labelTimerMinor.setText(minorText);
    }

    public void initialize() {
        updateTimerLabels();
    }

    public HelloController() {
        updateLoop = new AnimationTimer() {
            @Override
            public void handle(long $) {
                updateTime();
            }
        };
    }

    @FXML
    private void toggleStopwatch() throws IOException {
        if (isRunning) {
            now = Instant.now();
            updateLoop.start();
            buttonPrimary.setText("Pause");
        } else {
            updateLoop.stop();
            updateTime();
            buttonPrimary.setText("Start");
        }
        isRunning = !isRunning;
    }
}
