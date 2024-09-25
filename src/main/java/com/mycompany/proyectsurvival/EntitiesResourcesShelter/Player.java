package com.mycompany.proyectsurvival.EntitiesResourcesShelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends Entity{
    Keyboard key;
    
    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp, Keyboard key) {
        super(gp);
       
        this.key = key;
        
        screenX = gp.ScreenWidth/2;
        screenY = gp.ScreenHeight/2;
        
        setDefaultValues();
    }
    
    public void setDefaultValues(){
        WorldX = gp.maxWorldWidth/2;
        WorldY = gp.maxWorldHeight/2;
        speed = 4;
    }
    
    public void update (){
        if(key.moveUP){
            this.WorldY -= speed;
        }
        if (key.moveDOWN){
            this.WorldY += speed;
        } 
        if (key.moveRIGHT){
            this.WorldX += speed;
        }
        if (key.moveLEFT){
            this.WorldX -= speed;
        }
    }
    
    public void draw (Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillOval(screenX, screenY, 6, 6);
    }
}
