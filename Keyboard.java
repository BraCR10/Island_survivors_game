package com.mycompany.proyectsurvival;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    
    public boolean moveUP, moveDOWN, moveRIGHT, moveLEFT;
    
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W){moveUP = true;}
        if (code == KeyEvent.VK_S){moveDOWN = true;}
        if (code == KeyEvent.VK_A){moveLEFT = true;}
        if (code == KeyEvent.VK_D){moveRIGHT = true;}
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W){moveUP = false;}
        if (code == KeyEvent.VK_S){moveDOWN = false;}
        if (code == KeyEvent.VK_A){moveLEFT = false;}
        if (code == KeyEvent.VK_D){moveRIGHT = false;}
    }
}
