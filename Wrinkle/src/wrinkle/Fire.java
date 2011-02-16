/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
/**
 *
 * @author a.bresee
 */
public class Fire extends Actor{

    boolean dead;
    double angle;


    Fire(float X, float Y, float velX_, float velY_,
                        float accelX_, float accelY_ )
    {
        super("fire",X,Y,velX_,velY_,accelX_,accelY_);
        friction=false;
        
        
    }
    Fire(float X, float Y, float velX_, float velY_)
    {
        this(X,Y,velX_, velY_,0,0);
    }

    Fire(float X, float Y, float velX_, float velY_, double angle_)
    {
        this(X,Y,velX_,velY_,0,0);
        angle=angle_;

        System.out.println(angle);
        Graphics2D g=curSprite.createGraphics();

        g.rotate(angle);
        g.drawImage(curSprite,0,0,null);

    }

    @Override
    void update(GameObjects go)
    {
        super.update(go);
        if((x-Global.OffsetX>Global.WinX)||x-Global.OffsetX<0
                ||(y-Global.OffsetY>Global.WinY)||(y-Global.OffsetY)<0)
        {
            die();
        }
    }
    
    void die()
    {
        dead=true;
    }
    void hurt()
    {

    }
    boolean isDead()
    {
        return dead;
    }
}
