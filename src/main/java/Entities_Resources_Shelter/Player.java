package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class Player extends Entity{
    Keyboard key;
    
    public int screenX;
    public int screenY;
    
    public Player(GamePanel gp, Keyboard key) {
        super(gp);
       
        this.key = key;
        
        screenX = gp.ScreenWidth/2;
        screenY = gp.ScreenHeight/2;
        
        setDefaultValues();
        
    }
    
    public void setDefaultValues(){
        WorldX = 15*gp.size;
        WorldY = 15*gp.size;
        speed = 5;
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
    
     public String getString(){return "Player/God";}

     

}
