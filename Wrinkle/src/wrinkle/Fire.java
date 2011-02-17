/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
/**
 *
 * @author a.bresee
 */
public final class Fire extends activeCollidable{

    private boolean dead;
    private double angle;
    private static BufferedImage FireSprite;

    private static boolean spriteInit=false;
   
    Fire(float X, float Y, float velX_, float velY_)
    {
        super(X,Y,velX_, velY_,0,0);
    }

    Fire(float X, float Y, float velX_, float velY_, double angle_)
    {
        this(X,Y,velX_,velY_);
        angle=angle_;
        initImages();

        System.out.println(angle);
        Graphics2D g=curSprite.createGraphics();
        g.rotate(angle,FireSprite.getHeight()/2,FireSprite.getWidth()/2);
        g.drawImage(FireSprite,0,0,null);

    }
    final void initImages()
    {
        if(!spriteInit)
        {
           try
           {
               FireSprite=ImageIO.read(new File("Data/images/fire/fire.png"));
           }
           catch(Exception e)
           {
                e.printStackTrace();
           }
           spriteInit=true;
        }

        
       curSprite = new BufferedImage(FireSprite.getWidth(),
                       FireSprite.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        
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
        frame++;
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
    @Override
    void draw(Graphics2D g)
    {
        AffineTransform at=g.getTransform();
        g.scale(frame*0.05+.5, frame*0.05+.5);
        super.draw(g);
        g.setTransform(at);
    }

    void handleTerrainCollisionX(Terrain i){}
    void handleActorCollisionX(Actor i){}
    void handleTerrainCollisionY(Terrain i){}
    void handleActorCollisionY(Actor i){}
    void updateVel(){}
    void updateState(){}
    void updateAnim(){}
    void updateSound(){}
}
