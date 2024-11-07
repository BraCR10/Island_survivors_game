package Entities_Resources_Shelter;

import com.mycompany.proyectsurvival.GamePanel;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;


public abstract class Entity {
    
    GamePanel gp;
    public int WorldX,WorldY;
    public int screenX, screenY;
    
    public boolean CanMove = false;
    public Entity objective;    
    public int speed;
    public boolean inPosition = true;
    public boolean isCharacter = false;
    
    public int pos_x, pos_y;
    
    
    //IMAGE RELATED
    public JLabel label;
    public BufferedImage image;
    public final String path = "C:\\Users\\david\\Desktop\\TEC\\IC2101 - PROGRAMACIÓN ORIENTADA A OBJETOS\\ProyectSurvival\\src\\main\\res\\";
    
    // Const..........................................
    public Entity(GamePanel gp) {
        this.gp = gp;
        this.label = new JLabel();
        this.label.addMouseListener(new java.awt.event.MouseAdapter() {public void mousePressed(java.awt.event.MouseEvent evt){ LabelMousePressed(evt);}});
        

        
        gp.add(this.label);
    }    

    //Abstract.........................................
    public abstract String getString();

    // Handel Input from Keyvoard/Mouse................
    public void LabelMousePressed(java.awt.event.MouseEvent evt) {
        gp.key.mouse_Pressed = true;
        
        gp.mouseWorldX = WorldX;
        gp.mouseWorldY = WorldY; 
        
        gp.menu.Label_pos.setText("( " + gp.mouseWorldX / gp.size + "," + gp.mouseWorldY / gp.size + " )");
        
        gp.Item_touched = this;
        inPosition = true;
        System.out.println("Touched["+gp.Item_touched.getString()+"]"); 
        
        
        
    }  
    
    //.................................................
    public void setLabel(int Size, int x, int y){
        this.label.setSize(Size, Size);
        this.label.setLocation(x, y);
        
    }
    
    public boolean IsTouched(Entity entity){
       if (entity == null){return false;}
       return this.label.getBounds().intersects(entity.label.getBounds());
    }
    
    public boolean IsVisible(){
        return (WorldX + gp.size > gp.player.WorldX - gp.player.screenX &&
            WorldX -gp.size< gp.player.WorldX + gp.player.screenX &&
            WorldY +gp.size> gp.player.WorldY - gp.player.screenY &&
            WorldY -gp.size< gp.player.WorldY + gp.player.screenY);
    }
    
    public int getScreenX(){
        return (WorldX - gp.player.WorldX + gp.player.screenX); 
    }
    
    public int getScreenY(){
        return (WorldY - gp.player.WorldY + gp.player.screenY); 
    }

    public void update(){
        
        this.screenX = this.getScreenX();
        this.screenY = this.getScreenY();
    }

    public String getPath() {
        return path;
    }
   
}
