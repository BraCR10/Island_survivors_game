package Entities_Resources_Shelter;
import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Jaguar extends Animals{

    public Jaguar(GamePanel gp, Keyboard key) {
       
        super("Jaguar",// Type
                85   ,// power
                95    ,//difficultuHunting
                6    ,//foodProvided
                gp);
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\jaguar.png"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Jaguar");}
    }

    public Jaguar(GamePanel gp) {
       
        super("Jaguar",// Type
                85   ,// power
                95    ,//difficultuHunting
                6    ,//foodProvided
                gp);
        
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\jaguar.png"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Jaguar");}
    }
    
}
