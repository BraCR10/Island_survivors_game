package com.mycompany.proyectsurvival.EntitiesResourcesShelter;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author david
 */
public class Entity {
    
    GamePanel gp;
    public int WorldX,WorldY;
    public int speed;
    
    public BufferedImage up,down,left,right;
    public String direction;

    public Rectangle solidArea;
    public boolean collisioinOn = false;
    
    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    
    
    
}
