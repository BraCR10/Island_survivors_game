package com.mycompany.proyectsurvival.EntitiesResourcesShelter;

import java.util.ArrayList;
            

public class Shelter {
    int stability;
    int capacity;
    ArrayList<Resource> materials_required;
    
    public void AddCharacter (Character character){
        character.setShelter(this);
    }
    public void RemoveCharacter (Character character){
        character.setShelter(null);
    }
    
    public int GetStability(){
       return this.stability; 
    }
    
    
    //TODO:
    public void Repair (){
        if (this.stability <= 70){
        
        }
    }
}
