package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

//top left is 0,0 y and x increase as you go down and right
public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldRow;
    public final int worldHeight = tileSize * maxWorldCol;
    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter =  new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //10 slots for object displaying can be increased

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // makes rendering better
        this.addKeyListener(keyH);
        this.setFocusable(true); //GamePanel can be "focused" to receive key input
    }

    public void setUpGame(){
        aSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); //calls run
    }
    //When an object implementing Runnable is used to create a thread, starting the thread causes the object's run method to be called in that separately executing thread..
    //basicaly when thread is created it calls the run method

    @Override
    /*
    //uses a game loop called sleep not always perfect
    public void run() {
        //1 billion 9 zeros nano seconds or 1 second
        //System.nanoTime() returns the current system time
        double drawInterval = 1000000000/FPS; //draw the screen every 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            // 1: update information like character position
            update();

            // 2 DRAW: draw the screen with the updated information
            repaint(); //calls paintComponent method



            try {
                double remainingTime = nextDrawTime - System.nanoTime(); //how much time remaining until the next draw time
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
            }
        }
    }
     */
    //game loop called (delta)
    public void run() {
        //1 billion 9 zeros nano seconds or 1 second
        //System.nanoTime() returns the current system time
        double drawInterval = 1000000000/FPS; //draw the screen every 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timmer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timmer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
                // 1: update information like character position
                update();
                // 2 DRAW: draw the screen with the updated information
                repaint(); //calls paintComponent method
                delta--;
                drawCount++;
            }
            if(timmer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timmer = 0;
            }
        }
    }


    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //2d graphics
        //Tile
        tileM.draw(g2); //draw tiles firs so that background dos not hide stuff

        //Object obj is an array of the 10 objects to draw
        for(int i = 0; i <  obj.length; i++){
            if(obj[i] != null){ //if there is nothing  you get NullPont error
                obj[i].draw(g2,this);
            }
        }
        //Player
        player.draw(g2);

        g2.dispose();
    }
}

