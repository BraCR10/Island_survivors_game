
package com.mycompany.proyectsurvival;

import Entities_Resources_Shelter.Animals;
import Entities_Resources_Shelter.Crocodiles;
import Entities_Resources_Shelter.Crab;
import Entities_Resources_Shelter.Deer;
import Entities_Resources_Shelter.Fog;
import Entities_Resources_Shelter.Fruit;
import Entities_Resources_Shelter.Jaguar;
import Entities_Resources_Shelter.Mushrooms;
import Entities_Resources_Shelter.Rocks;
import Entities_Resources_Shelter.Shelter;
import Entities_Resources_Shelter.Snake;
import Entities_Resources_Shelter.Tree;
import Entities_Resources_Shelter.Turtle;
import General_Threads.ThreadAnimals;
import java.awt.Graphics2D;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    
    //Resources in map
    Tree new_tree;
    Fruit new_fruit;
    Mushrooms new_mushroom;
    Rocks new_rock;
    Fog fog;
    
    //Animals in map
    Snake new_snake;
    Jaguar new_jaguar;
    Turtle new_turtle;
    Crab new_crab;
    Deer new_deer;
    Crocodiles new_cocodrile;
    Shelter new_shelder;
    
    //Animals threads
    ThreadAnimals threadAnimals;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setAllAssets(){
        Random rand = new Random();
        
        int rand_x;
        int rand_y;
        int rand_subclass;

        this.insertFog();
        
        for(int i = 0; i < 30; i++){
            
            rand_subclass = rand.nextInt(1,4);
            
            rand_x = rand.nextInt(4, 27);
            rand_y = rand.nextInt(4, 27);
            new_tree = new Tree(gp, 5, rand_subclass);
            new_tree.WorldX = rand_x * gp.size;
            new_tree.WorldY = rand_y * gp.size;
            gp.Trees_in_map.add(new_tree);

            
            rand_x = rand.nextInt(4, 27);
            rand_y = rand.nextInt(4, 27);
            new_fruit = new Fruit(gp, 5, rand_subclass);
            new_fruit.WorldX = rand_x * gp.size;
            new_fruit.WorldY = rand_y * gp.size;
            gp.Fruits_in_map.add(new_fruit);
            
            rand_subclass = rand.nextInt(1,3);
            
            rand_x = rand.nextInt(4, 27);
            rand_y = rand.nextInt(4, 27);
            new_mushroom = new Mushrooms(gp, 5, rand_subclass);
            new_mushroom.WorldX = rand_x * gp.size;
            new_mushroom.WorldY = rand_y * gp.size;
            gp.Mushrooms_in_map.add(new_mushroom);
            
            
            rand_x = rand.nextInt(4, 27);
            rand_y = rand.nextInt(4, 27);
            new_rock = new Rocks(gp, 5, rand_subclass);
            new_rock.WorldX = rand_x * gp.size;
            new_rock.WorldY = rand_y * gp.size;
            gp.Rocks_in_map.add(new_rock);
        }  
        
        //Creats Snakes [+8]
        for (int i = 0; i < 8; i++) { 
            new_snake=new Snake(gp,gp.key);
            gp.animals_in_map.add(new_snake); 
            threadAnimals=new ThreadAnimals(new_snake,gp);
            gp.threadAnimals.add(threadAnimals);
            
        }
        
        //Creats Crabs [+10]
        for (int i = 0; i < 10; i++) {
            new_crab=new Crab(gp,gp.key);
            gp.animals_in_map.add(new_crab);
            threadAnimals=new ThreadAnimals(new_crab,gp);
            gp.threadAnimals.add(threadAnimals); 
        }
        
        // Creats Deers [+20]
        for (int i = 0; i < 20; i++) {
            new_deer=new Deer(gp,gp.key);
            gp.animals_in_map.add(new_deer);
            threadAnimals=new ThreadAnimals(new_deer,gp);
            gp.threadAnimals.add(threadAnimals);  
        }
        
        // Creats Crocodiles [+5]
        for (int i = 0; i < 5; i++) {
            new_cocodrile=new Crocodiles(gp,gp.key);
            gp.animals_in_map.add(new_cocodrile);
            threadAnimals=new ThreadAnimals(new_cocodrile,gp);
            gp.threadAnimals.add(threadAnimals);  
        }
        
        // Creats Trutles [+9]
        for (int i = 0; i < 9; i++) {
            new_turtle=new Turtle(gp,gp.key);
            gp.animals_in_map.add(new_turtle);
            threadAnimals=new ThreadAnimals(new_turtle,gp);
            gp.threadAnimals.add(threadAnimals);  
        }
        
        // Creats Jaguars [+3]
        for (int i = 0; i < 3; i++) {
            new_jaguar=new Jaguar(gp,gp.key);
            gp.animals_in_map.add(new_jaguar);
            threadAnimals=new ThreadAnimals(new_jaguar,gp);
            gp.threadAnimals.add(threadAnimals);  
        }
        //Start threads
        for (ThreadAnimals animals : gp.threadAnimals) {
            animals.start();
        }
        
        
        // Creats ONE new shealter
        new_shelder=new Shelter("First", gp, 15, 15);
        gp.shelters_in_map.add(new_shelder);
        new_shelder.AddCharacter(gp.explorer);
        
        /*
        new_shelder.AddCharacter(gp.builder);
        
        new_shelder.AddCharacter(gp.scientist);
        new_shelder.AddCharacter(gp.hunter);
        */


    }
    
    public void drawAssets(Graphics2D g2){
    
        
        for (Fruit f : gp.Fruits_in_map){
            if (f != null){
                f.draw(g2);
            }
        }
        for (Mushrooms m : gp.Mushrooms_in_map){
            if (m != null){
                m.draw(g2);
            }
        }
        
        for (Rocks r : gp.Rocks_in_map){
            if (r != null){
                r.draw(g2);
            }
        }
        for (Animals animal  : gp.animals_in_map){
            if (animal != null){
                animal.draw(g2);
            }
        }
        
        for (Tree t : gp.Trees_in_map){
            if (t != null){
                t.draw(g2);
            }
        }
        
        for (Shelter shelder  : gp.shelters_in_map){
            if (shelder != null){
                shelder.draw(g2);
            }
        }
 
    }
    
    public void updateAssets(){
        
        for (Fog f : gp.fog){
            if (f != null){
                f.update();
            }
        } 
    
        for (Tree t : gp.Trees_in_map){
            if (t != null){
                t.update();
            }
        }
        for (Fruit f : gp.Fruits_in_map){
            if (f != null){
                f.update();
            }
        }
        for (Mushrooms m : gp.Mushrooms_in_map){
            if (m != null){
                m.update();
            }
        }
        for (Rocks r : gp.Rocks_in_map){
            if (r != null){
                r.update();
            }
        }
        for (Animals animal: gp.animals_in_map){
            if (animal != null){
                animal.update();
            }
        }
        for (Shelter shelder  : gp.shelters_in_map){
            if (shelder != null){
                shelder.update();
            }
        }       
    }
    
    private void insertFog() {
    // Creats Fog around all the map
    for (int x = 0; x < 30; x++) {
        for (int y = 0; y < 30; y++) {
            if ((x >= 13 && x <= 17) && (y >= 13 && y <= 17)) {
                continue;
            }
            fog = new Fog(gp);
            fog.SetXY(x, y);
            gp.fog.add(fog);
        }
    }
}

}