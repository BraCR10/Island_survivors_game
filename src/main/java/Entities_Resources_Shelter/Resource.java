package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;

public class Resource extends Entity{
    
    private String type;
    private int amount;
    public boolean collision = false;

    public Resource(GamePanel gp, String type, int amount) {
        super(gp);
        this.speed = 0;
        this.type = type;
        this.amount = amount;
    }

    
    public void UseResource( int amo){
        this.setAmount(this.getAmount() - amo);
        if (this.getAmount() == 0){this.gp.Selected().inventory.remove(this);}
    }
    
    
    public void AddResource(){}

    public String getType() {
        return type;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String getString() {
        return this.type;
    }

    @Override
    public String toString() {
        if (this == null) {return "....";}
        return this.type.toUpperCase()+".... ["+this.amount+"]";
    }
    
    
 

}
