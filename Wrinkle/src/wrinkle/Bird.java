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
    
    Bird(Wrinkle wrinkle,int X, int Y)
    {
        super(wrinkle,"bird",X,Y);
        health=3;
    }

    protected void idleScript()
    {
       
        if(onTheGround)
        {
            if(frame%200==0)
            {
                velX+=((facingLeft)?.1:-.1);
               
            }
            if(frame%150==0)
            {
                jump();
                velX-=((facingLeft)?.1:-.1);
            }
        }
        
    }
    protected void activeScript()
    {

    }
    
//
//    @Override
//    void handleTerrainCollisionY(Terrain i)
//    {
//        super.handleTerrainCollisionX(i);
//
//        if(velY>0&&!active)
//        {
//            velX=0;
//        }
//        System.out.println("lol");
//        facingLeft=!facingLeft;
//    }

    @Override
    void draw(Graphics2D g)
    {
       System.out.println("X: "+x+"\nY: "+y);
        super.draw(g);
    }
    @Override
    void updateVel()
    {
        velY+=accelY*Global.timeStep;
         if (Math.abs(velY) > maxVelY) {
            velY = (velY < 0) ? -maxVelY : maxVelY;
        }

    }
//    void die()
//    {
//        super.die();
//    }
    
    
}
