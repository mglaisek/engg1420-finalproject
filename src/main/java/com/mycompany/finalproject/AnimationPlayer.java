package com.mycompany.finalproject;

import java.lang.Integer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class AnimationPlayer extends Application {
    private int numFrames, fps, numAnimations;
    private Shape[] shapes;
    private Group group;
    private Timeline timeline;
    private AnimationTimer timer;
    
    public AnimationPlayer()    {
    }

    public void loadAnimationFromFile(String filename)   {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            String line[];
            boolean readNext;
            
            numFrames = sc.nextInt();
            fps = sc.nextInt();
            numAnimations = sc.nextInt();
            
            sc.nextLine(); // skip the next line of blank space
            
            shapes = new Shape[numAnimations];
            group = new Group();
            timeline = new Timeline(fps);
            
            //<editor-fold defaultstate="collapsed" desc="Load shapes to be animated">
            for(int i=0; i<=numAnimations; i++) {
                switch (sc.nextLine()) {
                    case "Line":
                        Line li = new Line();
                        
                        line = sc.nextLine().split(": ",2);
                        if(line[0].equals("startX")) {
                            li.setStartX(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default startx
                            li.setStartX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("startY")) {
                            li.setStartY(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default starty
                            li.setStartY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("endX")) {
                            li.setEndX(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default endx
                            li.setEndX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("endY")) {
                            li.setEndY(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default endy
                            li.setEndY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("color")) {
                            line = line[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),255);
                            li.setFill(co);
                            readNext = true;
                        }
                        else    { // default color
                            li.setFill(Color.BLACK);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("border")) {
                            li.setStrokeWidth(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default border
                            li.setStrokeWidth(1);
                            readNext = false;
                        }   shapes[i] = li;
                        break;
                    case "Rectangle":
                        Rectangle r = new Rectangle();
                        
                        line = sc.nextLine().split(": ",2);
                        if(line[0].equals("length"))    {
                            r.setHeight(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default length
                            r.setHeight(1);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("width")) {
                            r.setWidth(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default width
                            r.setWidth(1);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("x")) {
                            r.setX(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default x
                            r.setX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("y")) {
                            r.setY(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default y
                            r.setY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("color")) {
                            line = line[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),255);
                            r.setFill(co);
                            readNext = true;
                        }
                        else    { // default color
                            r.setFill(Color.BLACK);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("border")) {
                            r.setStrokeWidth(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default border
                            r.setStrokeWidth(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("borderColor")) {
                            line = line[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),255);
                            r.setStroke(co);
                            readNext = true;
                        }
                        else    { // default bordercolor
                            r.setStroke(Color.BLACK);
                            readNext = false;
                        }   shapes[i] = r;
                        break;
                    case "Circle":
                        Circle c = new Circle();
                        
                        line = sc.nextLine().split(": ",2);
                        if(line[0].equals("r")) {
                            c.setRadius(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default radius
                            c.setRadius(1);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("x")) {
                            c.setCenterX(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default x
                            c.setCenterX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("y")) {
                            c.setCenterY(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default y
                            c.setCenterY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("color")) {
                            line = line[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),255);
                            c.setFill(co);
                            readNext = true;
                        }
                        else    { // default color
                            c.setFill(Color.BLACK);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("border"))    {
                            c.setStrokeWidth(Integer.parseInt(line[1]));
                            readNext = true;
                        }
                        else    { // default border
                            c.setStrokeWidth(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            line = sc.nextLine().split(": ",2);
                        if(line[0].equals("borderColor")) {
                            line = line[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),255);
                            c.setStroke(co);
                            readNext = true;
                        }
                        else    { // default border color
                            c.setStroke(Color.BLACK);
                            readNext = false;
                        }   
                        
                        shapes[i] = c;
                        break;
                    default:
                        break;
                }
                //</editor-fold>
                
            
             
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