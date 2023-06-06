package main;

import object.OBJ_Chest;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject(){

        //placing key col/y 30 and row/x 14 from the top
        gp.obj[0] =  new OBJ_Key(); //water
        gp.obj[0].worldX = 30 * gp.tileSize;
        gp.obj[0].worldY = 14 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(); //small bottom house
        gp.obj[1].worldX = 38 * gp.tileSize;
        gp.obj[1].worldY = 44 * gp.tileSize;

        gp.obj[2] =  new OBJ_Key(); //small left house
        gp.obj[2].worldX = 11 * gp.tileSize;
        gp.obj[2].worldY = 30 * gp.tileSize;

        gp.obj[3] =  new OBJ_Chest(); //top big house
        gp.obj[3].worldX = 34 * gp.tileSize;
        gp.obj[3].worldY = 5 * gp.tileSize;

    }
}
