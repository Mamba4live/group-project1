package gremlins;
import gremlins.App;

import processing.core.*;
import processing.data.*;
import java.util.*;
import java.io.*;

public class Player {


    int x;
    private static int x1;
    int y;
    private static int y1;
    public PImage sprite = App.player_Right;
    private int speed = 2;
    int xVel = 0;
    int yVel = 0;
    public int[] currentposition = new int[4];
    public boolean Moveup;
    public boolean Moveright;
    public boolean Movedown;
    public boolean Moveleft;

    public Player(int y,int x,PImage sprite){
        this.x = getposision()[1];
        this.y = getposision()[0];
        this.sprite = sprite;
    }

    public static int[] position = new int[2];

    public static int[] getposision(){
        int x = 0;
        while(x < App.Map.size()){
            int y = 0;
            while(y < App.lsForCounter.get(x)){
                if (App.Map.get(x).get(y).equals("W")){
                    x1= (x)*20;
                    y1 = (y)*20;
                    break;
                }
                y += 1;
            }
            x += 1;
        } 
        position[0] = x1;
        position[1] = y1;
        return position;
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


    public int[] currentAdress(){
        int xaxis1 =  (int) Math.ceil((double) App.p.getX()/ (double) 20);
        int yaxis1 =(int) Math.ceil((double) App.p.getY()/ (double) 20);
        int xaxis2 = (int) Math.floor((double) App.p.getX()/(double) 20);
        int yaxis2 =(int) Math.floor((double) App.p.getY()/(double) 20);


        currentposition[1] = xaxis1;
        currentposition[0] = yaxis1;
        currentposition[3] = xaxis2;
        currentposition[2] = yaxis2;


        
        return currentposition;

    }
    
    public void fire(){
        App.f = new Fireball(App.p.getY(), App.p.getX(), App.fireball);
        if(sprite == App.player_Down){
            App.f.down();
        }else if(sprite == App.player_Up){
            App.f.up();
        }else if(sprite == App.player_Left){
            App.f.left();
        }else if(sprite == App.player_Right){
            App.f.right();
        }
    }
    

    public boolean[] checkposition(){

        boolean[] movement = new boolean[4];
        int[] i = currentAdress();
        int xCoord1 = i[0];
        int yCoord1 = i[1];
        int xCoord2 = i[2];
        int yCoord2 = i[3];
        
        //System.out.println("-----");
        System.out.println(i[1] +", " + i[0]);
        System.out.println(i[3] +", " + i[2]);
        //System.out.println("-----");
        //System.out.println("Current position1: " + App.Map.get(xCoord1).get(yCoord1));
        if(yCoord2 < App.Map.size()){
            if (App.Map.get(xCoord2).get(yCoord2+1).equals(" ")|| App.Map.get(xCoord2).get(yCoord2+1).equals("W")|| App.Map.get(xCoord2).get(yCoord2+1).equals("E")|| App.Map.get(xCoord2).get(yCoord2+1).equals("G")||App.Map.get(xCoord2).get(yCoord2+1).equals("k")){
                Moveright = true;
            }else{
                Moveright = false;
            }
        }else{
            Moveright = false;
        }
        if(yCoord1 > 1){
            if (App.Map.get(xCoord1).get(yCoord1-1).equals(" " )|| App.Map.get(xCoord1).get(yCoord1-1).equals("W")|| App.Map.get(xCoord1).get(yCoord1-1).equals("E")|| App.Map.get(xCoord1).get(yCoord1-1).equals("G")|| App.Map.get(xCoord1).get(yCoord1-1).equals("k")){
                Moveleft = true;
            }else{
                Moveleft = false;
            }
        }else{
            Moveleft = false;
        }
        if(xCoord1 > 1){
           // System.out.println("Up:" + App.Map.get(xCoord1-1).get(yCoord1));
           // System.out.println("Current position2: " + App.Map.get(xCoord1).get(yCoord1));
            if(App.Map.get(xCoord1-1).get(yCoord1).equals(" ")||App.Map.get(xCoord1-1).get(yCoord1).equals("W")||App.Map.get(xCoord1-1).get(yCoord1).equals("E")||App.Map.get(xCoord1-1).get(yCoord1).equals("G")||App.Map.get(xCoord1-1).get(yCoord1).equals("k")){
                Moveup =true;
            }else{
                Moveup = false;
            }
        }else{
            Moveup = false;
        }
        if(xCoord2 < App.Map.get(i[3]).size()-1){
            if(App.Map.get(xCoord2+1).get(yCoord2).equals(" ")|| App.Map.get(xCoord2+1).get(yCoord2).equals("W")|| App.Map.get(xCoord2+1).get(yCoord2).equals("E")|| App.Map.get(xCoord2+1).get(yCoord2).equals("G")|| App.Map.get(xCoord2+1).get(yCoord2).equals("k")){
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

    public void draw(App app) {
        App.p.checkposition();
        if (App.p.getX() + xVel < 20 || App.p.getX() + xVel + 20 > 700 || App.p.getY() + yVel < 20|| App.p.getY() + yVel + 20 > 700) {
            this.stop();
        }
        System.out.println(App.p.getX()+" , "+App.p.getY());
        //System.out.println(App.f.getX()+" , "+App.f.getY());
        app.image(sprite,x,y,20, 20);
        //System.out.println(checkposition()[0]);
        System.out.println("UP:" + App.Map.get(currentAdress()[0]-1).get(currentAdress()[1]) + " Down:" + App.Map.get(currentAdress()[2]+1).get(currentAdress()[3])+ " Right:" + App.Map.get(currentAdress()[2]).get(currentAdress()[3]+1)+ " Left:" + App.Map.get(currentAdress()[0]).get(currentAdress()[1]-1));
        //System.out.println("UP:" + checkposition()[0] + " Down:" + checkposition()[1]+ " Right:" + checkposition()[2]+ " Left:" + checkposition()[3]);
        x += xVel;
        y += yVel;
        
    }
}