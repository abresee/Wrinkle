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
    Bird()
    {
        this(0,0);
    }
    Bird(int X, int Y)
    {
        super("bird",X,Y);
        curSprite=leftidle;
        System.out.println("yes");
    }
//   void update(GameObjects go)
//    {
//
//    }
    @Override
    void draw(Graphics2D g)
    {
        super.draw(g);
        System.out.println("bird drawn at x "+x+" y "+y);
        System.out.println("velX: "+velX+"\n velY: "+velY);
    }
    @Override
    void updateVel()
    {
        velX=0;
        velY+=accelY*Global.timeStep;
         if (Math.abs(velY) > maxVelY) {
            velY = (velY < 0) ? -maxVelY : maxVelY;
        }

    }
    
}
