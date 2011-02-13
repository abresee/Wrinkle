/*
 * Game.java
 *
 * Created on February 8, 2011, 9:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


package wrinkle;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;





/**
 * Class representing the actual game simulation itself. 
 * @author a.bresee
 */



public class Game extends JPanel implements KeyListener {

    Wrinkle wrinkle;

    GameObjects gameObjects;

    BufferedImage[] backgrounds;
        
    BufferedImage buff;

    boolean bg1changed;
    boolean bg2changed;
    boolean bg3changed;

    boolean running;

    Graphics2D buffg;
    Graphics2D panel;

    AffineTransform at;

    public Game()
    {
        setIgnoreRepaint(true);
        addKeyListener(this);
        setFocusable(true);

        at=new AffineTransform();

        gameObjects=new GameObjects();

        String prefix="Data/Images/background/";
        backgrounds=new BufferedImage[3];
        try
        {
            backgrounds[2]=ImageIO.read(new File(prefix+"bg1.png"));
            backgrounds[1]=ImageIO.read(new File(prefix+"bg2.png"));
            backgrounds[0]=ImageIO.read(new File(prefix+"bg3.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

       
        buff        = new BufferedImage(Global.WinX,Global.WinY,BufferedImage.TYPE_INT_RGB);
        buffg       = (Graphics2D)buff.createGraphics();

        running=true;

        wrinkle=new Wrinkle(Global.WinX/4+1,Global.WinY-200);

        gameObjects.add(new Terrain(0,Global.WinY-200+wrinkle.getHeight(),
                            400,400,Color.GREEN));
        gameObjects.add(new Terrain(500,Global.WinY-200+wrinkle.getHeight(),
                            300,400,Color.RED));
        gameObjects.add(new Terrain(300,400,100,100));
        gameObjects.add(new Terrain(200,200,50,50));
        gameObjects.add(new Terrain(Global.WinX,Global.WinY-200+wrinkle.getHeight(),
                            400,400,Color.MAGENTA));
        gameObjects.add(new Terrain(1200,Global.WinY-200+wrinkle.getHeight(),
                            400,400,Color.GREEN));
        gameObjects.add(new Terrain(1800,Global.WinY-200+wrinkle.getHeight(),
                            400,400,Color.GREEN));
        gameObjects.add(new Bird(600,0));
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {
        int key=e.getKeyCode();

        switch(key)
        {
            case KeyEvent.VK_SPACE:
                wrinkle.jump();
                break;
            case KeyEvent.VK_RIGHT:
                wrinkle.goRight();
                break;
            case KeyEvent.VK_LEFT:
                wrinkle.goLeft();
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key=e.getKeyCode();
        if (key==KeyEvent.VK_RIGHT)
        {
            wrinkle.unGoRight();
        }
        else if(key == KeyEvent.VK_LEFT)
        {
            wrinkle.unGoLeft();
        }

    }

    void drawToForeground()
    {
        at.setToTranslation(-Global.OffsetX,-Global.OffsetY);
        buffg.setTransform(at);
        
        gameObjects.draw(buffg);

        wrinkle.draw(buffg);

    }
    

    void drawBackground()
    {
        buffg.setBackground(Color.cyan);
        buffg.clearRect(0,0,Global.WinX,Global.WinY);
        
        for(int i=0;i<backgrounds.length;++i)
        {
           float x=-(Global.coeff[i]*Global.OffsetX)%Global.WinX;
           float y=-(Global.coeff[i]*Global.OffsetY);
           

           if(x>=0)
           {
               x=x-Global.WinX;
           }
           at.setToTranslation(x,y);
           buffg.setTransform(at);
           buffg.drawImage(backgrounds[i],0,0,this);
        }
        at.setToIdentity();
        buffg.setTransform(at);       

    }
    
    void go()
    {
    
    gameObjects.update();    
    wrinkle.update(gameObjects);
    
    drawBackground();
    drawToForeground();
    panel=(Graphics2D)this.getGraphics();
    panel.drawImage(buff,0,0, this);
    }
    void loop()
    {
        System.out.println("game start");
       while(running)
       {
            long time=System.currentTimeMillis();

            go();
            
            time=System.currentTimeMillis()-time;
            System.out.println(time);
            if(time<Global.timeStep)
            {
                try
                {
                    Thread.sleep(Global.timeStep-time);
                }

                catch(Exception e)
                {
                    e.printStackTrace();
                }
           }
            
        }
    }
}
