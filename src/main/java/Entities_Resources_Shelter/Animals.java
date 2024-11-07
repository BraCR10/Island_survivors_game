package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import General_Threads.ThreadAttackAnimal;
import java.awt.Graphics2D;
import java.util.Random;



public class Animals extends Entity{
    private String type;
    private int power;
    private int difficultyHunting;
    private int  foodProvided;
    private int positionX;
    private int positionY;
    public boolean attack;
    Random  rand = new Random();

    public Animals(String type, int power, int difficultyHunting, int foodProvided, GamePanel gp) {
        super(gp);
        this.CanMove=true;
        this.type = type;
        this.power = power;
        this.difficultyHunting = difficultyHunting;
        this.foodProvided = foodProvided;
        this.speed=1;
        this.positionX=rand.nextInt(1, 27);
        this.positionY=rand.nextInt(4, 27);
        this.attack=false;
        
    }

    public void attack(Character character){
        ThreadAttackAnimal thread;
        this.objective=character;
        thread=new ThreadAttackAnimal(this,character, gp);
        thread.start();
    };
    public void attackShelter(Shelter shelter){
        ThreadAttackAnimal thread;
        this.objective=shelter;
        thread=new ThreadAttackAnimal(this,shelter, gp);
        thread.start();
    };
    //TODO:Test
    public void hunted(Hunter hunter){
        if(hunter.lvl_hunter>this.difficultyHunting){
            hunter.ReduceStamina(5);
            hunter.ReduceHP(5);
            hunter.AddResource(new Resource(gp, "Meat", this.foodProvided)); 
            deleteAnimal();
            hunter.Write_Journal("Hunt Successful...");
        }
        else{
            hunter.ReduceStamina(10);
            hunter.ReduceHP(15);
            hunter.Write_Journal("Hunt Failed...");
        }
    };
    
    private void deleteAnimal(){
        for (int i = 0; i < gp.animals_in_map.size(); i++){
            if (this.equals(gp.animals_in_map.get(i))){
                gp.animals_in_map.remove(i);
            }
        }
    
    }
    public void move() {        
        if(CanMove && this.objective != null){
            if (this.WorldX <= this.objective.WorldX){ this.WorldX += this.speed; }
            if (this.WorldX >= this.objective.WorldX){ this.WorldX -= this.speed; }
            if (this.WorldY <= this.objective.WorldY){ this.WorldY += this.speed; }    
            if (this.WorldY >= this.objective.WorldY){ this.WorldY -= this.speed; }
            if((this.WorldY > this.objective.WorldY-3 && this.WorldY < this.objective.WorldY+3) 
               && (this.WorldX > this.objective.WorldX-3)&&(this.WorldX < this.objective.WorldX+3)){
               this.objective=null;
               attack=true;
            }
        }else  if (CanMove ){
            if (this.WorldX <= positionX*gp.size){ this.WorldX += this.speed; }
            if (this.WorldX >= positionX*gp.size){ this.WorldX -= this.speed; }
            if (this.WorldY <= positionY*gp.size){ this.WorldY += this.speed; }    
            if (this.WorldY >= positionY*gp.size){ this.WorldY -= this.speed; }
        }
    }
    

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPower() {
        return power;
    }
    
    
    
     @Override
    public void update() { 
         super.update();
         this.move();
    }

    @Override
    public String getString() {
        return this.type;
    }


    public void draw(Graphics2D g2) {
        if (this.IsVisible()){
            g2.drawImage(image, screenX, screenY, gp.size, gp.size, null);
            super.setLabel(gp.size,screenX,screenY);
        } 
    }



}
