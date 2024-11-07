package com.mycompany.proyectsurvival;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author david
 */
public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("---- GAME ----");
        
        window.setLayout(null);
        
 
        GamePanel gamepanel = new GamePanel();
        
        gamepanel.setBounds(0, 0, gamepanel.ScreenWidth, gamepanel.ScreenHeight);
        
        gamepanel.menu.setBounds(gamepanel.ScreenWidth, 0,400,gamepanel.ScreenHeight);
        gamepanel.menu.setFocusable(false);
       
        window.setSize(gamepanel.ScreenWidth+420,gamepanel.ScreenHeight+45);
        window.setResizable(true);
        
       
       window.add(gamepanel);
       window.add(gamepanel.menu);
       
       window.setLocale(null);
       window.setVisible(true);
       
       gamepanel.SetupGame();
       gamepanel.startThread();
    }
}   
