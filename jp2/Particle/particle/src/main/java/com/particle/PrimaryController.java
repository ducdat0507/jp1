package com.particle;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PrimaryController {

    @FXML Canvas mainCanvas;
    GraphicsContext mainContext;
    
    AnimationTimer updateLoop;

    public void initialize() {
        mainContext = mainCanvas.getGraphicsContext2D();
        updateLoop = new AnimationTimer() {
            @Override
            public void handle(long $) {
                update();
            }
        };
        updateLoop.start();
    }

    private void update() {
        double width = mainCanvas.getLayoutBounds().getWidth();
        double height = mainCanvas.getLayoutBounds().getHeight();

        mainCanvas.setWidth(width);
        mainCanvas.setHeight(height);

        mainContext.setFill(Color.BLACK);
        mainContext.fillRect(0, 0, width, height);

        mainContext.setFill(Color.WHITE);
        mainContext.fillText("Hello world", 10, 10);
    }

}
