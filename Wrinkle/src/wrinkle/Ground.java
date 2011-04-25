/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;

/**
 *
 * @author alex
 */
public class Ground extends Terrain{
    static BufferedImage sprite;
    static float sWidth()
    {
        return sprite.getWidth();
    }
    static float sHeight()
    {
        return sprite.getHeight();
    }
    static
    {
        try
        {
            sprite =ImageIO.read(new File("Data/images/terrain/ground.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    Ground(int X, int Y)
    {
        super(X,Y,sprite.getWidth(),sprite.getHeight());
    }
    @Override
    void draw(Graphics2D g)
    {
        g.drawImage(sprite, Math.round(x), Math.round(y), null);
    }

}
