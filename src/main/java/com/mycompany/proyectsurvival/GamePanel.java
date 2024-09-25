package com.mycompany.proyectsurvival;
import com.mycompany.proyectsurvival.EntitiesResourcesShelter.Entity;
import com.mycompany.proyectsurvival.EntitiesResourcesShelter.Explorer;
import com.mycompany.proyectsurvival.EntitiesResourcesShelter.Player;
import com.mycompany.proyectsurvival.EntitiesResourcesShelter.Resource;
import com.mycompany.proyectsurvival.tiles.TileManager;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;


public class GamePanel extends JPanel implements Runnable{
    // Main Screen Settings
    int default_size = 16;
    int scale = 2;
    
    public int size = default_size * scale;
    public int maxScreenCol = 22;
    public int maxScreenRow = 18;
    public int ScreenWidth = size * maxScreenCol;
    public int ScreenHeight = size * maxScreenRow;
    
    public int maxWorldCol = 30;
    public int maxWorldRow = 30;
    public int maxWorldWidth = size * maxWorldCol;
    public int maxWorldHeight = size * maxWorldRow;
    
    
    TileManager tileM = new TileManager(this);
    Keyboard key = new Keyboard();
    Thread thread;
    public CollisionChecker Cchecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    
    
    public Player God = new Player(this,key);
    public Resource obj[] = new Resource[30];
    public Explorer explorer = new Explorer(25, this, key, "Explorer");
            
    
    
    
    int FPS = 30;
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void SetupGame (){
        aSetter.setObject();
    }
    
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D image = (Graphics2D)g;
        tileM.draw(image);
        
        for (int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(image, this);
            }
        }
              
        explorer.draw(image);
        God.draw(image);
        image.dispose();
    }    
    
    public void update(){
        God.update();
    }  
    
    public void run(){
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long LastTime = System.nanoTime();
        long currentTime;
        
        while (thread != null){
            
           currentTime = System.nanoTime();    
           
           System.out.println("Game loop running... ");
           
           delta += (currentTime - LastTime)/ drawInterval;
           LastTime = currentTime;
           
           if (delta >= 1) {
               this.update();
               this.repaint();
               delta--;
           }
        }
    }
  
}
