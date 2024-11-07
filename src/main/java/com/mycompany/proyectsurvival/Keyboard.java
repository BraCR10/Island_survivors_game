package com.mycompany.proyectsurvival;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Keyboard implements KeyListener, MouseListener, MouseMotionListener{
    
    public boolean mouse_Clicked;
    public boolean mouse_Pressed;
    GamePanel gp;
    
    public boolean moveUP, moveDOWN, moveRIGHT, moveLEFT;
    public boolean done = false;
    
    

    public Keyboard(GamePanel gp) {
        this.gp = gp;
    }
    
    
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){moveUP = true;}
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){moveDOWN = true;}
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){moveLEFT = true;}
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){moveRIGHT = true;}
        
        if (code == KeyEvent.VK_ESCAPE){done = true;}
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){moveUP = false;}
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){moveDOWN = false;}
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){moveLEFT = false;}
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){moveRIGHT = false;}
    }
    
    public void ClearMouseClick(){mouse_Clicked = false;}
    
    public void mouseClicked(MouseEvent e) {
        mouse_Pressed = true;
        
        gp.ScreenX = e.getX();
        gp.ScreenY = e.getY();
        
        this.gp.mouseWorldX = gp.ScreenX + gp.player.WorldX - gp.player.screenX;
        this.gp.mouseWorldY = gp.ScreenY + gp.player.WorldY - gp.player.screenY;

        gp.menu.Label_pos.setText("( " + gp.mouseWorldX / gp.size + "," + gp.mouseWorldY / gp.size + " )");
        gp.Item_touched = null;
        gp.Selected().objective = null;
    }

    public void mousePressed(MouseEvent e) {}

    
    public void mouseReleased(MouseEvent e) {
        mouse_Clicked = true;
        mouse_Pressed = false;
    }

    public void mouseEntered(MouseEvent e) {gp.requestFocus();}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}
