package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Snake extends Animals{

    public Snake(GamePanel gp, Keyboard key) {
       
        super("Snake",// Type
                40   ,// power
                70    ,//difficultuHunting
                1    ,//foodProvided
                gp);

        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\snake.gif"));
        }catch(IOException e){ System.out.println("[ERROR] : Image Snake");}
    }
     
    public Snake(GamePanel gp) {
        super("Snake",// Type
                40   ,// power
                70    ,//difficultuHunting
                1    ,//foodProvided
                gp);
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\snake.gif"));
        }catch(IOException e){ System.out.println("[ERROR] : Image Snake");}
    }
    
    
   
    
}
