package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.File;

public class Gatherer extends Character{
    int lvl_gatherer;

    public Gatherer(int lvl_gatherer, GamePanel gp, Keyboard key, String name) {
        super(gp,name);
        this.isCharacter = true;
        this.lvl_gatherer = lvl_gatherer;
        this.Write_Journal("----[GATHERER]----\n");
       
        setDefaultValues(); 
        
        this.screenX = this.getScreenX();
        this.screenY = this.getScreenY();
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Characters\\Gatherer.png"));
        }catch(IOException e){System.out.println("[ERROR] : Image Gatherer");}
        
    }
    public void setDefaultValues(){
        this.WorldX = 14 * gp.size;
        this.WorldY = 16 * gp.size;

        this.speed = 1;
        
        this.AddResource(new Resource(gp, "Wood", 2));
        this.AddResource(new Resource(gp, "Mushrooms", 2));
        this.AddResource(new Resource(gp, "Fruits", 2));
        
    }

    public void Gather(Entity objective){
        if ("Wood".equals(this.objective.getString()) || "Rocks".equals(this.objective.getString())
            || "Mushrooms".equals(this.objective.getString()) || "Fruits".equals(this.objective.getString())){
            
            int index;
            this.moveToObjective();
            
            switch (objective.getString()){
                
                case "Wood" ->{
                    index = gp.Trees_in_map.indexOf(objective);
                    if (index != -1 && this.IsTouched(objective)){
                        this.AddResource((Resource) objective);
                        gp.Trees_in_map.remove((Tree) objective);
                        this.ReduceStamina(15);
                        this.Write_Journal("GOT Wood ...");
                        this.objective = null; 
                        this.gp.Item_touched = null;
                    } 
                    break;
                }
                case "Rocks" ->{
                    index = gp.Rocks_in_map.indexOf(objective);
                    if (index != -1 && this.IsTouched(objective)){
                        this.AddResource((Resource) objective);
                        gp.Rocks_in_map.remove((Rocks) objective);
                        this.ReduceStamina(20);
                        this.Write_Journal("GOT Rocks ...");
                        this.objective = null; 
                        this.gp.Item_touched = null;
                    }
                    break;
                }
                case "Mushrooms" ->{
                    index = gp.Mushrooms_in_map.indexOf(objective);
                    if (index != -1 && this.IsTouched(objective)){
                        this.AddResource((Resource) objective);
                        gp.Mushrooms_in_map.remove((Mushrooms)objective);
                        this.ReduceStamina(10);
                        this.Write_Journal("GOT Mushrooms ...");
                        this.objective = null; 
                        this.gp.Item_touched = null;
                    } 
                    break;
                }
                case "Fruits" ->{
                    index = gp.Fruits_in_map.indexOf(objective);
                    if (index != -1 && this.IsTouched(objective)){
                        this.AddResource((Resource) objective);
                        gp.Fruits_in_map.remove((Fruit)objective);
                        this.ReduceStamina(10);
                        this.Write_Journal("GOT Fruits ...");
                        this.objective = null; 
                        this.gp.Item_touched = null;
                    } 
                    break;
                }

            }
        } else {
            this.gp.Item_touched = null;
            this.gp.menu.msg_Actions.setText("Gatherer can't collect "+objective.getString());
            this.objective = null;
        }
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

        
        if (this.objective == null && !"Move".equals(this.Order)){return;}

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

            case "Gather" -> {
                Gather(objective);
                break;
            }

            default -> this.objective = null; 
        }
        
    }
    

}
