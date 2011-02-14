/*
 * Actor.java
 *
 * Created on February 8, 2011, 8:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.image.BufferedImage;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import java.io.File;
import javax.sound.sampled.*;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Abstract superclass of game actors -- that is, objects that have animations,
 * sounds, etc. 
 * @author a.bresee
 */
public abstract class Actor extends Collidable{    

    /**
     *Currently Unused enum to flag direction, to simplify collision code
     */
    enum direction{x,y};

    String state;

    float velX;
    float velY;
    float accelX;
    float accelY;
    float maxVelX;
    float maxVelY;
    
    float delX;
    float delY;

    /**
     *Currently unused - mass for calculating momentum changes in collisions
     */
    float mass;
    
    /**
     *How many game-frames should go by before the next animation frame is loaded
     */
    int frametime=5;
    
    /*
     *Variable keeps track of game-frames since last animation frame change
     */
    
    int timecount=0;
    
    /**
     *variable keeps track of what frame we're on in an animation sequence
     */
    int frame=0;

    
    boolean onTheGround;
    boolean goingRight;
    boolean goingLeft;
    boolean facingLeft;
    
    /**Occasionally, it may be desired that collisions are ignored*/
    boolean ignoreCollision;

    /**set to false if sound initialization doesn't complete*/
    boolean soundImplemented;

    
    BufferedImage curSprite;
    BufferedImage rightWalk[];
    BufferedImage leftWalk[];
    BufferedImage rightIdle;
    BufferedImage leftIdle;
    BufferedImage rightJump;
    BufferedImage leftJump;



    Clip jumpsnd;
    Clip walk1;
    Clip walk2;
    Clip land;

    abstract void hurt();
    abstract void die();

    Actor(String str, int X, int Y)
    {
        initImages(str);
        initPhys(X,Y);


        onTheGround=true;
        ignoreCollision=false;
        goingRight=false;
        goingLeft=false;
        facingLeft=false;
    }
    
    /**this is just a subroutine to make things cleaner*/
    final void initPhys(int X, int Y)
    {
        x=X;
        y=Y;

        velX=0;
        velY=0;

        maxVelX=0.5f;
        maxVelY=3.0f;

        accelX=0;
        accelY=Global.gravity;
    }
    
    /**this is just a subroutine to make things cleaner*/
    final void initImages(String str)
    {
        String prefix="Data/images/"+str+"/";
        rightWalk=new BufferedImage[Global.framecount];
        leftWalk=new BufferedImage[Global.framecount];
        try
        {
            rightIdle=ImageIO.read(new File(prefix+"rightidle.png"));
            leftIdle=ImageIO.read(new File(prefix+"leftidle.png"));
            String name="rightwalk";

            for(int i=0;i<Global.framecount;++i)
            {
              rightWalk[i]=ImageIO.read(new File(prefix+name+i+".png"));
            }

            name="leftwalk";
            for(int i=0;i<Global.framecount;++i)
            {
                leftWalk[i]=ImageIO.read(new File(prefix+name+i+".png"));
            }

            rightJump=ImageIO.read(new File(prefix+"rightjump.png"));
            leftJump=ImageIO.read(new File(prefix+"leftjump.png"));

        }
        catch(Exception e){e.printStackTrace();}
    }

    /**this is just a subroutine to make things cleaner*/
    void initSounds(String str)
    {

        String prefix="Data/audio/"+str+"/";

        try
        {
            jumpsnd=Global.makeClip(prefix+"jump.wav");
            walk1=Global.makeClip(prefix+"walk1.wav");
            walk2=Global.makeClip(prefix+"walk2.wav");
            land=Global.makeClip(prefix+"land.wav");
            soundImplemented=true;
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }

    }
    void generateBoundingBox()
    {
      bBox=new Rectangle2D.Float(x,y,curSprite.getWidth(),curSprite.getHeight());
    }

    int getHeight()
    {
        return curSprite.getHeight();
    }

    int getWidth()
    {
        return curSprite.getWidth();
    }

    void draw(Graphics2D g) 
    {        
        g.drawImage(curSprite, Math.round(x), Math.round(y), null);        
    }
    boolean roar(Actor a)
    {
        return true;
    }

    void tryMoveX(float delx, GameObjects go)
    {
         x+=delx;
         
         if(!ignoreCollision)
         {
             for(Terrain i:go.getTerrains())
             {
                 if(collidesWith(i))
                 {
                   
                     setXtoEdge(i);
                 }
             }
         }
         for(Actor i:go.getActors())
         {
             if(this.equals(i))
                 continue;
             else if(collidesWith(i))
             {
                 if(roar(i))
                     setXtoEdge(i);
             }
         }       
    }
    
    void setXtoEdge(Collidable i)
    {
        float otherx = i.getX();
        //set x to be either the right edge of the collidable,
        //or the left edge-cursprite's width
        x = i.getX() + ((x < otherx) ? -curSprite.getWidth()
                         : i.getWidth());
    }

    void tryMoveY(float delY, GameObjects go)
    {
         boolean bk = false;
         y+=delY;
        for (Terrain i:go.getTerrains())
        {            
            if (collidesWith(i))
            {
                if (velY > 0)
                {
                    if (!ignoreCollision)
                    {
                        if (!onTheGround)
                        {
                            onTheGround = true;
                            playClip(land);
                        }
                        velY = 0;
                        y = i.getY() - curSprite.getHeight();
                    }
                    else
                    {
                        bk = true;
                        break;
                    }
                }
                else
                {
                    ignoreCollision = true;
                    bk = true;
                    break;
                }
            }
        }
        for (Actor i:go.getActors())
        {
            if(this.equals(i))
                continue;
            if (collidesWith(i))
            {
                if (velY > 0)
                {
                    if (!ignoreCollision)
                    {
                        if (!onTheGround)
                        {
                            onTheGround = true;
                            playClip(land);
                        }
                        velY = 0;
                        y = i.getY() - curSprite.getHeight();
                    }
                    else
                    {
                        bk = true;
                        break;
                    }
                }
                else
                {
                    ignoreCollision = true;
                    bk = true;
                    break;
                }
            }
        }
        if (!bk) {
            ignoreCollision = false;
        }
        if (!bk) {
            ignoreCollision = false;
        }
    }
    

    void update(GameObjects go)
    {
      
        updateVel();

        float delx = velX * Global.timeStep;
        tryMoveX(delx,go);
        
        float dely = velY * Global.timeStep;
        tryMoveY(dely,go);

        updateState();
        
        updateAnim();
        
        updateSound();
        
        if ((Math.abs(velX) > .001f) && onTheGround)
        {
            if (timecount == frametime)
            {
                timecount = 0;
                if (frame % 2 == 0)
                {
                    playClip(walk1);
                } else
                {
                    playClip(walk2);
                }
                curSprite = (facingLeft) ? leftWalk[frame] : rightWalk[frame];
                frame =(frame < Global.framecount - 1) ? (frame + 1) : 0;
            }
            else
            {
                ++timecount;
            }
        }
        if (velX == 0 && onTheGround)
        {

            //if the actor is on the ground, pick between which idle sprite
            //should be displayed based on what direction it is facing
            curSprite = (facingLeft) ? leftIdle : rightIdle;
        }

    }
    void updateState()
    {
         if (onTheGround)
         {
             if(Math.abs(velX) > .001f)
                state="running";
             else
                 state="idle";
         }             
         else
             state="jumping";
    }
    
    void updateAnim()
    {
         if (state=="running")
        {
            if (timecount == frametime)
            {
                timecount = 0;
                if (frame % 2 == 0)
                {
                    playClip(walk1);
                } else
                {
                    playClip(walk2);
                }
                curSprite = (facingLeft) ? leftWalk[frame] : rightWalk[frame];
                frame =(frame < Global.framecount - 1) ? (frame + 1) : 0;
            }
            else
            {
                ++timecount;
            }
        }
         else if(state=="idle")
         {
                 curSprite = (facingLeft) ? leftIdle : rightIdle;
         }
         else if(state=="jumping")
         {
             curSprite = (facingLeft) ? leftJump : rightJump;
         }
    }
    void updateSound()
    {
        
    }


     void updateVel() {
       
        //accelerate the character right
        if (goingRight)
        {
            accelX = (velX < 0) ? 0.002f : 0.001f;
        } 
        //accelerate the character left
        else if (goingLeft)
        {
            accelX = (velX > 0) ? -0.002f : -0.001f;
        }

        else
        {
            //apply horizontal friction
            if (Math.abs(velX * Global.timeStep) <= 1.0f)
            {
                //velocity amounts to less than 1 pixel change per time step
                //so lets just forget about velocity shall we?
                accelX = 0;
                velX = 0;

            } else if (onTheGround) {
                accelX = -0.009f * velX;
            } else {
                accelX = -0.0009f * velX;
            }
        }

        velX += accelX * Global.timeStep;
        velY += accelY * Global.timeStep;

        if (Math.abs(velX) > maxVelX) {
            velX = (velX < 0) ? -maxVelX : maxVelX;
        }

        if (Math.abs(velY) > maxVelY) {
            velY = (velY < 0) ? -maxVelY : maxVelY;
        }
    }

    boolean isFacing(Collidable c)
    {
        return ((x<c.getX())&&!facingLeft)||(x>c.getX()&&facingLeft);
    }
    
     void playClip(Clip clip)
    {
        if (soundImplemented)
        {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
