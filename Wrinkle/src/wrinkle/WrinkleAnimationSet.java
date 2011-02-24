/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author alex
 */
public class WrinkleAnimationSet extends AnimationSet{

    static private AnimationCollection n;
    static private AnimationCollection d;
    static private AnimationCollection b;
    static private ArrayList<BufferedImage> rightBite;
    static private ArrayList<BufferedImage> leftBite;
    private int biteFrame=0;
    static private String str="Data/images/hero/";
    static
    {
        n=new AnimationCollection(str);
        d=new AnimationCollection(str+"dragon/");
        b=new AnimationCollection(str+"bird/");
        rightBite=new ArrayList<BufferedImage>();
        leftBite=new ArrayList<BufferedImage>();
        try{
            for(int i=0;i<Global.framecount;++i)
            {
                rightBite.add(ImageIO.read(new File(str+"rightbite"+i+".png")));
                leftBite.add(ImageIO.read(new File(str+"leftbite"+i+".png")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
            return n.getNextSprite(s,facingLeft);

    }
    BufferedImage getNextSprite(State s, JobMode m, boolean facingLeft)
    {
        
        switch(m){
            case bird:
                return b.getNextSprite(s,facingLeft);
            case dragon:
                return d.getNextSprite(s, facingLeft);
            case normal:
                return this.getNextSprite(s, facingLeft);
        }
        return null;
    }

}
