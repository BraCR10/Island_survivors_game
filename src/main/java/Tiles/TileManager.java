package Tiles;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileReader;


public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum [][];
    private final String path="C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\";

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[12];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
       
        getTilesImages();
        loadMap();
    }
    
    public void getTilesImages(){
try {
           tile[0] = new Tile();
           tile[0].image = ImageIO.read(new File(path+"Images\\grass01.png"));
           tile[0].collision = true;
           
           tile[1] = new Tile();
           tile[1].image = ImageIO.read(new File(path+"Images\\sand_nw.png"));
           tile[1].collision = true;
           
           tile[2] = new Tile();
           tile[2].image = ImageIO.read(new File(path+"Images\\sand_ne.png"));
       
           tile[3] = new Tile();
           tile[3].image = ImageIO.read(new File(path+"Images\\sand_sw.png"));
       
           tile[4] = new Tile();
           tile[4].image = ImageIO.read(new File(path+"Images\\sand_se.png"));
       
           tile[5] = new Tile();
           tile[5].image = ImageIO.read(new File(path+"Images\\sand_n.png"));
       
           tile[6] = new Tile();
           tile[6].image = ImageIO.read(new File(path+"Images\\sand_e.png"));
           
           tile[7] = new Tile();
           tile[7].image = ImageIO.read(new File(path+"Images\\sand_w.png"));
  
           tile[8] = new Tile();
           tile[8].image = ImageIO.read(new File(path+"Images\\sand_s.png"));
           
           tile[9] = new Tile();
           tile[9].image = ImageIO.read(new File(path+"Images\\sand.png"));
  
           tile[10] = new Tile();
           tile[10].image = ImageIO.read(new File(path+"Images\\grass02.png"));
  
           
           
       } catch (IOException e){
           e.printStackTrace();
       }
}
    
    public void loadMap(){
        System.out.print("[LOADING MAP]...");
        try {
            BufferedReader br = new BufferedReader(new FileReader(path+"Map\\map.txt"));

            int col = 0;
            int row = 0;
                
            String line;
            while ((line = br.readLine()) != null){
                
                String numbers[] = line.split(" ");
                for (int i = 0; i < gp.maxWorldCol; i++){
                    System.out.print(numbers[i]);
                    mapTileNum[row][i] = Integer.valueOf(numbers[i]);
                }
                System.out.println("");
                row++;           
            }
            br.close();
        } catch (Exception e){
            System.out.print("[Error] : Loading MAP :( ");
        }
    }
    
    
    public void draw (Graphics2D g){
            
        int Worldcol = 0;
        int Worldrow = 0;
        
        while (Worldcol < gp.maxWorldCol && Worldrow < gp.maxWorldRow){
            int tileNum = mapTileNum[Worldrow][Worldcol];
            
            int worldX = Worldcol * gp.size;
            int worldY = Worldrow * gp.size;
            int screenX = worldX - gp.player.WorldX + gp.player.screenX;
            int screenY = worldY - gp.player.WorldY + gp.player.screenY;
            
            if (worldX + gp.size > gp.player.WorldX - gp.player.screenX &&
                worldX -gp.size< gp.player.WorldX + gp.player.screenX &&
                worldY +gp.size> gp.player.WorldY - gp.player.screenY &&
                worldY -gp.size< gp.player.WorldY + gp.player.screenY){
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
