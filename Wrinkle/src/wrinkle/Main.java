/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.Insets;
import java.io.IOException;

/**
 *Class that initializes the Frame and serves as the entry point for the 
 *program
 *@author a.bresee
 */
public class Main {  
    static void init(JFrame window, Pan g) 
        {

            window.add(g);
            window.getContentPane().add(g);
            window.setSize(Global.WinX,Global.WinY);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            Insets off=window.getInsets();
            int xoff=off.left+off.right;
            int yoff=off.top+off.bottom;
            window.setSize(Global.WinX+xoff, Global.WinY+yoff);
            window.validate();
            window.addKeyListener(g);
            window.addMouseListener(g);
            window.addMouseMotionListener(g);
        }
    public static void main(String[] args) throws IOException {
        
        
        
    JFrame window=new JFrame("Wrinkle... th-t-the Dinosaur!");
//
//            Menu m;
//
//            m = new Menu();
//
//
//            init(window,m);
//
//            while(m.exists())
//            {
//                //spin
//            }
//            window.removeAll();
    
            Game g=new Game();

            init(window,g);

            int lives=3;
            do{
                g.go(lives);
                --lives;
            }while(lives>0);
            window.dispose();
            
    }

}

abstract class Pan extends JPanel implements KeyListener, MouseInputListener
{

}
