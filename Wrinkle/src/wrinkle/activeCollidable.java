/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
/**
 *
 * @author alex
 */
abstract class ActiveCollidable extends Collidable {
    
    
    protected BufferedImage curSprite;
    /** x velocity */
    protected float velX;
    /** y velocity */
    protected float velY;
    /** x acceleration*/
    protected float accelX;
    /** y acceleration*/
    protected float accelY;
    /** maximum x velocity -- velX is capped at this value*/
    protected float maxVelX;
    /** maximum y velocity -- velY is capped at this value*/
    protected float maxVelY;

    /** how much the collidable moved this frame - x */
    protected float delX;
    /** how much the collidable moved this frame - y */
    protected float delY;

    /**
     *Currently unused - mass for calculating momentum changes in collisions
     */
    protected float mass;

    /**
     *How many game-frames should go by before the next animation frame is loaded
     */
    protected int frameTime=10;

    /*
     *Variable keeps track of game-frames since last animation frame change
     */

    protected int timecount=0;

    /**
     *variable keeps track of what frame we're on in an animation sequence
     */
    protected int frame=0;

    protected boolean dead;
    protected boolean onTheGround;
    protected boolean goingRight;
    protected boolean goingLeft;
    protected boolean facingLeft;
    protected boolean friction;

    /**Occasionally, it may be desired that collisions are ignored*/
    protected boolean ignoreCollision;




    
    ActiveCollidable(float X, float Y, float velX_, float velY_,
                        float accelX_, float accelY_ )
    {
        initPhys(X,Y, velX_, velY_, accelX_, accelY_);
        onTheGround=true;
        ignoreCollision=false;
        goingRight=false;
        goingLeft=false;
        facingLeft=false;
        dead=false;
    }
    
     final void initPhys(float X, float Y, float velX_, float velY_,
                        float accelX_, float accelY_)
    {
        x=X;
        y=Y;

        velX=velX_;
        velY=velY_;

        maxVelX=0.5f;
        maxVelY=3.0f;

        accelX=accelX_;
        accelY=accelY_;
        friction=true;
    }

    /////////////////GETTERS///////////////////////////
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
   void generateBoundingBox()
    {
      collideShape=new Rectangle2D.Float(x,y,curSprite.getWidth(),curSprite.getHeight());
    }
   
   @Override
    public Rectangle2D getbBox(){return collideShape;}

    void die() throws DeadException
    {
        dead=true;
    }
    boolean isDead()
    {
        return dead;
    }
    public boolean collidesWith(Collidable c)
    {
        generateBoundingBox();
        c.generateBoundingBox();
        boolean b=collideShape.intersects(c.getbBox());
        return b;
    }
     void update() throws DeadException
    {

        updateVel();                    

        updateState();

        updateAnim();

        updateSound();

    }


     abstract void handleTerrainCollisionX(Terrain i);
     abstract void handleActorCollisionX(Actor i) throws DeadException;
     abstract void handleTerrainCollisionY(Terrain i);
     abstract void handleActorCollisionY(Actor i) throws DeadException;
     abstract void updateVel();
     abstract void updateState();
     abstract void updateAnim();
     abstract void updateSound();

}
