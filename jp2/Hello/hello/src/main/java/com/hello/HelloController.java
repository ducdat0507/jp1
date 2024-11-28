package com.hello;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML Label labelTimerMajor;
    @FXML Label labelTimerMinor;

    boolean isRunning = true;
    double timer;
    Instant now;
    AnimationTimer updateLoop;

    public void updateTimerLabels() {
        String majorText = String.format("%02.0f'", Math.floor(timer % 60));
        labelTimerMajor.setText(majorText);

        String minorText = String.format("%03.0f", Math.floor(timer * 1000 % 1000));
        labelTimerMinor.setText(minorText);
    }

    public HelloController() {
        updateLoop = new AnimationTimer() {
            @Override
            public void handle(long _) {
                Instant n = Instant.now();
                timer += Duration.between(now, n).toNanos() / 1e9;
                now = n;
                updateTimerLabels();
            }
        };
        updateTimerLabels();
    }

    @FXML
    private void toggleStopwatch() throws IOException {
        if (isRunning) {
            now = Instant.now();
            updateLoop.start();
        } else {
            updateLoop.stop();
        }
        isRunning = !isRunning;
    }
}
