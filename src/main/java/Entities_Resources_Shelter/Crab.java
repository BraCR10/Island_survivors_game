package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Crab extends Animals{

    public Crab(GamePanel gp, Keyboard key) {
       
        super("Crab",// Type
                10   ,// power
                20    ,//difficultuHunting
                2    ,//foodProvided
                gp);

        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\crab.gif"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Crab");}
    }
    
    public Crab(GamePanel gp) {
       
        super("Crab",// Type
                10   ,// power
                20    ,//difficultuHunting
                2    ,//foodProvided
                gp);
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\crab.gif"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Crab");}
    }
    
}
