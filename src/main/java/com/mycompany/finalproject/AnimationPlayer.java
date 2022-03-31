package com.mycompany.finalproject;

import java.lang.Integer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;

import javafx.util.Duration;
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
    private Group group;
    private Timeline timeline;
    
    public AnimationPlayer()    {
    }

    public void loadAnimationFromFile(String filename)   {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            String line;
            Duration time;
            String pline[];
            boolean readNext;
            
            numFrames = sc.nextInt();
            fps = sc.nextInt();
            numAnimations = sc.nextInt();
            
            sc.nextLine(); // skip the next pline of blank space
            group = new Group();
            timeline = new Timeline(fps);
            
            
            for(int i=0; i<=numAnimations; i++) {
                //<editor-fold defaultstate="collapsed" desc="Load shapes and animations">
                switch (sc.nextLine()) {
                    case "Line":
                        Line li = new Line();
                        
                        pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("startX")) {
                            li.setStartX(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default startx
                            li.setStartX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("startY")) {
                            li.setStartY(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default starty
                            li.setStartY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("endX")) {
                            li.setEndX(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default endx
                            li.setEndX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("endY")) {
                            li.setEndY(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default endy
                            li.setEndY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("color")) {
                            pline = pline[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                            li.setFill(co);
                            readNext = true;
                        }
                        else    { // default color
                            li.setFill(Color.BLACK);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("border")) {
                            li.setStrokeWidth(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default border
                            li.setStrokeWidth(1);
                            readNext = false;
                        }   
                        
                        // load animations
                        line = sc.nextLine();
                        while(!line.isBlank())  {
                            while(line.equals("effect"))    { // parses each effect until there are no more
                                line = sc.nextLine();
                                KeyFrame animation;
                                if(line.equals("Jump")) {
                                    int nX,nY;
                                    KeyValue setX;
                                    KeyValue setY;

                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    if(readNext)    {
                                        line = sc.nextLine();
                                        pline = line.split(": ",2);
                                    }
                                    if(pline[0].equals("x"))    {
                                        nX = Integer.parseInt(pline[1]);
                                        readNext = true;
                                    }
                                    else    { // default, keep original X
                                        nX = (int)li.getStartX();
                                        readNext = false;
                                    }

                                    if(readNext)    {
                                        line = sc.nextLine();
                                        pline = line.split(": ",2);
                                    }
                                    if(pline[0].equals("y"))    {
                                        nY = Integer.parseInt(pline[1]);
                                        readNext = true;
                                    }
                                    else    { // default, keep original Y
                                        nY = (int)li.getStartY();
                                        readNext = false;
                                    }

                                    setX = new KeyValue(li.startXProperty(), nX);
                                    setY = new KeyValue(li.startYProperty(), nY);
                                    animation = new KeyFrame(time,setX,setY);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("Show"))    {
                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    KeyValue show = new KeyValue(li.visibleProperty(), true);
                                    animation = new KeyFrame(time,show);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("Hide"))    {
                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    KeyValue hide = new KeyValue(li.visibleProperty(), false);
                                    animation = new KeyFrame(time,hide);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("ChangeColor")) {
                                    line = sc.nextLine();
                                    Color co;

                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    if(readNext)
                                        pline = sc.nextLine().split(": ",2);
                                    if(pline[0].equals("color")) {
                                        pline = pline[1].split(", ",3);
                                        co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                                        readNext = true;
                                    }
                                    else    { // default color
                                        co = Color.BLACK;
                                        readNext = false;
                                    }   
                                    KeyValue change = new KeyValue(li.fillProperty(), co);
                                    animation = new KeyFrame(time,change);
                                    timeline.getKeyFrames().add(animation);
                                }
                            }
                            line = sc.nextLine();
                        }
                        group.getChildren().add(li);
                        break;
                    case "Rectangle":
                        Rectangle r = new Rectangle();
                        
                        pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("length"))    {
                            r.setHeight(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default length
                            r.setHeight(1);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("width")) {
                            r.setWidth(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default width
                            r.setWidth(1);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("x")) {
                            r.setX(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default x
                            r.setX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("y")) {
                            r.setY(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default y
                            r.setY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("color")) {
                            pline = pline[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                            r.setFill(co);
                            readNext = true;
                        }
                        else    { // default color
                            r.setFill(Color.BLACK);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("border")) {
                            r.setStrokeWidth(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default border
                            r.setStrokeWidth(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("borderColor")) {
                            pline = pline[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                            r.setStroke(co);
                            readNext = true;
                        }
                        else    { // default bordercolor
                            r.setStroke(Color.BLACK);
                            readNext = false;
                        }   
                        
                        // load animations
                        line = sc.nextLine();
                        while(!line.isBlank())  {
                            while(line.equals("effect"))    { // parses each effect until there are no more
                                line = sc.nextLine();
                                KeyFrame animation;
                                if(line.equals("Jump")) {
                                    int nX,nY;
                                    KeyValue setX;
                                    KeyValue setY;

                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    if(readNext)    {
                                        line = sc.nextLine();
                                        pline = line.split(": ",2);
                                    }
                                    if(pline[0].equals("x"))    {
                                        nX = Integer.parseInt(pline[1]);
                                        readNext = true;
                                    }
                                    else    { // default, keep original X
                                        nX = (int)r.getX();
                                        readNext = false;
                                    }

                                    if(readNext)    {
                                        line = sc.nextLine();
                                        pline = line.split(": ",2);
                                    }
                                    if(pline[0].equals("y"))    {
                                        nY = Integer.parseInt(pline[1]);
                                        readNext = true;
                                    }
                                    else    { // default, keep original Y
                                        nY = (int)r.getY();
                                        readNext = false;
                                    }

                                    setX = new KeyValue(r.xProperty(), nX);
                                    setY = new KeyValue(r.yProperty(), nY);
                                    animation = new KeyFrame(time,setX,setY);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("Show"))    {
                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    KeyValue show = new KeyValue(r.visibleProperty(), true);
                                    animation = new KeyFrame(time,show);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("Hide"))    {
                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    KeyValue hide = new KeyValue(r.visibleProperty(), false);
                                    animation = new KeyFrame(time,hide);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("ChangeColor")) {
                                    line = sc.nextLine();
                                    Color co;

                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    if(readNext)
                                        pline = sc.nextLine().split(": ",2);
                                    if(pline[0].equals("color")) {
                                        pline = pline[1].split(", ",3);
                                        co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                                        readNext = true;
                                    }
                                    else    { // default color
                                        co = Color.BLACK;
                                        readNext = false;
                                    }   
                                    KeyValue change = new KeyValue(r.fillProperty(), co);
                                    animation = new KeyFrame(time,change);
                                    timeline.getKeyFrames().add(animation);
                                }
                            }
                            line = sc.nextLine();
                        }
                        group.getChildren().add(r);
                        
                        break;
                    case "Circle":
                        Circle c = new Circle();
                        
                        pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("r")) {
                            c.setRadius(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default radius
                            c.setRadius(1);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("x")) {
                            c.setCenterX(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default x
                            c.setCenterX(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("y")) {
                            c.setCenterY(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default y
                            c.setCenterY(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("color")) {
                            pline = pline[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                            c.setFill(co);
                            readNext = true;
                        }
                        else    { // default color
                            c.setFill(Color.BLACK);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("border"))    {
                            c.setStrokeWidth(Integer.parseInt(pline[1]));
                            readNext = true;
                        }
                        else    { // default border
                            c.setStrokeWidth(0);
                            readNext = false;
                        }   
                        
                        if(readNext)
                            pline = sc.nextLine().split(": ",2);
                        if(pline[0].equals("borderColor")) {
                            pline = pline[1].split(", ",3);
                            Color co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                            c.setStroke(co);
                            readNext = true;
                        }
                        else    { // default border color
                            c.setStroke(Color.BLACK);
                            readNext = false;
                        }   
                        
                        // load animations
                        line = sc.nextLine();
                        while(!line.isBlank())  {
                            while(line.equals("effect"))    { // parses each effect until there are no more
                                line = sc.nextLine();
                                KeyFrame animation;
                                if(line.equals("Jump")) {
                                    int nX,nY;
                                    KeyValue setX;
                                    KeyValue setY;

                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    if(readNext)    {
                                        line = sc.nextLine();
                                        pline = line.split(": ",2);
                                    }
                                    if(pline[0].equals("x"))    {
                                        nX = Integer.parseInt(pline[1]);
                                        readNext = true;
                                    }
                                    else    { // default, keep original X
                                        nX = (int)c.getCenterX();
                                        readNext = false;
                                    }

                                    if(readNext)    {
                                        line = sc.nextLine();
                                        pline = line.split(": ",2);
                                    }
                                    if(pline[0].equals("y"))    {
                                        nY = Integer.parseInt(pline[1]);
                                        readNext = true;
                                    }
                                    else    { // default, keep original Y
                                        nY = (int)c.getCenterY();
                                        readNext = false;
                                    }

                                    setX = new KeyValue(c.centerXProperty(), nX);
                                    setY = new KeyValue(c.centerYProperty(), nY);
                                    animation = new KeyFrame(time,setX,setY);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("Show"))    {
                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    KeyValue show = new KeyValue(c.visibleProperty(), true);
                                    animation = new KeyFrame(time,show);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("Hide"))    {
                                    line = sc.nextLine();
                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    KeyValue hide = new KeyValue(c.visibleProperty(), false);
                                    animation = new KeyFrame(time,hide);
                                    timeline.getKeyFrames().add(animation);
                                }
                                else if(line.equals("ChangeColor")) {
                                    line = sc.nextLine();
                                    Color co;

                                    pline = line.split(": ",2);
                                    if(pline[0].equals("start"))    {
                                        time = new Duration(Integer.parseInt(pline[1])*1000/fps); // converts the frame number into milliseconds
                                        readNext = true;
                                    }
                                    else    {
                                        time = new Duration(0);
                                        readNext = false;
                                    }

                                    if(readNext)
                                        pline = sc.nextLine().split(": ",2);
                                    if(pline[0].equals("color")) {
                                        pline = pline[1].split(", ",3);
                                        co = new Color(Integer.parseInt(pline[0]),Integer.parseInt(pline[1]),Integer.parseInt(pline[2]),255);
                                        readNext = true;
                                    }
                                    else    { // default color
                                        co = Color.BLACK;
                                        readNext = false;
                                    }   
                                    KeyValue change = new KeyValue(c.fillProperty(), co);
                                    animation = new KeyFrame(time,change);
                                    timeline.getKeyFrames().add(animation);
                                }
                            }
                            line = sc.nextLine();
                        }
                        group.getChildren().add(c);
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