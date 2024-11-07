package General_Threads;

import java.util.ArrayList;
import Entities_Resources_Shelter.Shelter;
import Entities_Resources_Shelter.Character;
import javax.swing.JTextField;

public class ThreadDays extends Thread{
    private boolean isRunning=true;
    private boolean isPaused=false;
    private JTextField day;
    private int cont=0;//Increase day in the screen
    ArrayList<Shelter> shelters_in_map;
    ArrayList<Character> characters;

    public ThreadDays(JTextField day, ArrayList<Shelter> shelters_in_map, ArrayList<Character> characters) {
        this.day = day;
        this.shelters_in_map = shelters_in_map;
        this.characters = characters;
    }

 
    

    @Override
    public void run() {
        while (isRunning) {
            
                day.setText(String.valueOf(cont));
                try {
                    sleep(30000);
                    this.affectCharacters();
                    this.affectShelters();
                    cont++;
                    
                } catch (InterruptedException ex) {
                }
            while (isPaused) {
               try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                }
                
            }
            
            
        }
        
    }
    private void affectShelters(){
        for (Shelter shelter : shelters_in_map) {
            shelter.reduceStability(5);
        }
        
    }
    private void affectCharacters(){
        for (Character character : characters) {
            character.ReduceHP(5);
            
        }
    }
    
    public void increaseDays(){
        this.cont++;
         day.setText(String.valueOf(cont));
        this.affectCharacters();
        this.affectShelters();
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

    public int getCont() {
        return cont;
    }
    
    
    
    
}
