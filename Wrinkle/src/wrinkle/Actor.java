/*
 * Actor.java
 *
 * Created on February 8, 2011, 8:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.image.BufferedImage;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import java.io.File;
import javax.sound.sampled.*;
import java.awt.Graphics2D;

/**
 * Abstract superclass of game actors -- that is, objects that have animations,
 * sounds, etc. 
 * @author a.bresee
 */
public abstract class Actor extends Collidable{    
    
    float velX;
    float velY;
    float accelX;
    float accelY;
    float maxVelX;
    float maxVelY;

    float mass;
    
    int frametime=5;
    int timecount=0;
    int frame=0;

    boolean onTheGround;
    boolean goingRight;
    boolean goingLeft;
    boolean facingLeft;
    boolean insideOkay;

    boolean soundImplemented;

    BufferedImage curSprite;
    BufferedImage rightwalk[];
    BufferedImage leftwalk[];
    BufferedImage rightidle;
    BufferedImage leftidle;
    BufferedImage rightJump;
    BufferedImage leftJump;

    Clip jumpsnd;
    Clip walk1;
    Clip walk2;
    Clip land;

    AffineTransform at;

    Actor(String str, int X, int Y)
    {
        initImages(str);
        initPhys(X,Y);

        at=new AffineTransform();

        onTheGround=true;
        insideOkay=false;
        goingRight=false;
        goingLeft=false;
        facingLeft=false;
    }

    final void initPhys(int X, int Y)
    {
        x=X;
        y=Y;

        velX=0;
        velY=0;

        maxVelX=0.5f;
        maxVelY=0.6f;

        accelX=0;
        accelY=Global.gravity;
    }

    final void initImages(String str)
    {
        String prefix="Data/images/"+str+"/";
        rightwalk=new BufferedImage[Global.framecount];
        leftwalk=new BufferedImage[Global.framecount];
        try
        {
            rightidle=ImageIO.read(new File(prefix+"rightidle.png"));
            leftidle=ImageIO.read(new File(prefix+"leftidle.png"));
            String name="rightwalk";

            for(int i=0;i<Global.framecount;++i)
            {
              rightwalk[i]=ImageIO.read(new File(prefix+name+i+".png"));
            }

            name="leftwalk";
            for(int i=0;i<Global.framecount;++i)
            {
                leftwalk[i]=ImageIO.read(new File(prefix+name+i+".png"));
            }

            rightJump=ImageIO.read(new File(prefix+"rightjump.png"));
            leftJump=ImageIO.read(new File(prefix+"leftjump.png"));

        }
        catch(Exception e){e.printStackTrace();}
    }

    void initSounds(String str)
    {

        String prefix="Data/audio/"+str+"/";

        try
        {
            jumpsnd=Global.makeClip(prefix+"jump.wav");
            walk1=Global.makeClip(prefix+"walk1.wav");
            walk2=Global.makeClip(prefix+"walk2.wav");
            land=Global.makeClip(prefix+"land.wav");
            soundImplemented=true;
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }

    }
    void generateBoundingBox()
    {
      bBox=new Rectangle2D.Float(x,y,curSprite.getWidth(),curSprite.getHeight());
    }

    int getHeight() {
        return curSprite.getHeight();

    }

    int getWidth() {
        return curSprite.getWidth();

    }
    void draw(Graphics2D g) {        
        g.drawImage(curSprite, Math.round(x), Math.round(y), null);        
    }
}
