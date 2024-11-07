/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities_Resources_Shelter;
import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Brian Ramirez
 */
public class Turtle extends Animals{
    
    public Turtle(GamePanel gp, Keyboard key) {
        super( "Turtle",// Type
                20   ,// power
                30    ,//difficultuHunting
                4    ,//foodProvided
                gp);
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\turtle.png"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Turle");}
    }
    
    public Turtle(GamePanel gp) {
        super( "Turtle",// Type
                20   ,// power
                30    ,//difficultuHunting
                4    ,//foodProvided
                gp);
        this.WorldX = gp.mouseWorldX;
        this.WorldY = gp.mouseWorldY;
        try {
            this.image = ImageIO.read(new File(getPath()+"Animals\\turtle.png"));
        }catch(IOException e){  System.out.println("[ERROR] : Image Turle");}
    }

    
    

}
