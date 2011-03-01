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
/**this class represents the fire that wrinkle breathes in dragon mode
 *
 * @author a.bresee
 */
public final class Fire extends ActiveCollidable{

    private double angle;
    private static BufferedImage FireSprite;

    private static boolean spriteInit=false;
   
   

    Fire(float X, float Y, float mag, float angle_)
    {
        super(X, Y, (float)(mag*Math.cos(angle_)), (float)(mag*Math.sin(angle_)), 0.0f, 0.0f);
        angle=angle_;

        initImages();
        Graphics2D g=curSprite.createGraphics();
        g.rotate(angle,FireSprite.getWidth()/2,FireSprite.getHeight()/2);
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
        x-=FireSprite.getWidth();
        y-=FireSprite.getHeight();
        
       curSprite = new BufferedImage(FireSprite.getWidth(),
                       FireSprite.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        
    }

    @Override
    void update(GameObjects go) throws DeadException
    {
        super.update(go);
        if((x-Global.OffsetX>Global.WinX)||x-Global.OffsetX<0
                ||(y-Global.OffsetY>Global.WinY)||(y-Global.OffsetY)<0)
        {
            die();
        }
        frame++;
        
    }
    @Override
    void die()
    {
        dead=true;
    }
    
    
    @Override
    void draw(Graphics2D g)
    {
        AffineTransform at=g.getTransform();
        g.translate((double)getWidth()/2, (double)getHeight()/2);

        super.draw(g);
        g.setTransform(at);
    }

    void handleTerrainCollisionX(Terrain i)
    {
    die();
    }
    void handleActorCollisionX(Actor i) throws DeadException
    {
        i.hurt();
        die();
    }
    void handleTerrainCollisionY(Terrain i){}
    void handleActorCollisionY(Actor i){}
    void updateVel(){}
    void updateState(){}
    void updateAnim(){}
    void updateSound(){}
}
