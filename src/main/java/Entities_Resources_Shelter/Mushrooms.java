package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mushrooms extends Resource{
    int subclass;

    public Mushrooms (GamePanel gp, int amount, int subclass) {
        super(gp, "Mushrooms", amount);
        this.subclass = subclass;
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Obj\\mushroom0"+subclass+".png"));
        }catch(IOException e){ e.printStackTrace();}
    }
    
    public Mushrooms (GamePanel gp){
        super(gp, "Mushrooms", 4);
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Obj\\mushroom01.png"));
        }catch(IOException e){ e.printStackTrace();}
    }
    
    
    //@Override
    public void draw(Graphics2D g2) {
        super.setLabel(gp.size/2,screenX,screenY);
        if (this.IsVisible()){
            g2.drawImage(image, screenX, screenY, gp.size/2, gp.size/2, null);
        } 
    }
}
