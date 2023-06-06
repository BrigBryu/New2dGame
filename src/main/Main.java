package main;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import javax.swing.JFrame;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        JFrame winodw = new JFrame();
        winodw.setDefaultCloseOperation(3);
        winodw.setResizable(false);
        winodw.setTitle("2D Adventrue");
        GamePanel gamePanel = new GamePanel();
        winodw.add(gamePanel);
        winodw.pack();
        winodw.setLocationRelativeTo((Component)null);
        winodw.setVisible(true);
        gamePanel.setUpGame();
        //Starts game :)
        gamePanel.startGameThread();
    }
}

