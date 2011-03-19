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
public class AnimationCollection{

    private ArrayList<BufferedImage> rightWalk;
    private ArrayList<BufferedImage> leftWalk;
    private ArrayList<BufferedImage> rightAction;
    private ArrayList<BufferedImage> leftAction;
    private BufferedImage rightIdle;
    private BufferedImage leftIdle;
    private BufferedImage rightJump;
    private BufferedImage leftJump;
    private int walkFrame=0;
    private int actionFrame=0;
    static boolean init=false;
    AnimationCollection(String str)
    {
        this(str,Global.framecount);
    }
    AnimationCollection(String str,int Framecount)
    {

        rightWalk=new ArrayList<BufferedImage>();
        leftWalk=new ArrayList<BufferedImage>();

        rightAction=new ArrayList<BufferedImage>();
        leftAction=new ArrayList<BufferedImage>();
        try{
        rightIdle=ImageIO.read(new File(str+"rightidle.png"));
        leftIdle=ImageIO.read(new File(str+"leftidle.png"));
        String name="right";
        for(int i=0;i<Framecount;++i)
        {
          rightWalk.add(ImageIO.read(new File(str+name+"walk"+i+".png")));
          rightAction.add(ImageIO.read(new File(str+name+"action"+i+".png")));
        }

        name="left";
        for(int i=0;i<Framecount;++i)
        {
            leftWalk.add(ImageIO.read(new File(str+name+"walk"+i+".png")));
            leftAction.add(ImageIO.read(new File(str+name+"action"+i+".png")));
        }

        rightJump=ImageIO.read(new File(str+"rightjump.png"));
        leftJump=ImageIO.read(new File(str+"leftjump.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        walkFrame=0;
        actionFrame=0;
        convertAll();
    }
    void convertAll()
    {
    for(int i=0;i<rightWalk.size();++i)
    {
    rightWalk.set(i,Global.convert(rightWalk.get(i)));
    leftWalk.set(i,Global.convert(leftWalk.get(i)));
    }
    for(int i=0;i<rightAction.size();++i)
    {
    rightAction.set(i,Global.convert(rightAction.get(i)));
    leftAction.set(i,Global.convert(leftAction.get(i)));
    }
    rightIdle=Global.convert(rightIdle);
    leftIdle=Global.convert(leftIdle);
    rightJump=Global.convert(rightJump);
    leftJump=Global.convert(leftJump);
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
