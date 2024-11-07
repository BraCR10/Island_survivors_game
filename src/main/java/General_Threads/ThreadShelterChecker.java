/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General_Threads;

import Entities_Resources_Shelter.Shelter;
import Events.Storm;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class ThreadShelterChecker extends Thread{
    boolean isRunning=true;
    boolean isPaused=false;
    private ArrayList<Shelter> shelters_in_map;  

    public ThreadShelterChecker(ArrayList<Shelter> shelters_in_map) {
        this.shelters_in_map = shelters_in_map;
    }
    
    
   
    @Override
    public void run() {
        while (isRunning) {
            try {
                   for (Shelter shelter : shelters_in_map) {
                    if(shelter.getStability()<=0){
                        shelter.destroy();
                        break;
                    }
                }
                sleep(800);
            } catch (Exception e) {
            }
            
            while (isPaused) {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) { }
                
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
