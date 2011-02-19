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
import javax.sound.sampled.*;
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

    private Wrinkle wrinkle;
    private GameObjects gameObjects;
    private BufferedImage[] backgrounds;
    private BufferedImage buff;
    private boolean running;
    private Graphics2D buffg;
    private Graphics2D panel;
    private AffineTransform at;
    private Clip bgm;

    public Game()
    {
//        try{
//        bgm=Global.makeClip("Data/audio/bgm.wav");
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        bgm.loop(Clip.LOOP_CONTINUOUSLY);
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
        buffg       = buff.createGraphics();
        buffg.setBackground(Color.cyan);

        running=true;

        wrinkle=new Wrinkle(Global.WinX/4+1,Global.WinY-200);

        gameObjects.add(new Terrain(0,Global.GroundLevel,
                            400,400,Color.GREEN));
        
        gameObjects.add(new DieBox(0,Global.WinY,Global.WinX,Global.WinY));
        gameObjects.add(new Terrain(500,Global.GroundLevel,300,400,Color.RED));
        gameObjects.add(new Terrain(300,400,100,100));
        gameObjects.add(new Terrain(200,200,50,50));
        gameObjects.add(new Terrain(Global.WinX,Global.GroundLevel,400,400,Color.MAGENTA));
        gameObjects.add(new Terrain(1200,Global.GroundLevel,400,400,Color.GREEN));
        gameObjects.add(new Terrain(1800,Global.GroundLevel,400,400,Color.GREEN));
        gameObjects.add(new Bird(600,0));
    }

    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void paint(Graphics g)
    {

        Graphics2D g1=(Graphics2D)g;
        g1.setBackground(Color.CYAN);
        g1.clearRect(0, 0, Global.WinX, Global.WinY);
        g1.drawImage(buff, null, 0, 0);
    }
    public void keyPressed(KeyEvent e)
    {
        int key=e.getKeyCode();

        if(key==Config.Jump)
        {
            wrinkle.jump();
        }
        else if(key==Config.MoveRight)
        {
            wrinkle.goRight();
        }
        else if(key==Config.MoveLeft)
        {
            wrinkle.goLeft();
        }
        else if(key==Config.BreatheFire)
        {
            wrinkle.breatheFire();
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key=e.getKeyCode();

        if(key==Config.MoveRight)
        {
            wrinkle.unGoRight();
        }
        else if(key==Config.MoveLeft)
        {
            wrinkle.unGoLeft();
        }
        else if(key==Config.BreatheFire)
        {
            wrinkle.unBreatheFire();
        }
    }

    void drawBackground()
    {
        
        //buffg.clearRect(0,0,Global.WinX,Global.WinY);
        
        for(int i=0;i<backgrounds.length;++i)
        {
           float x=-(Global.coeff[i]*Global.OffsetX)%Global.WinX;
           float y=-(Global.coeff[i]*Global.OffsetY);
           

           if(x>=0)
           {
               x-=Global.WinX;
           }
           at.setToTranslation(x,y);
           buffg.setTransform(at);
           buffg.drawImage(backgrounds[i],0,0,this);
        }
        at.setToIdentity();
        buffg.setTransform(at);       

    }

    void drawToForeground()
    {
        //buffg.clearRect(0, 0, Global.WinX, Global.WinY);
        at.setToTranslation(-Global.OffsetX,-Global.OffsetY);
        buffg.setTransform(at);
        
        gameObjects.draw(buffg);

        wrinkle.draw(buffg);

    }

    void go() throws DeadException
    {
    
    gameObjects.update();    
    wrinkle.update(gameObjects);
    
    drawBackground();
    drawToForeground();
    paintImmediately(0,0,Global.WinX,Global.WinY);
    }
    boolean loop()
    {
        System.out.println("game start");
       while(running)
       {
            long time=System.currentTimeMillis();

            try
            {
                go();
           }
            catch(DeadException e)
            {
                this.dispose();
                return true;
            }
            
            time=System.currentTimeMillis()-time;
            //System.out.println("time elapsed on that frame: "+time);
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
        return false;
    }
}
