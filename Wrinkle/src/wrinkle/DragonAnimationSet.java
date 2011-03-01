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
 * This class contains all the animation information for the dragon.
 * It has a static AnimationCollection to prevent multiply loading images.
 * @author alex
 */
public class DragonAnimationSet extends AnimationSet{

    protected static AnimationCollection a;
    protected static ArrayList<BufferedImage> rightSleep;
    protected static ArrayList<BufferedImage> leftSleep;
    protected int sleepFrame;
    static final String str="Data/images/dragon/";
    static
    {
        a=new AnimationCollection(str);
        rightSleep=new ArrayList<BufferedImage>();
        leftSleep=new ArrayList<BufferedImage>();
        try{
            for(int i=0;i<3;++i)
            {
                rightSleep.add(ImageIO.read(new File(str+"rightsleep"+i+".png")));
                leftSleep.add(ImageIO.read(new File(str+"leftsleep"+i+".png")));
            }
            rightSleep.add(rightSleep.get(1));
            leftSleep.add(leftSleep.get(1));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        convertAll();
    }
    
    DragonAnimationSet()
    {                
        sleepFrame=0;
    }
    static void convertAll()
    {
        for(int i=0;i<rightSleep.size();++i)
        {
            rightSleep.set(i,Global.convert(rightSleep.get(i)));
            leftSleep.set(i,Global.convert(leftSleep.get(i)));
        }
    }
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
            return a.getNextSprite(s,facingLeft);
        }
    }
}
