/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 *
 * @author alex
 */
public class DragonAnimationSet extends AnimationSet{

    protected ArrayList<BufferedImage> rightSleep;
    protected ArrayList<BufferedImage> leftSleep;
    protected int sleepFrame;
    DragonAnimationSet(AnimationSet a)
    {
        super(a);
        String prefix="Data/images/dragon/";
        
        rightSleep=new ArrayList<BufferedImage>();
        leftSleep=new ArrayList<BufferedImage>();
        try{
            for(int i=0;i<Global.framecount;++i)
            {
                rightSleep.add(ImageIO.read(new File(prefix+"rightsleep"+i+".png")));
                leftSleep.add(ImageIO.read(new File(prefix+"leftsleep"+i+".png")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        sleepFrame=0;
    }
    @Override
    BufferedImage getNextSprite(State s, boolean facingLeft)
    {
        if(s==State.sleeping)
        {
            return (facingLeft)
                    ?leftSleep.get(sleepFrame++%leftSleep.size())
                    :rightSleep.get(sleepFrame++%rightSleep.size());
        }
        else
        {
            return super.getNextSprite(s,facingLeft);
        }
    }
}
