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
import javax.sound.sampled.*;
import java.awt.geom.*;
import java.awt.Graphics2D;

/**
 * Abstract superclass of game actors -- that is, objects that have animations,
 * sounds, etc. 
 * @author a.bresee
 */
public abstract class Actor extends ActiveCollidable {

    protected enum State {

        running, jumping, idle, action
    };
    protected State state;
    protected int health;
    /**set to false if sound initialization doesn't complete*/
    private boolean soundImplemented;
    AnimationSet normal;
    AnimationSet currentSet;
    protected Clip jumpsnd;
    protected Clip walk1;
    protected Clip walk2;
    protected Clip land;

    void hurt() throws DeadException {
        if (health > 0) {
            --health;
        } else {
            die();
        }
    }

    Actor(String str, float X, float Y) {
        this(str, X, Y, 0, 0, 0, Global.gravity);
    }

    Actor(String str, float X, float Y, float velX_, float velY_,
            float accelX_, float accelY_) {

        super(X, Y, velX_, velY_, accelX_, accelY_);
        normal = new AnimationSet(str);
        currentSet = normal;
        initSounds(str);
        curSprite = currentSet.getLeftIdle();


    }

    /**this is just a subroutine to make things cleaner*/
    /**this is just a subroutine to make things cleaner*/
    final void initSounds(String str) {

        String prefix = "Data/audio/" + str + "/";

        try {
            jumpsnd = Global.makeClip(prefix + "jump.wav");
            walk1 = Global.makeClip(prefix + "walk1.wav");
            walk2 = Global.makeClip(prefix + "walk2.wav");
            land = Global.makeClip(prefix + "land.wav");
            soundImplemented = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void jump() {
        if (onTheGround) {
            playClip(jumpsnd);
            velY = -1.5f;
            accelY = Global.gravity;
            onTheGround = false;
        }
    }

    void handleTerrainCollisionX(Terrain i) {
        setXtoEdge(i);
    }

    void handleActorCollisionX(Actor i) {
        setXtoEdge(i);
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
                if (!onTheGround) {
                    onTheGround = true;
                    playClip(land);
                }
                velY = 0;
                y = i.getY() - curSprite.getHeight();
            }
        } else {
            ignoreCollision = true;
        }
    }

    void handleActorCollisionY(Actor i) {
        if (y < i.getY()) {
            if (!ignoreCollision) {
                if (!onTheGround) {
                    onTheGround = true;
                    playClip(land);
                }
                velY = 0;
                y = i.getY() - curSprite.getHeight();
            }

        } else {
            ignoreCollision = true;
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
    }

    void updateAnim() {
        switch (state) {
            case running:
                if (timecount == frametime) {
                    timecount = 0;
                    if (frame % 2 == 0) {
                        playClip(walk1);
                    } else {
                        playClip(walk2);
                    }
                    curSprite = (facingLeft)
                            ? currentSet.getLeftWalk().get(frame)
                            : currentSet.getRightWalk().get(frame);
                    frame = (frame < Global.framecount - 1) ? (frame + 1) : 0;
                } else {
                    ++timecount;
                }
                break;
            case idle:
                curSprite = (facingLeft)
                        ? currentSet.getLeftIdle()
                        : currentSet.getRightIdle();
                break;
            case jumping:
                curSprite = (facingLeft)
                        ? currentSet.getLeftJump()
                        : currentSet.getRightJump();
                break;
            case action:
                if(timecount==frametime)
                {
                curSprite = (facingLeft)
                          ? currentSet.getLeftAction().get(frame)
                          : currentSet.getRightAction().get(frame);
                frame = (frame < Global.framecount - 1) ? (frame + 1) : 0;
                }
                else{
                    ++timecount;
                }
        }
    }

    void updateSound() {
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
    void playClip(Clip clip) {
        if (soundImplemented) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
