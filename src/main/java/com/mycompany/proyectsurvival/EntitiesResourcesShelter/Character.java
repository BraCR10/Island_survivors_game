package com.mycompany.proyectsurvival.EntitiesResourcesShelter;
import com.mycompany.proyectsurvival.GamePanel;
import java.util.ArrayList;


public abstract class Character extends Entity{
    private String name;
    private int Lvl_stamina;
    private int Lvl_HP;
    private ArrayList<Resource> inventory;
    private Shelter shelter;
    
    
    public Character(GamePanel gp, String name) {
        super(gp);
        
        this.name = name;
        this.Lvl_stamina = 100;
        this.Lvl_HP = 100;
        this.inventory = new ArrayList<Resource>();
        this.shelter = null;
    }
    
    
    public abstract void Action();
    public abstract void Eat();
    public abstract void Rest();
    
    public void ReduceStamina(int points){
        Lvl_stamina -= points;
        if (Lvl_stamina <= 0){ Lvl_stamina = 0;}
    }
    public void ReduceHP(int points){
        Lvl_HP -= points;
        if (Lvl_HP <= 100){ Lvl_HP = 0;}
    }
    public void IncreaseStamina(int points){
        Lvl_stamina += points;
        if (Lvl_stamina >= 100){ Lvl_stamina = 100;}
    }     
    public void IncreaseHP (int points){
        Lvl_HP += points;
        if (Lvl_HP >= 100){ Lvl_HP = 100;}
    }
    public void ShareResource(Character receiver, Resource resourse) {
        int index = this.inventory.indexOf(resourse);
        receiver.inventory.add(resourse);
        this.inventory.remove(index);
    
    }

    public void AddResource(Resource resource){
        boolean found = false;
        
        for (int i = 0; i < this.inventory.size();i++){
            if (resource.getType() == this.inventory.get(i).getType()){
                found = true;
                this.inventory.get(i).setAmount(this.inventory.get(i).getAmount() + resource.getAmount());
                break;
            }    
        }
        if (!found){
            this.inventory.add(resource);
        } 
    }
    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
    
}
