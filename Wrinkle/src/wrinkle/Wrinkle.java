/*
 * Wrinkle.java
 *
 * Created on February 8, 2011, 8:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package wrinkle;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Point;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 * Class that defines the player character
 * @author a.bresee
 */
public final class Wrinkle extends Actor {

   private boolean biting;
   private boolean breathingFire;


   private LinkedList<Fire> fireList;
   private final Object fireListLock;

    /**
     *Enum to flag what 'copy mode' wrinkle is using
     *@author a.bresee
     */
    private enum JobMode{normal,bird,dragon};
    private JobMode m;
    
    
    /**
     *Default ctor calls parameterized ctor
     */
    Wrinkle() {
        this(0, 0);
    }

    /**
     * parameterized ctor takes pixel coords on where top left corner should be
     */
    Wrinkle(int X, int Y) {
        super("hero", X, Y);
        fireList=new LinkedList<Fire>();
        fireListLock=new Object();
        mass = 1;
        m=JobMode.normal;
        biting=false;
        breathingFire=false;
        health=3;
    }

    /**
     *Defines behavior of "jump" action. Called when player jumps.
     */
    void hurt()
    {
        if (health>0)
        {
            --health;
        }
        else
        {
            die();
        }

    }
    void die()
    {
        velX=0;
        velY=0;
        accelX=0;
        accelY=0;
        onTheGround=false;
        ignoreCollision=true;
        
    }

   
    /**
     *Defines behavior of "right" action. Called when player presses the 
     *"right" key.
     */
    void goRight()
    {
        if (!goingRight)
        {
            curSprite = rightWalk[0];
            frame = 0;
            timecount = 0;
            facingLeft = false;
            goingRight = true;
            goingLeft = false;
        }
    }
    /**
     *Cleans up after the "right" action. Called when player releases the 
     *"right" key.
     */
    void unGoRight()
    {
        goingRight = false;
    }

    /**
     *Defines behavior of "left" action. Called when player presses the 
     *"left" key.
     */
    void goLeft()
    {
        if (!goingLeft) {
            curSprite = leftWalk[0];
            frame = 0;
            timecount = 0;
            facingLeft = true;
            goingLeft = true;
            goingRight = false;
        }
    }

    /**
     *Cleans up after the "left" action. Called when player releases the 
     *"left" key.
     */
    void unGoLeft()
    {
        goingLeft = false;
    }
    

    void correctOffsets()
    {
        if (x - Global.OffsetX > (Global.WinX / 2))
        {
            Global.OffsetX = x - Global.WinX / 2;
        } 
        
        else if (x - Global.OffsetX < (Global.WinX / 4))
        {
            Global.OffsetX = x - Global.WinX / 4;
        }
        
        if (y - Global.OffsetY < (Global.WinY / 2))
        {
            Global.OffsetY = y - Global.WinY / 2;
        } 
        
        else if ((y - Global.OffsetY + curSprite.getHeight()) > Global.WinY - 50)
        {
            Global.OffsetY = (y + curSprite.getHeight()) - (Global.WinY - 50);
        }
    }
    void fire()
    {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b  = a.getLocation();
        double x_ = b.getX();
        double y_ = b.getY();
        double delx = x_-(x-Global.OffsetX);
        double dely = y_-(y-Global.OffsetY);

        double angle=Math.atan2(dely,delx);
        //double mag=Math.sqrt(delx*delx+dely*dely);
        float velx_=(float)Math.cos(angle);
        float vely_=(float)Math.sin(angle);

        System.out.println("angle: "+angle+"\nvely: "+vely_+"\nvelx: "+velx_);

        fireList.add(new Fire(x+getWidth()-5,y+12,velx_+velX,vely_+velY,angle));
    }
    void breatheFire()
    {
       breathingFire=true;
    }
    void unBreatheFire()
    {
        breathingFire=false;
    }

    @Override
    void update(GameObjects go)
    {
        super.update(go);
        if(breathingFire)
        {
            fire();
        }
        LinkedList<Fire> bab2=new LinkedList<Fire>();
        for(Fire i:fireList)
        {
            if(!i.isDead())
            {
                i.update(go);
                bab2.add(i);
            }

        }
        
        fireList=bab2;
        
        correctOffsets();
        //System.out.println("wrinkle x: "+x+"\nwrinkle y: "+y);
    }
    @Override
    void draw(Graphics2D g)
    {
        super.draw(g);
        synchronized(fireListLock)
        {
            for(Fire i:fireList)
            {
                i.draw(g);
            }
        }
        
    }

    @Override
    boolean roar(Actor a)
    {
        if (this.isFacing(a))
        {
         if(biting)
         {
            a.hurt();
            return false;
         }
        }
        return true;
    }
   
    
}
