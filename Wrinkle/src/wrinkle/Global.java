/*
 * Global.java
 *
 * Created on February 8, 2011, 8:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package wrinkle;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.image.BufferedImage;

/**
 * Class contains constants and functions that other classes might find handy
 * @author a.bresee
 */
enum ActorType
{
    Bird,
    Dragon,
    Wrinkle,
    Chameleon
}
public class Global
{
    static final int timeStep=20;
    static final int WinX=800;
    static final int WinY=600;
    static final int GroundLevel=500;
    static final int framecount=2;
    static final float gravity=0.0035f;
    static final float[] coeff={0.125f,0.25f,0.5f};
    static float OffsetX=0;
    static float OffsetY=0;
    static boolean menu=true;
    static boolean inWindow(int x, int y)
    {
        float X=x-OffsetX;
        float Y=y-OffsetY;
        return (X > 0) && (X < WinX) && (Y > 0) && (Y < WinY);
    }

    static Clip makeClip (String str) throws Exception
    {
       try
        {

            Clip clip;
            AudioInputStream as=AudioSystem.getAudioInputStream(
                                         new File(str));
            DataLine.Info info = new DataLine.Info(Clip.class, as.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(as);
            return clip;
        }
        catch(Exception e)
        {
            throw e;
        }

    }
    static BufferedImage convert(BufferedImage in)
    {
        BufferedImage temp=new BufferedImage(in.getWidth(),in.getHeight(),
        BufferedImage.TYPE_INT_ARGB);
        temp.getGraphics().drawImage(in, 0, 0, null);
        return temp;
    }
    
}





