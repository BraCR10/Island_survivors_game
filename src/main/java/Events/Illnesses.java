package Events;
import com.mycompany.proyectsurvival.GamePanel;
import java.util.Random;

public class Illnesses {
    String type;
    GamePanel gp;
    Boolean active;
    Random rand = new Random();
    int randomIndex;
    
    public Illnesses(String type, GamePanel gp) {
        this.type = type;
        this.gp = gp;
        active=true;
        this.randomIndex=rand.nextInt(gp.characters.size());
    }
    
    public void affectCharacter(){
    if(this.type.equals("Mild Illness")&&active){
        this.gp.menu.msg_Actions.setText(gp.characters.get(randomIndex).getString()+" is slightly ill");
        gp.characters.get(randomIndex).ReduceHP(10);
    }else if(active){
        this.gp.menu.msg_Actions.setText(gp.characters.get(randomIndex).getString()+" is seriously ill");
        gp.characters.get(randomIndex).ReduceHP(20);
    }
    }
    
    public void cure(){
        for (int i = 0; i < gp.characters.size(); i++) {
            if(gp.characters.get(randomIndex).equals(gp.characters.get(i))){
                this.gp.menu.msg_Actions.setText(gp.characters.get(randomIndex).getString()+" has been cured!");
                active=false;
            }
            
        }
    }

    public Boolean getActive() {
        return active;
    }

}
   







    