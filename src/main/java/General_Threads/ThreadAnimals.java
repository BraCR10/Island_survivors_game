/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General_Threads;
import java.util.Random;
import Entities_Resources_Shelter.Animals; 
import com.mycompany.proyectsurvival.GamePanel;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Brian Ramirez
 */
public class ThreadAnimals extends Thread {
    Animals animal;
    int x;
    int y;
    int randomProbably;
    int indexRandom;
    int wait;
    boolean isRunning = true;
    boolean isPaused = false;
    Random rand = new Random();
    GamePanel gp;

    public ThreadAnimals(Animals animal, GamePanel gp) {
        this.animal = animal;
        this.wait = rand.nextInt(5000, 12000); // Wait time between 5000ms and 12000ms
        this.gp = gp;
    }

    public void run() {
        while (isRunning) {
            x = rand.nextInt(0, 45);
            y = rand.nextInt(0, 45);
            randomProbably = rand.nextInt(100); // Generates a number between 0 and 99
            
            try {
                this.animal.setPositionX(x);
                this.animal.setPositionY(y);

                // Probability of 1% to attack a character
                if (randomProbably < 1) {
                    if (gp.characters.size() > 0) {
                        indexRandom = rand.nextInt(gp.characters.size());
                        this.animal.attack(gp.characters.get(indexRandom));
                        this.gp.menu.msg_Actions.setText("A " + animal.getString() + " will attack " + gp.characters.get(indexRandom).getString());
                    }
                }

                // Probability of 1% to attack a shelter
                if (randomProbably < 1) {
                    if (gp.shelters_in_map.size() > 0) {
                        indexRandom = rand.nextInt(gp.shelters_in_map.size());
                        this.animal.attackShelter(gp.shelters_in_map.get(indexRandom));
                        this.gp.menu.msg_Actions.setText("A " + animal.getString() + " will attack " + gp.shelters_in_map.get(indexRandom).getString());
                    }
                }

                sleep(wait);

            } catch (InterruptedException e) {
                e.printStackTrace(); // Log the exception if it occurs
            }

            // Pausing the thread
            while (isPaused) {
                try {
                    sleep(1000); // Sleep while paused, checking every second
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void stopTh() {
        this.isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void resumePause() {
        this.isPaused = false;
    }

    public void pause() {
        this.isPaused = true;
    }
}