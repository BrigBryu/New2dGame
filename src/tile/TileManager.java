package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImiage();
        loadMap();
    }

    public void getTileImiage() {

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));



        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){ //scans line by line col by col to get map txt file in 2d array
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map03.txt"); //imports text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //reads the text file

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine(); //puts one line into the line

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" "); //splits the string around matches so a space to get seperate numbers
                    int num = Integer.parseInt(numbers[col]); //changes the split String that holds numbers into int
                    mapTileNum[col][row] = num; //Stores the new int to the right row,col
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){
        /* test draw
        //x and y in pixels
        g2.drawImage(tile[0].image,0,0,gp.tileSize, gp.tileSize, null); //grass
        g2.drawImage(tile[1].image,48,0,gp.tileSize, gp.tileSize, null); //wall
        g2.drawImage(tile[2].image,96,0,gp.tileSize, gp.tileSize, null); //water
         */

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize; //position on map
            int worldY = worldRow * gp.tileSize; //position on map
            int ScreenX = worldX - gp.player.worldX + gp.player.screenX; //where on screen to draw
            int ScreenY = worldY - gp.player.worldY + gp.player.screenY; //where on screen to draw
            //ScreenX and ScreenY 0,0 means top left of the screen
            //worldX and worldY  0,0 means top left of map

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;
            }
        }
    }
}
