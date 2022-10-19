package gremlins;
import processing.core.*;
import processing.data.*;
import java.util.*;
import java.io.*;


public class Slime {

    private int x;
    private int y;
    public PImage sprite;
    private int speed = 4;
    private int xVel = 0;
    private int yVel = 0;
    public boolean attack;

    public Slime(int x,int y,PImage sprite){
        this.x = App.p.getX();
        this.y = App.p.getY();
        this.sprite = App.slime;
    }

    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void left() {
        this.xVel = -speed;
    }

    public void right() {
        this.xVel = speed;
    }

    public void up(){
        this.yVel = -speed;
    }
    public void down(){
        this.yVel = +speed;
    }

    public void stop() {
        this.xVel = 0;
        this.yVel = 0;
    }
    

    
    public void draw(App app) {
        app.image(sprite,getX(),getY(),20, 20);
        x += xVel;
        y += yVel;
    }
}