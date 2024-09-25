
package com.mycompany.proyectsurvival;
import com.mycompany.proyectsurvival.EntitiesResourcesShelter.Entity;


public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    

     
    public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.WorldX + entity.solidArea.x;
        int entityRightWorldX = entity.WorldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.WorldY + entity.solidArea.y;
        int entityBottomWorldY = entity.WorldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gp.size;
        int entityRightCol = entityRightWorldX/gp.size;
        int entityTopRow = entityTopWorldY/gp.size;
        int entityBottomRow = entityBottomWorldY/gp.size;
        
        int tileNum1, tileNum2;
        
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.size;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true ||gp.tileM.tile[tileNum2].collision == true ){
                    entity.collisioinOn = true;
                }break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.size;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true ||gp.tileM.tile[tileNum2].collision == true ){
                    entity.collisioinOn = true;
                }break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.size;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true ||gp.tileM.tile[tileNum2].collision == true ){
                    entity.collisioinOn = true;
                }break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.size;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true ||gp.tileM.tile[tileNum2].collision == true ){
                    entity.collisioinOn = true;
                }break;
        }
            
    
    }
    
    
    
    

}
