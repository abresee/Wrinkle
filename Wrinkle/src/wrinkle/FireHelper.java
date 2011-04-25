/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.util.LinkedList;
import java.awt.Point;
import java.awt.Graphics2D;
import javax.sound.sampled.*;

public class FireHelper {
    static Clip attack;
    static Clip loop;
    static Clip release;
    static
    {
        String prefix = "Data/audio/sfx/Fire/";
        try
        {
            attack=Global.makeClip(prefix+"attack.wav");
            loop=Global.makeClip(prefix+"loop.wav");
            release=Global.makeClip(prefix+"loop.wav");
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    final Object targetlock=new Object();
    final Object listlock=new Object();
    private Point target;
    private Actor parent;
    private LinkedList<Fire> fireList;
    boolean breathingFire=false;
    boolean isSound=false;
    boolean isLoop=false;

    FireHelper(Actor p)
    {
        parent=p;
        fireList=new LinkedList<Fire>();
    }
    boolean isBreathingFire()
    {
        return breathingFire;
    }
    void fireOn()
    {
        breathingFire=true;
    }
    void fireOff()
    {
        breathingFire=false;
        if(isSound)
        {
            loop.stop();
            playClip(release);
            isSound=false;
            isLoop=false;
        }
    }

    void fire()
    {
        double x_;
        double y_;
      // synchronized(targetlock)
       {
        x_ = target.getX()+Global.OffsetX;
        y_ = target.getY()+Global.OffsetY;
       }
        double spawnX=parent.x-20+((parent.facingLeft)?40:parent.getWidth());
        double spawnY=parent.y+40;

        double delx = x_-spawnX;
        double dely = y_-spawnY;

        double angle=Math.atan2(dely,delx);
        double delVelX=Math.cos(angle);
        double delVelY=Math.sin(angle);
        delVelX+=parent.velX;
        delVelY+=parent.velY;
        angle=Math.atan2(delVelY, delVelX);
        double mag=Math.sqrt(delVelX*delVelX+delVelY*delVelY);

        if(parent.facingLeft)
        {
            if(angle>0&&angle<3*Math.PI/4)
            {
                angle=3*Math.PI/4;
            }
            else if(angle<=0&&angle>-3*Math.PI/4)
            {
                angle=-3*Math.PI/4;
            }

        }
        else
        {
            if(angle>Math.PI/4)
            {
                angle=Math.PI/4;
            }
            else if(angle<-Math.PI/4)
            {
                angle=-Math.PI/4;
            }
        }
        synchronized(listlock){
        fireList.add(new Fire(parent,(float)spawnX,(float)spawnY,(float)mag,(float)angle));
        }
    }
    public void playClip(Clip clip)
    {
        clip.setFramePosition(0);
        clip.start();
    }
    public void loopClip(Clip clip)
    {
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    void update(GameObjects go)
    {
        if(breathingFire)
        {
            fire();
            if(!isSound)
            {
                playClip(attack);
                isSound=true;
            }
            else if(!attack.isRunning() && isLoop==false)
            {
                loopClip(loop);
                isLoop=true;
            }
        }
        LinkedList<Fire> bab2=new LinkedList<Fire>();
        for(Fire i:fireList)
        {
            if(!i.isDead())
            {
                i.update(go);
                bab2.add(i);
            }
        }
        fireList=bab2;
    }
    void setLoc(Point p)
    {
    //synchronized(targetlock)
        {
        target=p;
        }
    }
    void draw(Graphics2D g)
    {

    synchronized(listlock)
        {
            for(Fire i:fireList)
            {
                i.draw(g);
            }
        }
    }

}
