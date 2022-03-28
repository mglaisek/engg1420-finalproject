package com.mycompany.finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class AnimationPlayer extends Application {
    int numFrames, fps, numAnimations;

    public AnimationPlayer()    {
    }

    public void loadAnimationFromFile(String filename)   {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            numFrames = sc.nextInt();
            fps = sc.nextInt();
            numAnimations = sc.nextInt();
        }
    }
    
    public void run()    {
        launch();
    }

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        
        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}