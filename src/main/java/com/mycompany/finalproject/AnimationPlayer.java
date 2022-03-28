package com.mycompany.finalproject;

import java.lang.Integer;

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
    Shape[] shapes;
    Group group;

    public AnimationPlayer()    {
    }

    public void loadAnimationFromFile(String filename)   {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            numFrames = sc.nextInt();
            fps = sc.nextInt();
            numAnimations = sc.nextInt();
            shapes = new Shape[numAnimations];
            group = new Group();
            for(int i=0; i<=numAnimations; i++) {
                if(sc.nextLine().equals("Line"))    {
                    shapes[i] = new Line();
                }
                else if(sc.nextLine().equals("Rectangle"))   {
                    shapes[i] = new Rectangle();
                }
                else if(sc.nextLine().equals("Circle"))  {
                    shapes[i] = new Circle();
                    shapes[i].setRadius(Integer.parseInt(sc.nextLine().substring(2)));
                }
            }
        }
    }
    
    public void run()    {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(group, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}