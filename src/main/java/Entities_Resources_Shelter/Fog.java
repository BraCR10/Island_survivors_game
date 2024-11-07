package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Fog extends Resource{
    

    public Fog (GamePanel gp) {
        super(gp, "Fog", 1);
        
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Obj\\fog.png"));
        }catch(IOException e){ e.printStackTrace();}
    }
    
    //@Override
    public void draw(Graphics2D g2) {
        if (this.IsVisible()){
            g2.drawImage(image, screenX, screenY, gp.size, gp.size, null);
            super.setLabel(gp.size, screenX, screenY);
        } 
    }

    public void SetXY(int x, int y){
        this.WorldX = x * gp.size;
        this.WorldY = y * gp.size;
    }
}
