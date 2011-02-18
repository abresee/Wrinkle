/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.geom.*;
/**
 *
 * @author alex
 */
abstract public class ActiveCollidable extends Collidable {

    /** x velocity */
    float velX;
    /** y velocity */
    float velY;
    /** x acceleration*/
    float accelX;
    /** y acceleration*/
    float accelY;
    /** maximum x velocity -- velX is capped at this value*/
    float maxVelX;
    /** maximum y velocity -- velY is capped at this value*/
    float maxVelY;

    /** how much the collidable moved this frame - x */
    float delX;
    /** how much the collidable moved this frame - y */
    float delY;

    /**
     *Currently unused - mass for calculating momentum changes in collisions
     */
    float mass;

    /**
     *How many game-frames should go by before the next animation frame is loaded
     */
    int frametime=10;

    /*
     *Variable keeps track of game-frames since last animation frame change
     */

    int timecount=0;

    /**
     *variable keeps track of what frame we're on in an animation sequence
     */
    int frame=0;

    boolean dead;
    boolean onTheGround;
    boolean goingRight;
    boolean goingLeft;
    boolean facingLeft;
    boolean friction;

    /**Occasionally, it may be desired that collisions are ignored*/
    boolean ignoreCollision;




    
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
void generateBoundingBox()
    {
      collideShape=new Rectangle2D.Float(x,y,curSprite.getWidth(),curSprite.getHeight());
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
    public Rectangle2D getbBox(){return collideShape;}

    void die()
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
     void update(GameObjects go)
    {

        updateVel();
        float dely = velY * Global.timeStep;
        tryMoveY(dely,go);
        
        float delx = velX * Global.timeStep;
        tryMoveX(delx,go);

       

        updateState();

        updateAnim();

        updateSound();

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
                    handleTerrainCollisionX(i);

                 }
             }
         }
         for(Actor i:go.getActors())
         {
             if(this.equals(i))
                 continue;
             else if(collidesWith(i))
             {
                handleActorCollisionX(i);
             }
         }
    }


 void tryMoveY(float delY, GameObjects go)
    {
         boolean bk = false;
         y+=delY;
        for (Terrain i:go.getTerrains())
        {
            if (collidesWith(i))
            {
                handleTerrainCollisionY(i);
                bk=true;
                break;
            }
        }
        for (Actor i:go.getActors())
        {
            if(this.equals(i))
                continue;
            if (collidesWith(i))
            {
                handleActorCollisionY(i);
                bk=true;
                break;
            }
        }
        if (!bk) {
            ignoreCollision = false;
        }
    }

     abstract void handleTerrainCollisionX(Terrain i);
     abstract void handleActorCollisionX(Actor i);
     abstract void handleTerrainCollisionY(Terrain i);
     abstract void handleActorCollisionY(Actor i);
     abstract void updateVel();
     abstract void updateState();
     abstract void updateAnim();
     abstract void updateSound();

}
