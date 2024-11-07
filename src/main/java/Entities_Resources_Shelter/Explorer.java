package Entities_Resources_Shelter;

import Entities_Resources_Shelter.Shelter;
import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.File;

public class Explorer extends Character{
    int lvl_exploration;
   
    public Explorer(int lvl_exploration, GamePanel gp, Keyboard key, String name) {
        super(gp,name);
        
        this.isCharacter = true;
        this.lvl_exploration = lvl_exploration;
        this.Write_Journal("----[EXPLORER]----\n");
       
        setDefaultValues(); 
        
        this.screenX = this.getScreenX();
        this.screenY = this.getScreenY();
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Characters\\Explorer.png"));
        }catch(IOException e){ System.out.println("[ERROR] : Image Explorer");}
       
        
        
    }
    public void setDefaultValues(){
        this.WorldX = 14 * gp.size;
        this.WorldY = 14 * gp.size;
        this.speed = 3;
        
        this.AddResource(new Resource(gp, "Wood", 2));
        this.AddResource(new Resource(gp, "Mushrooms", 2));
    }
    
    private void Explor(Entity objective){
        
        if ("Fog".equals(this.objective.getString())){
            
            int index;
            this.moveToObjective();
            
            for (Fog f : gp.fog){
                if (f.IsTouched(this) && f != this.objective){
                    gp.fog.remove((Fog) f);
                    this.ReduceStamina(5);
                    break;
                }
            }

            index = gp.fog.indexOf(objective);
            if (index != -1 && this.IsTouched(objective)){
                gp.fog.remove((Fog) objective);
                this.ReduceStamina(15);
                this.gp.Item_touched = null;
                this.objective = null;
                this.Write_Journal("...WENT TO EXPLORE");
            }
 
        } else {
            this.gp.Item_touched = null;
            this.gp.menu.msg_Actions.setText("Explorer can't explor "+objective.getString());
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
    public void update() { 
        super.update();
        
        
        if (this.objective == null && !"Move".equals(this.Order) && !"Rest".equals(this.Order)){return;}
        
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
            
            case "Move"-> {
                this.moveToPos();
                break;
            }
            
            case "Explor" -> {
                Explor(objective);
                break;
            }
            
            default -> this.objective = null; 
                
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.IsVisible()){
            g2.drawImage(image, screenX, screenY, gp.size, gp.size, null);
            super.setLabel(gp.size,screenX,screenY);
        } 
    }

}
