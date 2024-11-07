package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
            

public class Shelter extends Entity{
    int stability;
    int capacity;
    String name;
    int subclass;
   
    ArrayList<Character> characters;
    //?? it isn't used
    public ArrayList<Resource> materials_required;

    public Shelter(String name,GamePanel gp, int worldX,int worldY) {
        super(gp);
        this.name=name;
        this.stability = 100;
        this.capacity = 5;
        this.materials_required = new ArrayList<>();
        this.characters = new ArrayList<>();
        
        this.WorldX=worldX * gp.size;
        this.WorldY=worldY * gp.size;
        
        this.update();

        
        try {
            this.image = ImageIO.read(new File(getPath()+"Shelders\\shelder01.png"));
        }catch(IOException e){ e.printStackTrace();}
    }
    
    public void AddCharacter (Character character){
        if(capacity<=5){
            characters.add(character);
            character.setShelter(this);
            capacity--;
        }
    }
    
    public void RemoveCharacter (Character character){
        if(capacity>=0){
            for (int i = 0; i < this.characters.size(); i++) {
                if(gp.characters.get(i).equals(characters.get(i)))
                characters.remove(i);
            }
            character.setShelter(null);
            capacity++;
        }
    }
    
    public void destroy (){
        for (int i = 0; i < gp.shelters_in_map.size(); i++) {
            if(gp.shelters_in_map.get(i).equals(this)){
                for (Character character : characters) {
                    if (character.getShelter().equals(this)) {
                       character.setShelter(null);
                    }
                }           
                gp.shelters_in_map.remove(i);
            }
    
        }
    }

    public void Repair (Builder builder){
        if (this.stability <= 70){
            for (int i = 0; i < builder.inventory.size(); i++) {
                if(builder.inventory.get(i).getType().equals("Wood")){
                    if(builder.inventory.get(i).getAmount()<10 && builder.inventory.get(i).getAmount()>=5){
                        this.increaseStability(10);
                        //Could be reduce amount in builder
                        builder.inventory.get(i).setAmount(builder.inventory.get(i).getAmount()-5);
                    }
                    if(builder.inventory.get(i).getAmount()<=15 && builder.inventory.get(i).getAmount()>10){
                        this.increaseStability(20);
                        //Could be reduce amount in builder
                        builder.inventory.get(i).setAmount(builder.inventory.get(i).getAmount()-10);
                    }
                    if(builder.inventory.get(i).getAmount()>15){
                        this.increaseStability(30);
                        //Could be reduce amount in builder
                        builder.inventory.get(i).setAmount(builder.inventory.get(i).getAmount()-15);
                    }
                }
            }
        }
    }
    
    @Override
    public String getString() {
        return "Shelter";
    }

    public int getStability() {
        return stability;
    }
    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public ArrayList<Resource> getMaterials_required() {
        return materials_required;
    }

    public void reduceStability(int pts) {
        this.stability = this.stability -pts;
    }
    public  void increaseStability(int pts ) {
        if(this.stability<=100)
            this.stability+=pts;
    }


    public void draw(Graphics2D g2) {
        if (this.IsVisible()){
            g2.drawImage(image, screenX, screenY, gp.size, gp.size, null);
            super.setLabel(gp.size,screenX,screenY);
        } 
    }
}
