/*
 * Wrinkle.java
 *
 * Created on February 8, 2011, 8:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package wrinkle;

import javax.sound.sampled.*;
import java.util.ArrayList;

/**
 * Class that defines the player character
 * @author a.bresee
 */
public final class Wrinkle extends Actor {

   boolean biting;

   int hearts;
    /**
     *Enum to flag what 'copy mode' wrinkle is using
     *@author a.bresee
     */
    enum JobMode{normal,bird,dragon};
    JobMode m;
    
    
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
        mass = 1;
        curSprite = rightIdle;
        m=JobMode.normal;
        biting=false;
        hearts=3;
    }

    /**
     *Defines behavior of "jump" action. Called when player jumps.
     */
    void hurt()
    {
        if (hearts>0)
        {
            hearts-=1;
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

    void jump() {
        if (onTheGround) {
            playClip(jumpsnd);
            velY = -1.5f;
            accelY = Global.gravity;
            onTheGround = false;
            curSprite = (facingLeft) ? leftJump : rightJump;
        }
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
    
    /**
     *Plays the sound file contained in the clip param. if <code>soundImplemented 
     *</code> is false, nothing happens. This is to prevent errors on systems without
     *sound
     *@param Clip is a well-formed clip, loaded with data
     */
   

   
    

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

    @Override
    void update(GameObjects go)
    {
        super.update(go);
        correctOffsets();
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
