package gremlins;

import processing.core.*;
import processing.data.*;
import java.util.*;
import java.io.*;

public class Fireball {
    private int x;
    private int y;
    public PImage sprite;
    private int speed = 4;
    int xVel = 0;
    int yVel = 0;
    public boolean attack;
    public boolean broke;

    public Fireball(int x, int y, PImage sprite) {
        this.x = App.p.getX();
        this.y = App.p.getY();
        this.sprite = App.fireball;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean left() {
        this.xVel = -speed;
        breakthing();
        return broke;
    }

    public boolean right() {
        this.xVel = speed;
        breakthing();
        return broke;
    }

    public boolean up() {
        this.yVel = -speed;
        breakthing();
        return broke;
    }

    public boolean down() {
        this.yVel = +speed;
        breakthing();
        return broke;
    }

    public void stop() {
        this.xVel = 0;
        this.yVel = 0;
    }

    public boolean breakthing() {
        int xaxis = (int) Math.floor(App.f.getY() / 20);
        int yaxis = (int) Math.floor(App.f.getX() / 20);
        if (App.Map.get(xaxis).get(yaxis).equals("B") || App.Map.get(xaxis).get(yaxis).equals("G")) {
            App.Map.get(xaxis).set(yaxis, "k");
            broke = true;
            App.f = new Fireball(10000, 10000, App.slime);
        }else if (App.Map.get(xaxis).get(yaxis).equals("X")){
            App.f = new Fireball(10000, 10000, App.slime);
        }
        System.out.println(App.f.broke);
        return broke;
    }

    public void draw(App app) {
        if (App.f.getX() + xVel < 20 || App.f.getX() + xVel + 20 > 700 || App.f.getY() + yVel < 20 || App.f.getY() + yVel + 20 > 700) {
            this.stop();
        }
        breakthing();
        app.image(sprite, getX(), getY(), 20, 20);
        x += xVel;
        y += yVel;
    }
}
