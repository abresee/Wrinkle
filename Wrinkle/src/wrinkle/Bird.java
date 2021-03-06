/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.Graphics2D;

/**
 * Class defining the Bird enemy
 * @author a.bresee
 */
public final class Bird extends Enemy
{
    
   static SFX sfx;
   static
   {
       try
       {
           sfx = new SFX("bird");
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
    
    Bird(Wrinkle wrinkle, int X, int Y)
    {
        super(wrinkle, "bird",X,Y);
        set=new BirdAnimationSet();
        health=3;
        m=JobMode.bird;
        curSprite = set.getNextSprite(state, facingLeft);
    }

    protected void idleScript()
    {
       
        if(onTheGround)
        {
            if(frame%200==0)
            {
                jump();
                velX+=((facingLeft)?-.1:.1);
               
            }
        }
        
    }
    protected void activeScript()
    {
         if(onTheGround)
         {
            boolean cond=((wrinkle.getX()-x)>0);
            velX=(float)(.3*((cond)?1:-1));
            facingLeft=!cond;
            jump();
         }
        
    }
    

    @Override
    void handleTerrainCollisionY(Terrain i)
    {
        super.handleTerrainCollisionY(i);

        if(!active)
        {
        facingLeft=!facingLeft;
        }
    }

    @Override
    void draw(Graphics2D g)
    {
        super.draw(g);
    }
    @Override
    void updateVel()
    {
       if(onTheGround&&!active)
       {
           velX=0;
       }
        velY+=accelY*Global.timeStep;
         if (Math.abs(velY) > maxVelY) {
            velY = (velY < 0) ? -maxVelY : maxVelY;
        }
    }
}
