package gremlins;
import gremlins.App;
import processing.core.*;
import processing.data.*;
import java.util.*;
import java.io.*;


public class Enemy{

    private static int x;
    private static int x1;
    private static int y;
    private static int y1;
    public PImage sprite = App.player_Right;
    private int speed = 2;
    private int xVel = 0;
    private int yVel = 0;
    public static ArrayList<ArrayList<Integer>> Enemyposition = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<Boolean> movement1 = new  ArrayList<Boolean>();
    public boolean Moveup;
    public boolean Moveright;
    public boolean Movedown;
    public boolean Moveleft;
    public boolean Dead;




    public Enemy(int y,int x,PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public static ArrayList<ArrayList<Integer>> getposition(){
        /*for (int i = 0; i < 4; i++) {
            ArrayList<Integer> smallList = new ArrayList<Integer>();
            smallList.add(0);
            smallList.add(0);
            Enemyposition.add(smallList);
        }*/

        int x = 0;
        while(x < App.Map.size()){
            int y = 0;
            while(y < App.lsForCounter.get(x)){
                if (App.Map.get(x).get(y).equals("G")){
                    ArrayList<Integer> smallList = new ArrayList<Integer>();
                    smallList.add(y);
                    smallList.add(x);
                    Enemyposition.add(smallList);
                }
                y += 1;
            }
            x += 1;
        } 
        return Enemyposition;
    }


    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    
    public void left() {
        this.xVel = -speed;
        sprite = App.player_Left;
    }

    public void right() {
        this.xVel = speed;
        sprite = App.player_Right;
    }

    public void up(){
        this.yVel = -speed;
        sprite = App.player_Up;
    }
    public void down(){
        this.yVel = +speed;
        sprite = App.player_Down;
    }

    public void stop() {
        this.xVel = 0;
        this.yVel = 0;
    }
    public boolean[] checkposition(){

        boolean[] movement = new boolean[4];
        if(Enemy.getY() < App.Map.size()-1){
            if (App.Map.get(Enemy.getX()).get(Enemy.getY()+1).equals(" ") || App.Map.get(Enemy.getX()).get(Enemy.getY()+1).equals("G")){
                Moveright = true;
            }else{
                Moveright = false;
            }
        }else{
            Moveright = false;
        }
        if(Enemy.getY() > 1){
            if (App.Map.get(Enemy.getX()).get(Enemy.getY()-1).equals(" " )|| App.Map.get(Enemy.getX()).get(Enemy.getY()-1).equals("G")){
                Moveleft = true;
            }else{
                Moveleft = false;
            }
        }else{
            Moveleft = false;
        }
        if(Enemy.getX() > 1){
            if(App.Map.get(Enemy.getX()-1).get(Enemy.getY()).equals(" ")||App.Map.get(Enemy.getX()-1).get(Enemy.getY()).equals("G")){
                Moveup =true;
            }else{
                Moveup = false;
            }
        }else{
            Moveup = false;
        }
        if(Enemy.getX() < App.Map.get(Enemy.getY()).size()-1){
            if(App.Map.get(Enemy.getX()+1).get(Enemy.getY()).equals(" ")|| App.Map.get(Enemy.getX()+1).get(Enemy.getY()).equals("G")){
                Movedown =true;
            }else{
                Movedown = false;
            }
        }else{
            Movedown = false;
        }

        movement[0] = Moveup;
        movement[1] = Movedown;
        movement[2] = Moveright;
        movement[3] = Moveleft;


        return movement;
    }


    public void move(){
        int i2 = 0;
        String[] arry1 = {"MoveUp","MoveDown","Moveright","Moveleft"};
        ArrayList<String> arry = new ArrayList<String>();
        while(i2 < checkposition().length){
            if (checkposition()[0] == true){
                arry.set(i2, arry1[i2]);
                    i2 += 1;
            }else{
                i2 += 1;
            }
        }
        
        
        int i = (int)(Math.random()*arry.size());
        String s = arry.get(i);
        if(s.equals("MoveUp")){
            up();
        }else if(s.equals("MoveDown")){
            down();
        }else if(s.equals("Moveright")){
            right();
        }else if(s.equals("Moveleft")){
            left();
        }
        
    }


    /*public void fire(){

    }*/

    public void draw(App app) {
        if (getX() + xVel < 20 || getX() + xVel + 20 > 700 || getY() + yVel < 20 || App.f.getY() + yVel + 20 > 700) {
            this.stop();
        }
        app.image(sprite,x,y,20, 20);
        //move();
        x += yVel;
        y += xVel;
        
    }
}


