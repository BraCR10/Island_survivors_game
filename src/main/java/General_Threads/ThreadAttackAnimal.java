/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General_Threads;
import java.util.Random;
import Entities_Resources_Shelter.Animals;
import Entities_Resources_Shelter.Character;
import Entities_Resources_Shelter.Shelter; 
import com.mycompany.proyectsurvival.GamePanel;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Brian Ramirez
 */
public class ThreadAttackAnimal extends Thread{
    Animals animal;
    Character character;
    Shelter shelter;
    boolean isRunning=true;
    boolean isPaused=false;
    GamePanel gp;
    
    
    public ThreadAttackAnimal(Animals animal,Character character, GamePanel gp) {
        this.gp = gp;
        this.animal=animal;
        this.character=character;
        this.shelter=null;
    }
    public ThreadAttackAnimal(Animals animal,Shelter shelter, GamePanel gp) {
        this.gp = gp;
        this.animal=animal;
        this.character=null;
        this.shelter=shelter;
    }
    
    /**
     *
     */
    @Override
    public void run() {
        while (isRunning) {
           try {
            if (animal.attack) {
                if (character != null) {
                    if(animal.getPower()>50 ){
                character.ReduceHP(30);
                character.ReduceStamina(25);
                }
                else{
                    character.ReduceHP(10);
                    character.ReduceStamina(10);
                    
                } 
                 this.gp.menu.msg_Actions.setText("A "+animal.getString()+" has attacked to: "+character.getString());
                animal.attack=false;
                } else if (shelter != null) {
                   if(animal.getPower()>50 ){
                   shelter.reduceStability(30);
                   //shelter.destroy();
                    this.gp.menu.msg_Actions.setText("A "+animal.getString()+" has attacked the shelter :"+shelter.getString());
                }else{
                    this.gp.menu.msg_Actions.setText("A "+animal.getString()+" has tried to attack the shelter :"+shelter.getString());
                }
                }
                 while (isPaused) {
                     try {
                         sleep(1000);
                     } catch (Exception e) { }               
                     
                }
                animal.attack = false; 
                stopTh();
            }
               sleep(10);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
    }
    
   public void  stopTh(){
       this.isRunning=false;
   }
   public boolean  isRunnig(){
       return isRunning;
   }
    public boolean isPause() {
        return isPaused;
    }

    public void resumePause() {
        this.isPaused = false;
    }
    public void pause() {
        this.isPaused = true;
    }
    
}
