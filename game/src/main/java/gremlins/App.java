package gremlins;
import gremlins.Player;
import processing.core.*;
import processing.data.*;
import processing.event.*;
import java.util.*;

import java.io.*;


public class App extends PApplet {

    public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;

    public static final int FPS = 60;

    public static final Random randomGenerator = new Random();

    public int counter;
    public String configPath;
    public static PImage brickwall;
    public static PImage stonewall;
    public static PImage gremlin;
    public static PImage slime;
    public static PImage fireball;
    public static PImage player_Right;
    public static PImage player_Left;
    public static PImage player_Up;
    public static PImage player_Down;
    public static int playerinfo;
    public static int counter1;
    public static int counter2;
    public static int counter3;
    public static int counter4;
    public PImage brickwall_destroyed0;
    public PImage brickwall_destroyed1;
    public PImage brickwall_destroyed2;
    public PImage brickwall_destroyed3;
    public PImage door;
    public static PImage EmptyTile;
    public static ArrayList<ArrayList<String>> Map = new ArrayList<ArrayList<String>>();
    public static ArrayList<Integer> lsForCounter = new ArrayList<Integer>(); 
    public ArrayList<PImage> breakwall = new ArrayList<PImage>();
    public static Player p;
    public static Fireball f;
    public static Enemy G1;
    public static Enemy G2;
    public static Enemy G3;
    public static Enemy G4;
    

    
    public static int level = 1;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        // Load images during setup
        this.stonewall = loadImage(this.getClass().getResource("stonewall.png").getPath().replace("%20", ""));
        this.brickwall = loadImage(this.getClass().getResource("brickwall.png").getPath().replace("%20", ""));
        this.gremlin = loadImage(this.getClass().getResource("gremlin.png").getPath().replace("%20", ""));
        this.slime = loadImage(this.getClass().getResource("slime.png").getPath().replace("%20", ""));
        this.fireball = loadImage(this.getClass().getResource("fireball.png").getPath().replace("%20", ""));
        this.player_Down = loadImage(this.getClass().getResource("wizard3.png").getPath().replace("%20", ""));
        this.player_Up = loadImage(this.getClass().getResource("wizard2.png").getPath().replace("%20", ""));
        this.player_Right = loadImage(this.getClass().getResource("wizard1.png").getPath().replace("%20", ""));
        this.player_Left = loadImage(this.getClass().getResource("wizard0.png").getPath().replace("%20", ""));
        this.brickwall_destroyed0 = loadImage(this.getClass().getResource("brickwall_destroyed0.png").getPath().replace("%20", ""));
        this.brickwall_destroyed1 = loadImage(this.getClass().getResource("brickwall_destroyed1.png").getPath().replace("%20", ""));
        this.brickwall_destroyed2 = loadImage(this.getClass().getResource("brickwall_destroyed2.png").getPath().replace("%20", ""));
        this.brickwall_destroyed3 = loadImage(this.getClass().getResource("brickwall_destroyed3.png").getPath().replace("%20", ""));
        this.EmptyTile = loadImage(this.getClass().getResource("empty.png").getPath().replace("%20", ""));
        this.door = loadImage(this.getClass().getResource("door.png").getPath().replace("%20", ""));
        


        JSONObject conf = loadJSONObject(new File(this.configPath));
        JSONArray mapinfo = conf.getJSONArray("levels");
        if (counter == 0){
            playerinfo = conf.getInt("lives");
        }
        JSONObject Level1info = mapinfo.getJSONObject(0);
        JSONObject Level2info = mapinfo.getJSONObject(1);
        String map1 = Level1info.getString("layout");
        String map2 = Level2info.getString("layout");
        float wizard_cooldown_1 = Level1info.getFloat("wizard_cooldown");
        float enemy_cooldown_1 = Level1info.getFloat("enemy_cooldown");
        float wizard_cooldown_2 = Level2info.getFloat("wizard_cooldown");
        float enemy_cooldown_2 = Level2info.getFloat("enemy_cooldown");
        try{
            if (level == 1){
                File f = new File(map1);
                Scanner s = new Scanner(f);
                Map = new ArrayList<ArrayList<String>>();
                lsForCounter = new ArrayList<Integer>();
                while(s.hasNext()){
                    String s1 = s.nextLine();
                    String[] arr =  s1.split("");
                    ArrayList<String> ls = new ArrayList<String>();
                    int counter = 0;
                    for(String c : arr){
                        ls.add(c);
                        counter += 1;
                    }
                    lsForCounter.add(counter);
                    Map.add(ls);
                }
                int x = 0;
                while(x < Map.size()){
                    int y = 0;
                    while(y < lsForCounter.get(x)){
                        System.out.print(Map.get(x).get(y));
                        y += 1;
                    }
                    System.out.println();
                    x += 1;
                } 
            }else if(level == 2){
                File f = new File(map2);
                Scanner s = new Scanner(f);
                Map = new ArrayList<ArrayList<String>>();
                lsForCounter = new ArrayList<Integer>();
                while(s.hasNext()){
                    String s1 = s.nextLine();
                    String[] arr =  s1.split("");
                    ArrayList<String> ls = new ArrayList<String>();
                    int counter = 0;
                    for(String c : arr){
                        ls.add(c);
                        counter += 1;
                    }
                    lsForCounter.add(counter);
                    Map.add(ls);
                }
                int x = 0;
                while(x < Map.size()){
                    int y = 0;
                    while(y < lsForCounter.get(x)){
                        System.out.print(Map.get(x).get(y));
                        y += 1;
                    }
                    System.out.println();
                    x += 1;
                } 
            }
            
            
        }catch(FileNotFoundException e){
            System.out.println("Maps cannot be found");
        }
        
        //System.out.println(Enemy.getposition().get(0).get(0)+" , "+ Enemy.getposition().get(0).get(1));

        p = new Player(Player.getposision()[0]*20,Player.getposision()[1]*20 , player_Right);
        f = new Fireball(p.getX(), p.getY(), fireball);
        G1 = new Enemy(Enemy.getposition().get(0).get(0),Enemy.getposition().get(0).get(1), gremlin);
        G2 = new Enemy(Enemy.getposition().get(1).get(0),Enemy.getposition().get(1).get(1), gremlin);
        G3 = new Enemy(Enemy.getposition().get(2).get(0),Enemy.getposition().get(2).get(1), gremlin);
        G4 = new Enemy(Enemy.getposition().get(3).get(0),Enemy.getposition().get(3).get(1), gremlin);

    }

    

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        p.checkposition();
        
        if (key == 65) { //a
            p.sprite = player_Left;
            if(p.checkposition()[3] == true){
                p.left();
            }else{
                p.stop();
            }
        } else if (key == 68) { //d
            p.sprite = player_Right;
            if(p.checkposition()[2] == true){
                p.right();
            }else{
                p.stop();
            }
        } else if (key == 87) { //w
            p.sprite = player_Up;
            if(p.checkposition()[0] == true){
                p.up();
            }else{
                p.stop();
            }
        } else if (key == 83) { //s
            p.sprite = player_Down;
            if(p.checkposition()[1] == true){
                p.down();
            }else{
                p.stop();
            }
        } else if (key == 32) { //" "
            f.attack = true;
            //if(fired == false){
            p.fire();
            //}

        System.out.println("Fire!");
        }
        //System.out.println(counter1 + " , " + counter2+ " , " + counter3+ " , " + counter4);
    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == 65 || key == 68 || key == 87 || key == 83) {
                p.stop();
            }

    }


    /**
     * Draw all elements in the game by current frame. 
	 */
    public void draw() {
        if(Map.get(p.currentAdress()[0]).get(p.currentAdress()[1]).equals("G" ) ){
            System.out.println("LIVES: " + playerinfo);
            playerinfo -= 1;
            p = new Player(Player.getposision()[0]*20,Player.getposision()[1]*20 , App.player_Right);
            counter += 1;
            setup();
        }
        if(Map.get(p.currentAdress()[0]).get(p.currentAdress()[1]).equals("E" ) && level < 2){
            level += 1;
            p = new Player(Player.getposision()[0]*20,Player.getposision()[1]*20 , App.player_Right);
            setup();
        }
        
        background(255,140,0);
        text("LIFE:                     " + Integer.toString(playerinfo), 0, 680, 160, 200);
        textAlign(LEFT);
        text("LEVEL:                     " + Integer.toString(level), 500, 680, 160, 200);
        textAlign(LEFT);

        if((f.getX() + f.xVel <= 700 && f.getY()+ f.yVel <= 700 && f.getX()- f.xVel > 20 && f.getY() - f.yVel> 20)){
            f.draw(this);
            
        }

        p.checkposition();
        int x = 0;
        int line = 0;
        while(x < Map.size()){
            int y = 0;
            int col = 0;
            while(y < lsForCounter.get(x)){
                if(Map.get(x).get(y).equals("X")){
                    this.image(stonewall,col,line,20,20);
                    col+=20;
                }
                else if(Map.get(x).get(y).equals("B")){
                    this.image(brickwall,col,line,20,20);
                    col+=20;
                }
                else if(Map.get(x).get(y).equals(" ")){
                    this.image(EmptyTile,col,line,20,20);
                    col+=20;
                }
                else if(Map.get(x).get(y).equals("E")){
                    this.image(door,col,line,20,20);
                    col+=20;
                }else if(Map.get(x).get(y).equals("W")){
                    this.image(EmptyTile,col,line,20,20);
                    col+=20;
                }else if(Map.get(x).get(y).equals("G")){
                    this.image(EmptyTile,col,line,20,20);
                    this.image(gremlin,col,line,20,20);
                    col+=20;
                }else if(Map.get(x).get(y).equals("k")){
                    for(PImage wall: breakwall){
                        this.image(wall,col,line,20,20);
                    }
                    this.image(EmptyTile,col,line,20,20);
                    col+=20;
                }
                
                y += 1;

            }
            x += 1;
            line += 20;
        } 

        p.draw(this);
        if((f.getX() + f.xVel <= 700 && f.getY()+ f.yVel <= 700 && f.getX()- f.xVel > 20 && f.getY() - f.yVel> 20)){
            f.draw(this);
            
        }

        
        /*G1.draw(this);
        G2.draw(this);
        G3.draw(this);
        G4.draw(this);*/



        

        if(playerinfo == 0){
            background(255,140,0);
            text("GAME OVER", 360, 360);
            textAlign(CENTER);
            fill(0);
        }
        if(level == 2 && App.Map.get(p.currentAdress()[0]).get(p.currentAdress()[1]).equals("E" )){
            background(255,140,0);
            text("YOU WIN", 360, 360);
            textAlign(CENTER);
            fill(0);
        }
        
    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
