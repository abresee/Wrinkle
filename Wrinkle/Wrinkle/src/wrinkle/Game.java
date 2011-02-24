/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrinkle;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.*;

/**
 *
 * @author alex
 */
public class Game extends JPanel implements KeyListener,MouseListener,MouseMotionListener
{

    Level l;
    BufferedImage q;

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
        l.setMouseLoc(e.getPoint());
    }
    public void mousePressed(MouseEvent e)
    {
        l.clickAction(e.getPoint());
    }
    public void mouseReleased(MouseEvent e)
    {
        l.unBreatheFire();
    }

    public boolean go()
    {
        l = new Level();
        q = l.getImage();
        while (true)
        {
            long time = System.currentTimeMillis();
            try
            {
                l.go();
            }
            catch (DeadException e)
            {
                return true;
            }
            paintImmediately(0,0,Global.WinX,Global.WinY);
            time = System.currentTimeMillis() - time;
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
