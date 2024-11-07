package Events;

import com.mycompany.proyectsurvival.GamePanel;
import java.util.Random;

public class Accident {
    private Random rand=new Random(); // Declare Random object
    GamePanel gp;
    int randomIndex;
    int randomDamage;
   

    public Accident( GamePanel gp) {
        this.gp = gp;
        this.randomDamage=rand.nextInt(0,30);
        if (gp.characters.size()-1>1)
        this.randomIndex=rand.nextInt(1,gp.characters.size()-1);
    }
    
    public void generateDamage(){
        this.gp.characters.get(randomIndex).ReduceHP(randomDamage);
        this.gp.menu.msg_Actions.setText("The "+ this.gp.characters.get(randomIndex).getString()+" has suffered a accident -"+randomDamage+"HP");
        }
}

