
package com.mycompany.proyectsurvival.EntitiesResourcesShelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.awt.Graphics2D;
import java.awt.Color;


public class Explorer extends Character{
    int lvl_exploration;
    
    GamePanel gp;
    Keyboard key;
    
    public final int screenX;
    public final int screenY;

    public Explorer(int lvl_exploration, GamePanel gp, Keyboard key, String name) {
        super(gp,name);
        this.lvl_exploration = lvl_exploration;
        this.gp = gp;
        this.key = key;
        this.screenX = gp.ScreenWidth/2 + 10;
        this.screenY = gp.ScreenHeight/2 + 10;
        
        setDefaultValues();    
    }
    
    public void draw (Graphics2D g2){
        g2.setColor(Color.BLUE);
        g2.fillRect(WorldX, WorldY, 6, 6);
    }
    
    public void setDefaultValues(){
        WorldX = screenX;
        WorldY = screenY;
        speed = 4;
    }
    
    public void Action(){};
    public void Eat(){};
    public void Rest(){};
}
