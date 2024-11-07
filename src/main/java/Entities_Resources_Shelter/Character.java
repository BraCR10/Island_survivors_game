package Entities_Resources_Shelter;


import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Graphics2D;
import java.util.ArrayList;


public abstract class Character extends Entity{
    
    public ArrayList<Resource> inventory;
    private final String name;
    private int Lvl_stamina;
    private Shelter shelter;
    private int Lvl_HP;
    
    private String journal = "";
    public String Order = "";
  
     
    
    public Character(GamePanel gp, String name) {
        super(gp);
        this.name = name;
        this.Lvl_stamina = 100;
        this.Lvl_HP = 100;
        this.inventory = new ArrayList<Resource>();
        this.shelter = null;
        this.label.setLocation(WorldX, WorldY);
        this.CanMove = true;
        
    }
    
    abstract public void draw(Graphics2D g2);
    public abstract void Eat();
    public abstract void Rest();
    
    public void ShareResource(Character receiver, Resource resourse) {
        this.moveToObjective();   
        if (this.IsTouched(receiver)){
           
            Resource resource_To_Consume = this.getResource(this.gp.Resource_Selected);
            if (resource_To_Consume != null && resource_To_Consume.getAmount() > 0){
                receiver.AddResource(resource_To_Consume);
                this.inventory.remove(resource_To_Consume);   
                this.Write_Journal("SHARE with "+receiver.getString()+" ["+resource_To_Consume.getString()+"]");
            }
            
            this.objective = null; 
            this.gp.Item_touched = null;
        }
    }
    public void AddResource(Resource resource){
        boolean found = false;
        
        for (int i = 0; i < this.inventory.size();i++){
            if (resource.getType() == this.inventory.get(i).getType()){
                this.inventory.get(i).setAmount(this.inventory.get(i).getAmount() + resource.getAmount());
                found = true;
                break;
            }    
        }
        if (!found){
            this.inventory.add(resource);
        } 
    }
    public Resource getResource(String type){
        for (Resource r : this.inventory){
            if (r.getString() == type){ return r;}
        }
        return null;
    }
    
    public void IncreaseStamina(int points){
        Lvl_stamina += points;
        if (Lvl_stamina >= 100){ Lvl_stamina = 100;}
    } 
    public void ReduceStamina(int points){
        Lvl_stamina -= points;
        if (Lvl_stamina <= 0){ Lvl_stamina = 0;}
    }
    public void IncreaseHP (int points){
        Lvl_HP += points;
        if (Lvl_HP >= 100){ Lvl_HP = 100;}
    }
    public void ReduceHP(int points){
        Lvl_HP -= points;
        if (Lvl_HP <= 0){ Lvl_HP = 0;}
    }    
    
    
    
    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
    public Shelter getShelter() {
        return shelter;
    }
    
    
    public void moveToObjective() {
        if (Lvl_stamina <= 0){CanMove = false; this.objective = null;} else {CanMove = true;}       
        if (CanMove && objective != null){
            if (this.WorldX <= this.objective.WorldX){ this.WorldX += this.speed; }
            if (this.WorldX >= this.objective.WorldX){ this.WorldX -= this.speed; }
            if (this.WorldY <= this.objective.WorldY){ this.WorldY += this.speed; }    
            if (this.WorldY >= this.objective.WorldY){ this.WorldY -= this.speed; }
        }
    }
    public boolean moveToPos(){
        
        if (Lvl_stamina <= 0){CanMove = false;}
        
        while ((pos_x - this.WorldX)% this.speed != 0){
            pos_x ++;
        }
        
        while ((pos_y - this.WorldY) % this.speed != 0){
            pos_y ++;
        }
        
        if (CanMove && !inPosition && objective == null){
            if (this.WorldX < pos_x){ this.WorldX += this.speed;}
            if (this.WorldX > pos_x){ this.WorldX -= this.speed;}
            if (this.WorldY < pos_y){ this.WorldY += this.speed;}
            if (this.WorldY > pos_y){ this.WorldY -= this.speed;}
        } 
        if (this.WorldX == pos_x && this.WorldY == pos_y) {inPosition = true; 
        this.Order = "";
        this.objective = null;
        this.gp.Item_touched = null;
        return true;} 
        else {return false;}
        
    }

    public int getLvl_stamina() {
        return Lvl_stamina;
    }
    public int getLvl_HP() {
        return Lvl_HP;
    }
        
    @Override
    public String getString(){return this.name;}
    
    public String ToString() {return "Character";}
    
    public void Write_Journal(String Event){journal = journal+Event+"\n";}
    public String Read_Journal(){ return this.journal;}
    
    
}
