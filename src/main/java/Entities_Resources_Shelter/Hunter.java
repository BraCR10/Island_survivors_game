package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.File;

public class Hunter extends Character{
    int lvl_hunter;
   

    public Hunter(int lvl_hunter, GamePanel gp, Keyboard key, String name) {
        super(gp,name);
        this.isCharacter = true;
        this.lvl_hunter = lvl_hunter;
        this.Write_Journal("----[HUNTER]----\n");

        setDefaultValues(); 
        
        this.screenX = this.getScreenX();
        this.screenY = this.getScreenY();
        
        
        try {
            this.image = ImageIO.read(new File(getPath()+"Characters\\Hunter.png"));
        }catch(IOException e){ System.out.println("[ERROR] : Image Hunter");}
    }
    
    public void setDefaultValues(){
        this.WorldX = 16 * gp.size;
        this.WorldY = 16 * gp.size;

        this.AddResource(new Resource(gp, "Meat", 4));
        
        this.speed = 3;
    }
    
    public void Eat(){
        Resource resource_To_Consume = this.getResource(this.gp.Resource_Selected);
    
        if (resource_To_Consume != null ){

            switch (resource_To_Consume.getString()) {
                    
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

            case "Hunt" -> {
                Hunt();
                break;
            }
            
            case "Protect" -> {
                Protect();
                break;
            }

            default -> this.objective = null; 
        }
        
    }

    private void Protect(){
    
    
    if (this.objective.isCharacter){

        Character to_protect  = (Character) objective;
        for (Animals A : gp.animals_in_map){
            if (A.IsTouched(to_protect)){
                
                this.objective = A;
                this.Order = "Hunt";
                break;
            }
        }
        
    } else {
        this.gp.menu.msg_Actions.setText("Hunter cant protect "+objective.getString());
        this.objective = null; 
        this.gp.Item_touched = null;
        this.Order = "";

    }
        
    }
    
    private void Hunt(){

        if ("Jaguar".equals(this.objective.getString()) || "Deer".equals(this.objective.getString())
            || "Crab".equals(this.objective.getString()) || "Snake".equals(this.objective.getString())
            || "Crocodile".equals(this.objective.getString()) || "Turtle".equals(this.objective.getString())){
            
            
            this.moveToObjective();
            Animals prey = gp.animals_in_map.get(gp.animals_in_map.indexOf(objective));
            
            if(this.IsTouched(prey)){
                this.Write_Journal("WENT to hunt "+objective.getString());
                prey.attack(this);
                prey.hunted(this);
                this.gp.Item_touched = null;
                this.Order = "";
                this.objective = null;
            }
            
            this.gp.Item_touched = null;
            
        } else {
            this.gp.Item_touched = null;
            this.gp.menu.msg_Actions.setText("Hunter can't hunt "+objective.getString());
            this.objective = null;
        }
    
    
    }
}