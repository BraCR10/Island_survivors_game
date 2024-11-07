package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.File;

public class Scientist extends Character{
    int lvl_scientist;

   
    public Scientist (int lvl_scientist, GamePanel gp, Keyboard key, String name) {
        super(gp,name);
        this.isCharacter = true;
        this.lvl_scientist = lvl_scientist;
        this.Write_Journal("----[SCIENTIST]----\n");
       
        setDefaultValues(); 
        
        this.screenX = this.getScreenX();
        this.screenY = this.getScreenY();
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Characters\\Scientist.png"));
        }catch(IOException e){System.out.println("[ERROR] : Image Scientist");}
    } 
    public void setDefaultValues(){
        this.WorldX = 17 * gp.size;
        this.WorldY = 15 * gp.size;
        
        this.AddResource(new Resource(gp, "Potions", 2));
        this.AddResource(new Resource(gp, "Tools", 1));
        
        this.speed = 2;
    }
    
    public void Eat(){
        Resource resource_To_Consume = this.getResource(this.gp.Resource_Selected);
    
        if (resource_To_Consume != null ){
            
            
            switch (resource_To_Consume.getString()) {
                case "Fruits":
                    this.IncreaseStamina(10);
                    resource_To_Consume.UseResource(1);
                    this.Write_Journal("... ATE "+resource_To_Consume.getString()+" ...");
                    break;
                    
                case "Mushrooms":
                    this.IncreaseStamina(10);
                    resource_To_Consume.UseResource(1);
                    this.Write_Journal("... ATE "+resource_To_Consume.getString()+" ...");
                    break;
                    
                case "Meat":
                    this.IncreaseStamina(20);
                    resource_To_Consume.UseResource(1);
                    this.Write_Journal("... ATE "+resource_To_Consume.getString()+" ...");
                    break;

                case "Potions":
                    this.IncreaseHP(25);
                    this.IncreaseStamina(20);
                    resource_To_Consume.UseResource(1);
                    this.Write_Journal("... ATE "+resource_To_Consume.getString()+" ...");
                    break;
            }
  
        }
    }
    public void Rest(){
        
    if (this.objective != null && this.objective.getString().equals("Shelter")){

        this.moveToObjective();
  
        if (this.IsTouched(objective)){
            this.IncreaseHP(25);
            this.IncreaseStamina(100);            
            this.objective = null; 
            this.gp.Item_touched = null;
            this.Order = "";
            this.Write_Journal("... WENT to rest ...");
        }
    } else {
            this.Order = "";
            this.gp.Item_touched = null;
            this.objective = null;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.IsVisible()){
            g2.drawImage(image, screenX, screenY, gp.size, gp.size, null);
            super.setLabel(gp.size,screenX,screenY);
        } 
    }
    
    @Override
    public void update() { 
        super.update();

        
        if (this.objective == null && !"Move".equals(this.Order) && !"Creat Potion".equals(this.Order)
               && !"Creat Tool".equals(this.Order)){return;}

        switch (this.Order) {


            case "Rest" -> {
                Rest();
                break;
            }
            case "Share" -> {
                this.ShareResource((Character)this.objective, this.getResource(this.gp.Resource_Selected));
                break;
            }

            case "Change Shelter" -> {
                if (this.objective.getString() == "Shelter"){
                    this.moveToObjective();
                    if (this.IsTouched(objective)){
                        this.setShelter((Shelter) objective);
                        this.objective = null; 
                        this.gp.Item_touched = null;
                    } 
                } else {
                    this.objective = null;
                    this.gp.Item_touched = null;
                }

                break;
            }

            case "Move" -> {
                this.moveToPos();
                break;
            }

            case "Creat Tool" -> {
                CreatTool();
                break;
            }
            
            case "Creat Potion" -> {
                CreatPotion();
                break;
            }

            default -> this.objective = null; 
        }
        
    }
    
    private void CreatTool(){
        Resource material_1 = getResource("Wood");
        Resource material_2 = getResource("Rocks");
        
        if (material_1 != null && material_2 != null){
            material_1.UseResource(1);
            material_2.UseResource(1);
            this.AddResource(new Resource(gp, "Tools", 1));
            this.ReduceStamina(5);
            this.Order = "";
            this.objective = null; 
            this.gp.Item_touched = null;
            this.Write_Journal("... CREATED a new TOOL ...");
        }     
    
    }
    private void CreatPotion (){
        Resource material_1 = getResource("Mushrooms");
        Resource material_2 = getResource("Fruits");
        
        if (material_1 != null && material_2 != null){
            material_1.UseResource(1);
            material_2.UseResource(1);
            this.AddResource(new Resource(gp, "Potions", 1));
            this.ReduceStamina(5);
            this.Order = "";
            this.objective = null; 
            this.gp.Item_touched = null;
            this.Write_Journal("... CREATED a new Potion ...");
        }     
    
    }
}
