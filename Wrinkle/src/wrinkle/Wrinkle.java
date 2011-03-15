/*
 * Wrinkle.java
 *
 * Created on February 8, 2011, 8:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package wrinkle;
import java.awt.Point;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 * Class that defines the player character
 * @author a.bresee
 */

enum JobMode{normal,bird,dragon};

public final class Wrinkle extends Actor {

   private boolean biting;
   private boolean breathingFire;
   private final int maxHealth;


   private AnimationCollection d;
   
   
   private LinkedList<Fire> fireList;
   private final Object fireListLock;

   private Point mouseLoc;

   private final Object lock;

    /**
     *Enum to flag what 'copy mode' wrinkle is using
     *@author a.bresee
     */
    
    
    
    
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
        set=new WrinkleAnimationSet();
        fireList=new LinkedList<Fire>();
        fireListLock=new Object();
        mass = 1;
        m=JobMode.normal;
        biting=false;
        breathingFire=false;
        health=3;
        maxHealth=health;

        jumpVel=-1.5f;

        lock = new Object();
        mouseLoc = new Point();
        curSprite = set.getNextSprite(state, facingLeft);
    }
    int getMaxHealth()
    {
        return maxHealth;
    }

    @Override
    void die() throws DeadException
    {
        throw new DeadException();
    }

    boolean isBreathingFire()
    {
        return breathingFire;
    }

    boolean isBiting()
    {
        return biting;
    }
    boolean isEnemy()
    {
        return false;
    }
    boolean isPlayer()
    {
        return true;
    }

    /**Required but unused*/
    void bitten(){}

    void goRight()
    {
        if (!goingRight)
        {
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
        
        else if ((y - Global.OffsetY +
                curSprite.getHeight())
                > Global.WinY - 50)
        {
            Global.OffsetY = (y + curSprite.getHeight()) - (Global.WinY - 50);
        }
    }
    void fire()
    {
        double x_;
        double y_;
       synchronized(lock)
       {
        x_ = mouseLoc.getX()+Global.OffsetX;
        y_ = mouseLoc.getY()+Global.OffsetY;
       }
        double spawnX=x-20+((facingLeft)?40:getWidth());
        double spawnY=y+40;
        
        double delx = x_-spawnX;
        double dely = y_-spawnY;

        double angle=Math.atan2(dely,delx);
        double delVelX=Math.cos(angle);
        double delVelY=Math.sin(angle);
        delVelX+=velX;
        delVelY+=velY;
        angle=Math.atan2(delVelY, delVelX);
        double mag=Math.sqrt(delVelX*delVelX+delVelY*delVelY);

        if(facingLeft)
        {
            if(angle>0&&angle<3*Math.PI/4)
            {
                angle=3*Math.PI/4;
            }
            else if(angle<=0&&angle>-3*Math.PI/4)
            {
                angle=-3*Math.PI/4;
            }

        }
        else
        {
            if(angle>Math.PI/4)
            {
                angle=Math.PI/4;
            }
            else if(angle<-Math.PI/4)
            {
                angle=-Math.PI/4;
            }
        }
        


        fireList.add(new Fire((float)spawnX,(float)spawnY,(float)mag,(float)angle));
    }

    @Override
    void handleActorCollisionX(Actor i)
    {
        if(biting&i.isVulnerable())
        {
            m=i.getMode();
            try{i.die();}
            catch(Exception e){}

        }
    }
    void setMouseLoc(Point p)
    {
    synchronized(lock)
        {
        mouseLoc=p;
        }
    }
    void clickAction(Point p)
    {
       if(m==JobMode.dragon)
       {
            breathingFire=true;
            synchronized(lock)
            {
            mouseLoc=p;
            }
       }
    }
    void keyAction()
    {
        if(m==JobMode.normal)
        {
            biting=true;
        }
    }
    void unKeyAction()
    {
        biting=false;
    }
    void unBreatheFire()
    {
        breathingFire=false;
    }
    void unJob()
    {
        m=JobMode.normal;
    }

    @Override
    void update() throws DeadException
    {
        super.update();
        if(breathingFire)
        {
            fire();
        }
        LinkedList<Fire> bab2=new LinkedList<Fire>();
        for(Fire i:fireList)
        {
            if(!i.isDead())
            {
                i.update();
                bab2.add(i);
            }

        }
        fireList=bab2;
        correctOffsets();
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
    void updateState()
    {
        super.updateState();
        if(breathingFire)
        {
            state=State.action;
        }
        if(biting)
        {
            state=State.biting;
        }
    }
}
