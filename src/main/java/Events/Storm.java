/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Events;
import java.util.ArrayList;
import com.mycompany.proyectsurvival.GamePanel;
import Entities_Resources_Shelter.Shelter;
import Entities_Resources_Shelter.Character;

/**
 *
 * @author Brian Ramirez
 */
public class Storm {
    int days;
    String type;
    GamePanel gp;
    ArrayList<Shelter> shelters_in_map;
    ArrayList<Character> characters_in_map;

    public Storm(GamePanel gp,String type,int days) {
        this.gp = gp;
        this.days = days;
        this.type=type;
        this.shelters_in_map = gp.shelters_in_map;
        this.characters_in_map = gp.characters;
    }


    
    void attackShelters(){
        
        for (Shelter shelter : shelters_in_map) {
            if(shelter.getStability()>70 && shelter.getStability()<100){
                for (Character character : shelter.getCharacters()) {
                    character.ReduceStamina(5);
                }
            }
            else if(shelter.getStability()>30 && shelter.getStability()<70){
                for (Character character : shelter.getCharacters()) {
                    character.ReduceStamina(15);
                }
            }
            else if(shelter.getStability()<30 ){
                for (Character character : shelter.getCharacters()) {
                    character.ReduceStamina(25);
                }
            }
          
            if(type.equals("Strong Storm")){
                shelter.reduceStability(40);
            }else if(type.equals("Weak Storm")){
                shelter.reduceStability(20);
            }
        }
    }
    
    void attackChacters(){
        for (Character character : characters_in_map) {
            if (character.getShelter()==null){
            character.ReduceStamina(15);
            character.ReduceHP(10); 
            }
        }         
    }
    
    public void provokeStormEffects(){
        
        this.attackChacters();
        this.attackShelters();
    }

    public int getDays() {
        return days;
    }

    
    @Override
    public String toString() {
        return "Storm{" + "days=" + days + ", type=" + type + ", gp=" + gp + '}';
    }
    
    
}
