/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.Point;
import java.awt.Graphics2D;
/**
 * Class defining the Dragon enemy
 * @author a.bresee
 */
public final class Dragon extends Enemy{

   static SFX sfx;
   FireHelper fhelp;
   int fireTimer=0;
   static
   {
       try
       {
           sfx = new SFX("dragon");
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
   SFX getSfx()
    {
        return sfx;
    }
    Dragon(Wrinkle w,int X, int Y)
    {
        super(w,"dragon",X,Y);
        fhelp=new FireHelper(this);
        set=new DragonAnimationSet();
        m=JobMode.dragon;
        curSprite = set.getNextSprite(state, facingLeft);
    }
    @Override
    void draw(Graphics2D g)
    {
        super.draw(g);
        fhelp.draw(g);
    }

    @Override
    void updateState()
    {
        if(!active)
        {
            state=State.sleeping;
        }
        else
        {
            super.updateState();
        }
    }
    @Override
    void die()
    {
        super.die();
        fhelp.fireOff();
    }
    @Override
    void update(GameObjects go)
    {
        super.update(go);
        fhelp.update(go);        
    }
    boolean isBreathingFire()
    {
        return fhelp.isBreathingFire();
    }
    protected void idleScript()
    {
        if(fhelp.isBreathingFire())
        {
            fhelp.fireOff();
        }
    }
    protected void activeScript()
    {
        boolean cond=((wrinkle.getX()-x)>0);
        velX=(float)(.1*((cond)?1:-1));
        facingLeft=!cond;
        fhelp.setLoc(new Point(Math.round(wrinkle.x-Global.OffsetX),Math.round(wrinkle.y-Global.OffsetY)));
        if(fireTimer<75)
        {
            if(!fhelp.isBreathingFire())
            {
                fhelp.fireOn();
            }
        }
        else
        {
            if(fhelp.isBreathingFire())
            {
                fhelp.fireOff();
            }
        }
        ++fireTimer;
        fireTimer%=200;
    }
   
}
