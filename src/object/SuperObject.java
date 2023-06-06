package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){
        int ScreenX = worldX - gp.player.worldX + gp.player.screenX; //where on screen to draw
        int ScreenY = worldY - gp.player.worldY + gp.player.screenY; //where on screen to draw
        //ScreenX and ScreenY 0,0 means top left of the screen
        //worldX and worldY  0,0 means top left of map

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);

        }
    }
}
