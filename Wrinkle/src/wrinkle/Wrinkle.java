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
 *
 * @author a.bresee
 */
final class Wrinkle extends Actor {

    enum Mode{normal,bird,dragon};
    Mode m;

    ///////////////////////
    //INITIALIZATION CODE//
    ///////////////////////    
    //default ctor calls parametered ctor//    
    Wrinkle() {
        this(0, 0);
    }

    //parametered ctor takes pixel coords on where top left corner should be//
    Wrinkle(int X, int Y) {
        super("hero", X, Y);
        mass = 1;
        curSprite = rightidle;
        m=Mode.normal;
    }

    //called by ctor to init position, velocity, and acceleration
    void jump() {
        if (onTheGround) {
            playClip(jumpsnd);
            velY = -2f;
            accelY = Global.gravity;
            onTheGround = false;
            curSprite = (facingLeft) ? leftJump : rightJump;
        }
    }

    void goRight()
    {
        if (!goingRight)
        {
            curSprite = rightwalk[0];
            frame = 0;
            timecount = 0;
            facingLeft = false;
            goingRight = true;
            goingLeft = false;
        }
    }

    void unGoRight()
    {
        goingRight = false;
    }

    void goLeft()
    {
        if (!goingLeft) {
            curSprite = leftwalk[0];
            frame = 0;
            timecount = 0;
            facingLeft = true;
            goingLeft = true;
            goingRight = false;
        }
    }

    void unGoLeft()
    {
        goingLeft = false;
    }

    void playClip(Clip clip)
    {
        if (soundImplemented)
        {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    void updateVel() {
        //user is holding right, so
        //accelerate the character right
        if (goingRight)
        {
            accelX = (velX < 0) ? 0.002f : 0.001f;
        } //user is holding left, so
        //accelerate the character left
        else if (goingLeft)
        {
            accelX = (velX > 0) ? -0.002f : -0.001f;
        }
        
        else
        {
            //user is not holding a direction key, apply horizontal friction
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
        if (Math.abs(velX) > maxVelX) {
            velX = (velX < 0) ? -maxVelX : maxVelX;
        }
        velX += accelX * Global.timeStep;

        velY += accelY * Global.timeStep;
    }

    void collideTerrain(ArrayList<Terrain> terrains,float delx)
    {
        if (!insideOkay) {
            for (int i = 0; i< terrains.size(); ++i)
            {
                if (collidesWith(terrains.get(i)))
                {
                    x -= delx;
                    if (onTheGround)
                    {
                        float otherx = terrains.get(i).getX();

                        //set x to be either the right edge of the collidable,
                        //or the left edge-cursprite's width
                        x = terrains.get(i).getX() +
                                ((x < otherx) ? -curSprite.getWidth()
                                : terrains.get(i).getWidth());
                    }
                    break;
                }
            }
        }
    }
    void collideActors(ArrayList<Actor> actors,float delx)
    {
        
    }
    void update(GameObjects go) 
    {
        updateVel();

        ArrayList<Terrain> terrains=go.getTerrains();
        ArrayList<Actor> actors=go.getActors();

        updateVel();

        float delx = velX * Global.timeStep;
        x+=delx;

        collideTerrain(terrains,delx);
        
        collideActors(actors,delx);
        

        float dely = velY * Global.timeStep;
        y += dely;

        boolean bk = false;

        for (int i = 0; i<terrains.size();++i)
        {
            if (collidesWith(terrains.get(i)))
            {
                if (velY > 0)
                {
                    if (!insideOkay)
                    {
                        if (!onTheGround)
                        {
                            onTheGround = true;
                            playClip(land);
                        }
                        velY = 0;
                        y =terrains.get(i).getY() - curSprite.getHeight();
                    } 
                    else
                    {
                        bk = true;
                        break;
                    }
                } 
                else
                {
                    insideOkay = true;
                    bk = true;
                    break;
                }
            }
        }
        if (!bk) {
            insideOkay = false;
        }

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
                curSprite = (facingLeft) ? leftwalk[frame] : rightwalk[frame];
                frame =(frame < Global.framecount - 1) ? (frame + 1) : 0;
            }
            else
            {
                ++timecount;
            }
        }
        if (velX == 0 && onTheGround)
        {
            curSprite = (facingLeft) ? leftidle : rightidle;
        }
        correctOffsets();
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
}
