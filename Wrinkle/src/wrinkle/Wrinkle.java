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


/**
 * Class that defines the player character
 * @author a.bresee
 */

enum JobMode{normal,bird,dragon};

public final class Wrinkle extends Actor {

   private boolean biting;
   private final int maxHealth;
   private FireHelper fhelp;
   final int birdJumpLimit=3;
   int birdJump=0;


   private AnimationCollection d;
   static SFX sfx;
   static
   {
       try
       {
           sfx = new SFX("wrinkle");
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
   SFX getSfx()
    {
        return sfx;
    }
   
  
   private final Object fireListLock;

   private Point mouseLoc;

   private final Object lock;

    String getModeString()
    {
        switch(m)
        {
            case bird:
                return "bird";
            case dragon:
                return "dragon";
            default:
                return "normal";
        }
    }
    
    
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
        fhelp=new FireHelper(this);
        fireListLock=new Object();
        m=JobMode.normal;
        biting=false;
        health=3;
        maxHealth=health;

        lock = new Object();
        mouseLoc = new Point();
        curSprite = set.getNextSprite(state, facingLeft);
    }
    
    int getMaxHealth()
    {
        return maxHealth;
    }

   
    @Override
    void die()
    {
        super.die();
        fhelp.fireOff();
    }
    
    boolean isBreathingFire()
    {
        return fhelp.isBreathingFire();
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
        
        if (y - Global.OffsetY < (Global.WinY*7/13))
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
    

    @Override
    void handleActorCollisionX(Actor i)
    {
        if(biting&i.isVulnerable())
        {
            m=i.getMode();

        }
    }
    void setMouseLoc(Point p)
    {
        fhelp.setLoc(p);
    }
    void clickAction(Point p)
    {
       if(m==JobMode.dragon)
       {
            fhelp.fireOn();
            fhelp.setLoc(p);
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
       fhelp.fireOff();
    }
    @Override
    void jump()
    {
        if(m==JobMode.bird)
        {
            flap();
        }
        else
        {
            super.jump();
        }
    }
    void flap()
    {
        if(birdJump<birdJumpLimit)
        {
            velY = -1.5f;
            accelY = Global.gravity;
            birdJump++;
        }
        else
        {
            onTheGround=false;
        }
    }
    @Override
    void collideAbove(Collidable i)
    {
        birdJump=0;
        super.collideAbove(i);
    }
    void unJob()
    {
        m=JobMode.normal;
    }
    
    @Override
    void update(GameObjects go)
    {
        super.update(go);
        fhelp.update(go);
       
        correctOffsets();
    }
    @Override
    void draw(Graphics2D g)
    {
        super.draw(g);
        fhelp.draw(g);
    }
    @Override
    void updateState()
    {
        super.updateState();
        if(isBreathingFire())
        {
            state=State.action;
        }
        if(biting)
        {
            state=State.biting;
        }
    }
}
