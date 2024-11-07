package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Tree extends Resource{
    int subclass;

    public Tree(GamePanel gp, int amount, int subclass) {
        super(gp, "Wood", amount);
        this.subclass = subclass;
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Obj\\tree0"+subclass+".png"));
        }catch(IOException e){ e.printStackTrace();}
        
    }
    
    public Tree(GamePanel gp){
        super(gp, "Wood", 4);
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Obj\\tree01.png"));
        }catch(IOException e){ e.printStackTrace();}
    }
    
    //@Override
    public void draw(Graphics2D g2) {
        super.setLabel(gp.size,screenX,screenY);
        if (this.IsVisible()){
            g2.drawImage(image, screenX, screenY, gp.size, gp.size, null);
        } 
    }
}
