package com.mycompany.proyectsurvival.EntitiesResourcesShelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import com.mycompany.proyectsurvival.Keyboard;
import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends Entity{
    GamePanel gp;
    Keyboard key;
    
    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp, Keyboard key) {
        this.gp = gp;
        this.key = key;
        
        screenX = gp.ScreenWidth/2;
        screenY = gp.ScreenHeight/2;
        
        setDefaultValues();
    }
    
    public void setDefaultValues(){
        WorldX = 45;
        WorldY = 45;
        speed = 2;
    }
    
    public void update (){
        if(key.moveUP){
            WorldY -= speed;
        } else if (key.moveDOWN){
            WorldY += speed;
        } else if (key.moveRIGHT){
            WorldX += speed;
        }else if (key.moveLEFT){
            WorldX -= speed;
        }
    }
    
    public void draw (Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillOval(screenX, screenY, 6, 6);
    }
}
