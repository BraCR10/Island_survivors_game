package Entities_Resources_Shelter;
import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Crocodiles extends Animals{

    public Crocodiles(GamePanel gp, Keyboard key) {
       
        super( "Crocodile",// Type
                80   ,// power
                80    ,//difficultuHunting
                3    ,//foodProvided
                gp);

        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\cocodrile.png"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Cocodrile");}
    }
    
    public Crocodiles(GamePanel gp) {
        super( "Crocodile",// Type
                80   ,// power
                80    ,//difficultuHunting
                3    ,//foodProvided
                gp);
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\Cocodrile.png"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Cocodrile");}
    }
    
}
