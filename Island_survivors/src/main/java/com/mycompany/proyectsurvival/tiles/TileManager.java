package com.mycompany.proyectsurvival.tiles;

import com.mycompany.proyectsurvival.GamePanel;
import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileReader;


public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum [][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[7];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        
        getTilesImages();
        loadMap();
    }
    
    public void getTilesImages(){
       try {
           tile[0] = new Tile();
           tile[0].image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Images\\water02.png"));
       
           tile[1] = new Tile();
           tile[1].image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Images\\water01.png"));
       
           tile[2] = new Tile();
           tile[2].image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Images\\sand.png"));
       
           tile[3] = new Tile();
           tile[3].image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Images\\grass03.png"));
       
           tile[4] = new Tile();
           tile[4].image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Images\\grass02.png"));
       
           tile[5] = new Tile();
           tile[5].image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Images\\grass01.png"));
       
           tile[6] = new Tile();
           tile[6].image = ImageIO.read(new File("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Images\\soil.png"));
       
       } catch (IOException e){
           e.printStackTrace();
       }
}
    
    public void loadMap(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\Map\\map_1.txt"));

            int col = 0;
            int row = 0;
                
            String line;
            while ((line = br.readLine()) != null){
                
                String numbers[] = line.split(" ");
                for (int i = 0; i < 60; i++){
                    System.out.print(numbers[i]);
                    mapTileNum[row][i] = Integer.valueOf(numbers[i]);
                }
                row++;           
            }
            br.close();
        } catch (Exception e){
            System.out.print(">>>>>>>>>>>>>++++++++++++++");
        }
    }
    
    
    public void draw (Graphics2D g){
            
        int Worldcol = 0;
        int Worldrow = 0;
        
        while (Worldcol < gp.maxWorldCol && Worldrow < gp.maxWorldRow){
            int tileNum = mapTileNum[Worldrow][Worldcol];
            
            int worldX = Worldcol * gp.size;
            int worldY = Worldrow * gp.size;
            int screenX = worldX - gp.God.WorldX + gp.God.screenX;
            int screenY = worldY - gp.God.WorldY + gp.God.screenY;
            
            if (worldX + gp.size > gp.God.WorldX - gp.God.screenX &&
                worldX -gp.size< gp.God.WorldX + gp.God.screenX &&
                worldY +gp.size> gp.God.WorldY - gp.God.screenY &&
                worldY -gp.size< gp.God.WorldY + gp.God.screenY){
                g.drawImage(tile[tileNum].image, screenX, screenY, gp.size, gp.size, null);
            }
            Worldcol ++;

            
            if (Worldcol == gp.maxWorldCol){
                Worldcol = 0;
                Worldrow++;
            }
            
        }
    }
}
