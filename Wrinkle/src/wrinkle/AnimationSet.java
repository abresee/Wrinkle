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
    private int walkFrame=0;
    private int actionFrame=0;

    
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
