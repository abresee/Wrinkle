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

    private final ArrayList<BufferedImage> rightWalk;
    private final ArrayList<BufferedImage> leftWalk;
    private final ArrayList<BufferedImage> rightAction;
    private final ArrayList<BufferedImage> leftAction;
    private final BufferedImage rightIdle;
    private final BufferedImage leftIdle;
    private final BufferedImage rightJump;
    private final BufferedImage leftJump;
    private int walkFrame=0;
    private int actionFrame=0;

    AnimationSet(AnimationSet a)
    {
        rightWalk=a.rightWalk;
        leftWalk=a.leftWalk;
        rightAction=a.rightAction;
        leftAction=a.leftAction;
        rightIdle=a.rightIdle;
        leftIdle=a.leftIdle;
        leftJump=a.leftJump;
        rightJump=a.rightJump;
     }
     AnimationSet(String str) throws java.io.IOException
    {
        String prefix="Data/images/"+str+"/";
        rightWalk=new ArrayList<BufferedImage>();
        leftWalk=new ArrayList<BufferedImage>();

        rightAction=new ArrayList<BufferedImage>();
        leftAction=new ArrayList<BufferedImage>();
        rightIdle=ImageIO.read(new File(prefix+"rightidle.png"));
        leftIdle=ImageIO.read(new File(prefix+"leftidle.png"));
        String name="right";
        for(int i=0;i<Global.framecount;++i)
        {
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
   BufferedImage getNextSprite(State s, boolean facingLeft)
   {
       BufferedImage bi=leftIdle;
       switch(s)
       {
           case running:
               bi=(facingLeft)
                 ?leftWalk.get((walkFrame++)%leftWalk.size())
                 :rightWalk.get((walkFrame++)%rightWalk.size());

               break;
           case jumping:
               bi=(facingLeft)?leftJump:rightJump;
               break;
           case idle:
               bi=(facingLeft)?leftIdle:rightIdle;
               break;
           case action:
               bi=(facingLeft)
                 ?leftAction.get((actionFrame++)%leftAction.size())
                 :rightAction.get((actionFrame++)%rightAction.size());
               break;
       }
       return bi;
   }
}
