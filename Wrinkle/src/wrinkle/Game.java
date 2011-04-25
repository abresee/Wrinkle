/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrinkle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.*;

/**
 *
 * @author alex
 */
public class Game extends Pan
{

    Level l;
    BufferedImage q;
    boolean levelinit;

    Game()
    {
        setIgnoreRepaint(true);
        this.addKeyListener(this);
        setFocusable(true);

    }
    public Level getLevel(){return l;}

    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g1=(Graphics2D)g;
        g1.setBackground(Color.CYAN);
        g1.clearRect(0,0,Global.WinX,Global.WinY);
        g1.drawImage(q,0,0,null);
    }

     public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
         if(l!=null)
         {
            if (key == Config.Jump) {
                l.jump();
            } else if (key == Config.MoveRight) {
                l.goRight();
            } else if (key == Config.MoveLeft) {
                l.goLeft();
            } else if (key == Config.Action){
                l.keyAction();
            } else if (key == Config.UnJob)
            {
                l.unJob();
            }
         }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(l!=null)
        {
            if (key == Config.MoveRight) {
                l.unGoRight();
            } else if (key == Config.MoveLeft) {
                l.unGoLeft();
            } else if (key == Config.Action) {
                l.unKeyAction();
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e)
    {
        if(levelinit)
        {
            l.setMouseLoc(e.getPoint());
        }

    }
    public void mousePressed(MouseEvent e)
    {
        if(levelinit)
        {
            l.clickAction(e.getPoint());
        }
    }
    public void mouseReleased(MouseEvent e)
    {
        if(levelinit)
        {
        l.unBreatheFire();
        }
    }

    public boolean go(int lives)
    {
        l = new Level(lives);
        levelinit=true;
        q = l.getImage();
        while (true)
        {
            long time = System.currentTimeMillis();
            
            if(!l.go())
                return false;
                        
            paintImmediately(0,0,Global.WinX,Global.WinY);
            time = System.currentTimeMillis() - time;
           // System.out.println(time);
            if (time < Global.timeStep)

            {
                try
                {
                    Thread.sleep(Global.timeStep - time);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
