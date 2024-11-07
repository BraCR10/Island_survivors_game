package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.Keyboard;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.File;

public class Builder extends Character{
    int lvl_builder;

    public Builder (int lvl_builder, GamePanel gp, Keyboard key, String name) {
        super(gp,name);
        this.isCharacter = true;
        this.lvl_builder = lvl_builder;
        this.Write_Journal("----[BUILDER]----\n");
       
        setDefaultValues(); 
        
        this.screenX = this.getScreenX();
        this.screenY = this.getScreenY();
        
        try {
            this.image = ImageIO.read(new File(getPath()+"\\Characters\\Builder.png"));
        }catch(IOException e){System.out.println("[ERROR] : Image Builder");}
        
    } 
    public void setDefaultValues(){
        this.WorldX = 16 * gp.size;
        this.WorldY = 14 * gp.size;
        this.speed = 1;
        
        this.AddResource(new Resource(gp, "Tools", 1));
    }
    
    private void Build(){
        Resource r_rocks = this.getResource("Rocks");
        Resource r_wood = this.getResource("Wood");
        Resource r_tools = this.getResource("Tools");
        
        boolean var = r_rocks != null && r_wood != null && r_tools != null;
        
        if (var && r_wood.getAmount() >= 5 && r_rocks.getAmount() >= 3 && r_tools.getAmount() >= 1){
            r_wood.UseResource(5);
            r_rocks.UseResource(3);
            r_tools.UseResource(1);
            
            
            this.gp.shelters_in_map.add(new Shelter("Shelter", gp, this.WorldX/gp.size, this.WorldY/gp.size));
            this.Order = "";
            this.objective = null;
            this.gp.Item_touched = null;
            this.ReduceStamina(35);
            
            this.Write_Journal("... BUILD a new shelter ...");
        } else {
            this.Order = "";
            this.objective = null;
            this.gp.Item_touched = null;
            this.gp.menu.msg_Actions.setText("Insufficient Resources, you \nneed 5xWood, 3xRocks, \n1xTool");
        }
    }
    private void Repair(){
    if (this.objective.getString().equals("Shelter")){
        int index = gp.shelters_in_map.indexOf(objective);
        this.moveToObjective();
        
        
                
        if (index != -1 && this.IsTouched(objective)){
            Shelter shelter_to_repair = gp.shelters_in_map.get(index);
            shelter_to_repair.Repair(this);
            this.ReduceStamina(20);            
            this.objective = null; 
            this.gp.Item_touched = null;
            this.Write_Journal("... REPAIR a shelter ...");
        }
    } else {
            this.gp.Item_touched = null;
            this.objective = null;
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

        
        if (this.objective == null && !"Move".equals(this.Order)&& !"Build".equals(this.Order)
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

            case "Build" -> {
                Build();
                break;
            }
            
            case "Repair" -> {
                Repair();
                break;
            }
            
            case "Creat Tool" -> {
                CreatTool();
                break;
            }

            default -> this.objective = null; 
        }
        
    }
    


}
