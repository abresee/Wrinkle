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
public class WrinkleAnimationSet extends AnimationSet{

    protected ArrayList<BufferedImage> rightBite;
    protected ArrayList<BufferedImage> leftBite;
    protected int biteFrame;
    WrinkleAnimationSet(AnimationSet a)
    {
        super(a);
        String prefix="Data/images/hero/";

        rightBite=new ArrayList<BufferedImage>();
        leftBite=new ArrayList<BufferedImage>();
        try{
            for(int i=0;i<Global.framecount;++i)
            {
                rightBite.add(ImageIO.read(new File(prefix+"rightbite"+i+".png")));
                leftBite.add(ImageIO.read(new File(prefix+"leftbite"+i+".png")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        biteFrame=0;
    }
    @Override
    BufferedImage getNextSprite(State s, boolean facingLeft)
    {
        if(s==State.biting)
        {
            return (facingLeft)
                    ?leftBite.get(biteFrame++%leftBite.size())
                    :rightBite.get(biteFrame++%rightBite.size());
        }
        else
            return super.getNextSprite(s,facingLeft);

    }

}
