package com.mycompany.proyectsurvival;
import javax.swing.JFrame;
/**
 *
 * @author david
 */
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("---- GAME ----");
        
        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);
        window.pack();
        
        window.setLocale(null);
        window.setVisible(true);
        
        gamepanel.startThread();
    }
}
