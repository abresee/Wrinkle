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
public class AnimationSet {

    protected ArrayList<BufferedImage> rightWalk;
    protected ArrayList<BufferedImage> leftWalk;
    protected ArrayList<BufferedImage> rightAction;
    protected ArrayList<BufferedImage> leftAction;
    protected BufferedImage rightIdle;
    protected BufferedImage leftIdle;
    protected BufferedImage rightJump;
    protected BufferedImage leftJump;

     AnimationSet(String str)
    {
        String prefix="Data/images/"+str+"/";
        rightWalk=new ArrayList<BufferedImage>();
        leftWalk=new ArrayList<BufferedImage>();

        rightAction=new ArrayList<BufferedImage>();
        leftAction=new ArrayList<BufferedImage>();
        try
        {
            rightIdle=ImageIO.read(new File(prefix+"rightidle.png"));
            leftIdle=ImageIO.read(new File(prefix+"leftidle.png"));
            String name="right";

            for(int i=0;i<Global.framecount;++i)
            {
              System.out.println(prefix+name+"action"+i+".png");
              rightWalk.add(ImageIO.read(new File(prefix+name+"walk"+i+".png")));
              rightAction.add(ImageIO.read(new File(prefix+name+"action"+i+".png")));
            }

            name="left";
            for(int i=0;i<Global.framecount;++i)
            {
                leftWalk.add(ImageIO.read(new File(prefix+name+"walk"+i+".png")));
                leftAction.add(ImageIO.read(new File(prefix+name+"action"+i+".png")));
            }

            rightJump=ImageIO.read(new File(prefix+"rightjump.png"));
            leftJump=ImageIO.read(new File(prefix+"leftjump.png"));
           
        }
        catch(Exception e){e.printStackTrace();}
    }
    List<BufferedImage> getRightWalk()
     {
         return Collections.unmodifiableList(rightWalk);
     }
    List<BufferedImage> getLeftWalk()
    {
        return Collections.unmodifiableList(leftWalk);
    }
    BufferedImage getRightIdle()
    {
        return rightIdle;
    }
    BufferedImage getLeftIdle()
    {
        return leftIdle;
    }
    BufferedImage getLeftJump()
    {
        return leftJump;
    }
    BufferedImage getRightJump()
    {
        return rightJump;
    }
    List<BufferedImage> getRightAction()
    {
        return Collections.unmodifiableList(rightAction);
    }
    List<BufferedImage> getLeftAction()
    {
        return Collections.unmodifiableList(leftAction);
    }
}
