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
import java.awt.Graphics2D;


enum State {

        running, jumping, idle, action, sleeping,biting
    };
/**
 * Abstract superclass of game actors -- that is, objects that have animations,
 * sounds, etc. 
 * @author a.bresee
 */
public abstract class Actor extends ActiveCollidable {

    
    protected State state;
    protected int health;
    protected boolean recovering;
    protected boolean flip=false;
    protected int recoverCount;
    /**set to false if sound initialization doesn't complete*/
    private boolean soundImplemented;
    AnimationSet set;
    
    protected JobMode m;

    JobMode getMode()
    {
        return m;
    }

    void hurt()
    {
        if(!recovering)
        {
            if (health > 0) {
                --health;
                recovering=true;
                recoverCount++;
            } else {
                die();
            }
        }
    }

    Actor(String str, float X, float Y) {
        this(str, X, Y, 0, 0, 0, Global.gravity);
    }

    Actor(String str, float X, float Y, float velX_, float velY_,
            float accelX_, float accelY_) {

        super(X, Y, velX_, velY_, accelX_, accelY_);
        try{
        }
        catch(Exception e)
        {
         e.printStackTrace();
        }
        state=State.idle;
    }



    void jump(){
        if (onTheGround) {
            getSfx().jump();
            velY = -1.5f;
            accelY = Global.gravity;
            onTheGround = false;
        }
    }

    @Override
    void draw(Graphics2D g)
    {
    if(recoverCount%3==0)
        {
        super.draw(g);
        }
    }
    void handleTerrainCollisionX(Terrain i) {
        setXtoEdge(i);
    }

    void handleActorCollisionX(Actor i)
    {
        setXtoEdge(i);
         if(isPlayer()&&i.isEnemy()&&!isBiting())
            {
            hurt();
            }
            else if(isBiting())
            {
                i.bitten();
            }

    }

    void setXtoEdge(Collidable i) {
        float otherx = i.getX();
        //set x to be either the right edge of the collidable,
        //or the left edge-cursprite's width
        x = i.getX() + ((x < otherx) ? -curSprite.getWidth()
                : i.getWidth());
    }

    void handleTerrainCollisionY(Terrain i) {
        if (velY > 0) {
            if (!ignoreCollision) {
                collideAbove(i);
            }
        } else {
            ignoreCollision = true;
        }
    }

    void collideAbove(Collidable i)
    {
        if (!onTheGround) {
                    onTheGround = true;
                    getSfx().land();
                }
                velY = 0;
                y = i.getY() - curSprite.getHeight();
    }


    void handleActorCollisionY(Actor i)
    {
        if (y < i.getY()) {
            if (!ignoreCollision) {
               collideAbove(i);
            }

        } else {
            if(isPlayer()&&i.isEnemy()&&!isBiting())
            {
            hurt();
            }
            else if(isBiting())
            {
                i.bitten();
            }
        }
    }

    void updateState() {
        if (onTheGround) {
            if (Math.abs(velX) > .001f) {
                state = State.running;
            } else {
                state = State.idle;
            }
        } else {
            state = State.jumping;
        }
        if(recovering)
        {
            if(recoverCount++%40==0)
            {
                recoverCount=0;
                recovering=false;
            }
        }
    }


    void updateAnim() {
        if(frame++%frameTime==0)
        {
        curSprite=set.getNextSprite(state,m,facingLeft);
        }
    }

    void updateSound()
    {
        if(frame++%frameTime==0)
        {
            if(onTheGround && velX!=0)
            {
                if(flip)
                getSfx().walk1();
                else
                    getSfx().walk2();
                flip=!flip;
            }
        }
    }

    void updateVel() {

        //accelerate the character right
        if (goingRight) {
            accelX = (velX < 0) ? 0.002f : 0.001f;
        } //accelerate the character left
        else if (goingLeft) {
            accelX = (velX > 0) ? -0.002f : -0.001f;
        } else if (friction) {
            //apply horizontal friction
            if (Math.abs(velX * Global.timeStep) <= 1.0f) {
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

    boolean isFacing(Collidable c) {
        return ((x < c.getX()) && !facingLeft) || (x > c.getX() && facingLeft);
    }

    /**
     *Plays the sound file contained in the clip param. if <code>soundImplemented
     *</code> is false, nothing happens. This is to prevent errors on systems without
     *sound
     *@param Clip is a well-formed clip, loaded with data
     */
    
    int getHealth()
    {
        return health;
    }
    @Override
    void die()
    {
      super.die();
      getSfx().die();
    }
    
    abstract SFX getSfx();
    abstract boolean isPlayer();
    abstract boolean isEnemy();
    abstract boolean isBiting();
    boolean isVulnerable(){return false;}
    abstract void bitten();
}
