
package com.mycompany.proyectsurvival.EntitiesResourcesShelter;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author david
 */
public class Resource {
    private String type;
    private int amount;
    
    public BufferedImage image;
    public boolean collision = false;
    public int worldX, worldY;
    
    
    
    
    public void UseResource(){}
    public void AddResource(){}

    public String getType() {
        return type;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void draw(Graphics2D g, GamePanel gp){
        int screenX = worldX - gp.God.WorldX + gp.God.screenX;
        int screenY = worldY - gp.God.WorldY + gp.God.screenY;

        if (worldX + gp.size > gp.God.WorldX - gp.God.screenX &&
            worldX -gp.size< gp.God.WorldX + gp.God.screenX &&
            worldY +gp.size> gp.God.WorldY - gp.God.screenY &&
            worldY -gp.size< gp.God.WorldY + gp.God.screenY){
            g.drawImage(image, screenX, screenY, gp.size*2, gp.size*2, null);
        }
     
    }
}
