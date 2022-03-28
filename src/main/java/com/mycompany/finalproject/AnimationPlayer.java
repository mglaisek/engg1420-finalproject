package com.mycompany.finalproject;

import java.lang.Integer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.Group;
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
            sc.nextLine();
            shapes = new Shape[numAnimations];
            group = new Group();
            for(int i=0; i<=numAnimations; i++) {
                if(sc.nextLine().equals("Line"))    {
                    Line li = new Line();
                    li.setStartX(Integer.parseInt(sc.nextLine().substring(7)));
                    li.setStartY(Integer.parseInt(sc.nextLine().substring(7)));
                    li.setEndX(Integer.parseInt(sc.nextLine().substring(5)));
                    li.setEndY(Integer.parseInt(sc.nextLine().substring(5)));
                    shapes[i] = li;
                }
                else if(sc.nextLine().equals("Rectangle"))   {
                    Rectangle r = new Rectangle();
                    r.setHeight(Integer.parseInt(sc.nextLine().substring(7)));
                    r.setWidth(Integer.parseInt(sc.nextLine().substring(6)));
                    r.setX(Integer.parseInt(sc.nextLine().substring(2)));
                    r.setY(Integer.parseInt(sc.nextLine().substring(2)));
                    shapes[i] = r;
                }
                else if(sc.nextLine().equals("Circle"))  {
                    Circle c = new Circle();
                    c.setRadius(Integer.parseInt(sc.nextLine().substring(2)));
                    c.setCenterX(Integer.parseInt(sc.nextLine().substring(2)));
                    c.setCenterY(Integer.parseInt(sc.nextLine().substring(2)));
                    shapes[i] = c;
                }
            }
        } catch (FileNotFoundException e)   {
            
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