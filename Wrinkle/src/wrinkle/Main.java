/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import javax.swing.JFrame;
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
    public static void main(String[] args)  {



    JFrame window=new JFrame("Wrinkle... th-t-the Dinosaur!");
    /*
            Menu m=null;
            try
            {
                m = new Menu();
                Global.menu=true;
            }
            catch(IOException e)
            {
                System.exit(20);
            }



            init(window,m);

            while(Global.menu)
            {
                if(m.musicStopped())
                {
                    System.out.println("does this happen?");
                    m.startNextTrack();
                }
            }
            m.stopMusic();
            m.setVisible(false);
            System.out.println("whoa dawg");
            window.dispose();
      */

            window=new JFrame("Wrinkle... th-t-the Dinosaur!");

            Game g=new Game();
            g.setVisible(true);

            init(window,g);

            int lives=3;
            g.go(lives);
            window.dispose();

    }

}