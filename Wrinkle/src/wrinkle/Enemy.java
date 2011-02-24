/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.Graphics2D;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

/**
 * Abstract superclass for enemy characters 
 * @author a.bresee
 */
abstract public class Enemy extends Actor{

    
    protected boolean active=false;
    protected Wrinkle wrinkle;
    protected boolean vulnerable=false;
    protected int wait;
    private final int waittime=100;
    static LookupOp luo;


    static
    {
         short[] data = new short[256];
    for (short i = 0; i < 256; i++)
        {
        data[i] = (short)255;
        }

        ShortLookupTable l=new ShortLookupTable(0, data);
        luo=new LookupOp(l, null);
    }

    Enemy(Wrinkle wrinkle_, String str, int X, int Y)
    {
        super(str, X, Y);
        wrinkle=wrinkle_;
        state=State.sleeping;
        wait=0;
    }

    
    @Override
    void update(GameObjects go) throws DeadException
    {
        if(Math.abs(x-wrinkle.getX())<300||recovering)
        {
            active=true;
        }
        else
        {
            active=false;
        }
        if(active)
        {
            activeScript();
        }
        else
        {
            idleScript();
        }
        super.update(go);
    }
    void bitten() throws DeadException
    {

        if(vulnerable)
        {
           if(wait++>waittime)
            {
            die();
            }
        }
        else
        {
            vulnerable=true;
        }
        

    }
    @Override
    boolean isVulnerable()
    {
        return vulnerable&&(wait>waittime);
    }
    @Override
    void draw(Graphics2D g)
    {
        if(vulnerable)
        {
            g.drawImage(curSprite, luo, (int)x, (int)y);
        }
        else
        {
            super.draw(g);
        }
    }
    boolean isPlayer(){return false;}
    boolean isEnemy(){return true;}
    boolean isBiting(){return false;}
   
    abstract protected void activeScript();
    abstract protected void idleScript();
}
