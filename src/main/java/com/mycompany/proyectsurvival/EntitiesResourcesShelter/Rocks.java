package com.mycompany.proyectsurvival.EntitiesResourcesShelter;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rocks extends Resource{
    String name;    
    
    public Rocks(int num){
        name = "Rocks";
        
        try {
            image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Obj\\rocks0"+num+".png"));
        }catch(IOException e){ e.printStackTrace();}
    
    }
}
