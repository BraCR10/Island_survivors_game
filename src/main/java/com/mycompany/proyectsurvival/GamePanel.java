package com.mycompany.proyectsurvival;

import Entities_Resources_Shelter.Animals;
import Entities_Resources_Shelter.Builder;
import Entities_Resources_Shelter.Character;
import Entities_Resources_Shelter.Explorer;
import Entities_Resources_Shelter.Fog;
import Entities_Resources_Shelter.Player;
import Entities_Resources_Shelter.Mushrooms;
import Entities_Resources_Shelter.Fruit;
import Entities_Resources_Shelter.Gatherer;
import Entities_Resources_Shelter.Hunter;
import Entities_Resources_Shelter.Medic;
import Entities_Resources_Shelter.Rocks;
import Entities_Resources_Shelter.Scientist;
import Entities_Resources_Shelter.Shelter;
import Entities_Resources_Shelter.Tree;
import Events.Accident;
import Events.Illnesses;
import Events.Storm;


import General_Threads.ThreadAnimals;
import General_Threads.ThreadDays;
import General_Threads.ThreadIllness;
import General_Threads.ThreadShelterChecker;
import General_Threads.ThreadStorm;

import Tiles.TileManager;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable{
    // Main Screen Settings
    
    public Menu menu;  
    
    private int default_size = 16;
    private int scale = 2;
    
    public int size = default_size * scale;
    public int maxScreenCol = 22;
    public int maxScreenRow = 18;
    public int ScreenWidth = size * maxScreenCol;
    public int ScreenHeight = size * maxScreenRow;
    
    public int maxWorldCol = 30;
    public int maxWorldRow = 30;
    public int maxWorldWidth = size * maxWorldCol;
    public int maxWorldHeight = size * maxWorldRow;
    
    public int mouseWorldX,mouseWorldY;
    public int ScreenX, ScreenY;
    
    
    public TileManager tile_manager = new TileManager(this);
    public Keyboard key = new Keyboard(this);
    public Entities_Resources_Shelter.Entity Item_touched;
    public String Resource_Selected = "";
    
    Thread thread;
    public AssetSetter aSetter = new AssetSetter(this);
    
    //Player
    public Player player = new Player(this,key);

    //Resources/Shelters/Animals in Map
    public ArrayList<Tree> Trees_in_map; 
    public ArrayList<Fruit> Fruits_in_map;
    public ArrayList<Mushrooms> Mushrooms_in_map;
    public ArrayList<Rocks> Rocks_in_map;
    public ArrayList<Shelter> shelters_in_map;  
    public ArrayList<Fog> fog;
    
    public ImageIcon icon;
    
    //Animals
    public ArrayList<Animals> animals_in_map;
    public ArrayList<ThreadAnimals> threadAnimals;

    //Characters
    public Explorer explorer = new Explorer(75, this, key, "Explorer");
    public Hunter hunter = new Hunter(75, this, key, "Hunter");
    public Gatherer gatherer = new Gatherer(75, this, key, "Gatherer");
    public Builder builder = new Builder(75, this, key, "Builder");
    public Medic medic = new Medic(75,this,key,"Medic");
    public Scientist scientist = new Scientist(75, this, key,"Scientist");
    
    
    //List of characters
    public ArrayList<Character> characters;
  
    //Days
    public ThreadDays timeManager;
  
   
    int FPS = 20;
    
    //*******************************************************************
     
    public GamePanel() {
        mouseWorldX = 15 * size;
        mouseWorldY = 15 * size;
        
        //Panel properties
        this.menu = new Menu(this);
        this.setSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(new Color(21, 108, 153));
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        this.addMouseListener(key);
        this.addMouseMotionListener(key);
        
        
        //Set lists of resources
        this.characters = new ArrayList<>();
        this.Trees_in_map = new ArrayList<>();
        this.Fruits_in_map = new ArrayList<>();
        this.Mushrooms_in_map = new ArrayList<>();
        this.Rocks_in_map = new ArrayList<>();
        this.shelters_in_map = new ArrayList<>();
        this.fog = new ArrayList<>();
        
        //Set lists of animals
        this.animals_in_map=new ArrayList<>();
                
        //Thread animals
        this.threadAnimals=new ArrayList<>();
        
        //Thread days
        this.startDays();
        
        //Thread to destroy shelters if stability =0
        this.startShelterChecker();
      
    }

    public void SetupGame (){
        
        //Insert Characters in the list
        this.characters.add(0,this.explorer);
        this.characters.add(1,this.hunter);
        this.characters.add(2,this.gatherer);
        this.characters.add(3,this.builder);
        this.characters.add(4,this.medic);
        this.characters.add(5,this.scientist);
        
        //Set Objects
        aSetter.setAllAssets();

        

    }
    
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D image = (Graphics2D)g;
        tile_manager.draw(image);
        
        aSetter.drawAssets(image);
        
        player.draw(image);
        for (Character c : characters) {c.draw(image);}
        
        for (int i = 0; i < fog.size(); i++){
            if (fog.get(i) != null){
                fog.get(i).draw(image);
            }
        }
        
        image.dispose();

    }    
    
    public void update(){
        this.MenuUpdate();

        player.update();
        for (Character c : characters) {c.update();}
               
        aSetter.updateAssets();
        
        HandelMouseInput();
    }  
    
   
    
    public void HandelMouseInput(){
        
        if (key.mouse_Clicked){
            this.requestFocus();
            System.out.println(this.ScreenX+", "+this.ScreenY+"-->( "+this.mouseWorldX/this.size+","+this.mouseWorldY/this.size+" )");
            this.Selected().pos_x = this.mouseWorldX;
            this.Selected().pos_y = this.mouseWorldY;
        }
        key.ClearMouseClick();
    }
    
    public void run(){
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long LastTime = System.nanoTime();
        long currentTime;
        
        while (thread != null){
            
            
            
           currentTime = System.nanoTime();    
           delta += (currentTime - LastTime)/ drawInterval;
           LastTime = currentTime;
           
           if (delta >= 1) {
               this.update();
               this.repaint();
               delta--;
           }
        }
    }

    public void MenuUpdate(){
        
        icon = new ImageIcon(getClass().getResource("/"+this.Selected().getString()+".png"));        
        this.menu.jLabel_Char.setIcon(icon);
        
        if (this.Selected().objective != null){
        this.Selected().objective.WorldX = this.mouseWorldX;
        this.Selected().objective.WorldY = this.mouseWorldY;}

        this.menu.HealthBar.setValue(this.Selected().getLvl_HP());
        this.menu.StaminaBar.setValue(this.Selected().getLvl_stamina());
        
        this.setInventory();
        if (this.Item_touched != null){
        this.menu.jLabel_Objective.setText("Obj.: >>> "+this.Item_touched.getString());}
        else {this.menu.jLabel_Objective.setText("Obj.: >>> ");}
        
        this.menu.Journal.setText(this.Selected().Read_Journal());
        
        if (this.Selected().getShelter() == null){this.menu.jLabel_Shelter.setText("NO shelter");}
        else {this.menu.jLabel_Shelter.setText("Shelter ["+this.Selected().getShelter().WorldX/this.size+", "+this.Selected().getShelter().WorldY/this.size+"]");}

    }
    
    public Character Selected(){
        return this.characters.get(this.menu.BOXSelectedCharacter.getSelectedIndex());
    }
    
    
    private void setInventory(){
              
        if (this.Selected().getResource("Meat") != null){this.menu.jLabel_MEAT.setText(this.Selected().getResource("Meat").toString());
        } else {this.menu.jLabel_MEAT.setText("EMPTY !!!");}
        if (this.Selected().getResource("Rocks") != null){this.menu.jLabel_ROCKS.setText(this.Selected().getResource("Rocks").toString());
        }else {this.menu.jLabel_ROCKS.setText("EMPTY !!!");}
        if (this.Selected().getResource("Wood") != null){this.menu.jLabel_WOOD.setText(this.Selected().getResource("Wood").toString());
        }else {this.menu.jLabel_WOOD.setText("EMPTY !!!");}
        if (this.Selected().getResource("Fruits") != null){this.menu.jLabel_FRUITS.setText(this.Selected().getResource("Fruits").toString());
        }else {this.menu.jLabel_FRUITS.setText("EMPTY !!!");}
        if (this.Selected().getResource("Mushrooms") != null){this.menu.jLabel_MUSHROOMS.setText(this.Selected().getResource("Mushrooms").toString());
        }else {this.menu.jLabel_MUSHROOMS.setText("EMPTY !!!");}
        if (this.Selected().getResource("Potions") != null){this.menu.jLabel_POTIONS.setText(this.Selected().getResource("Potions").toString());
        }else {this.menu.jLabel_POTIONS.setText("EMPTY !!!");}
        if (this.Selected().getResource("Tools") != null){this.menu.jLabel_TOOLS.setText(this.Selected().getResource("Tools").toString());
        }else {this.menu.jLabel_TOOLS.setText("EMPTY !!!");}
    }
    
    private void startDays(){
        this.menu.text_days.setEditable(false);
        timeManager = new  ThreadDays(this.menu.text_days,this.shelters_in_map,this.characters);
        timeManager.start();
    
    }
    
    public void createStrom(int durationDays, String type){
        if(type.equals("Weak Storm")||type.equals("Strong Storm")){
            Storm storm = new Storm(this,type,durationDays);
            ThreadStorm threadStorm = new  ThreadStorm(storm, timeManager);
            threadStorm.start();
            //To review 
            System.out.println("A "+ type.toLowerCase()+" has started for "+durationDays+" days!\n"
                    + "All those without shelter will be affected!");
        }
        
    }
    private void startShelterChecker(){
        ThreadShelterChecker threadShelterChecker = new ThreadShelterChecker(this.shelters_in_map);
        threadShelterChecker.start();
    }
    public void generateAccident(){
        Accident accident = new Accident(this);
        accident.generateDamage();
    }
    
    public void createIllness( String type){
        if(type.equals("Mild Illness")||type.equals("Severe Illness")){
            Illnesses illnesses = new  Illnesses(type, this);
            ThreadIllness threadIllness = new  ThreadIllness( illnesses, timeManager);
            threadIllness.start();
        }
        
    }
    
}
