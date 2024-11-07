package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Deer extends Animals{


    public Deer(GamePanel gp, Keyboard key) {
       
        super("Deer",// Type
                
                30   ,// power
                35    ,//difficultuHunting
                6    ,//foodProvided
                gp);

        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\deer.gif"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Deer");}
    }
    
    public Deer(GamePanel gp) {
       
        super("Deer",// Type
                30   ,// power
                35    ,//difficultuHunting
                6    ,//foodProvided
                gp);
        
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\deer.gif"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Deer");}
    }
    
}
