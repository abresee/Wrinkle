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

    Wrinkle wrinkle;
    protected boolean active=false;
    protected boolean vulnerable=false;
    private final int waittime=100;
    static LookupOp luo;
    private int vulncount;


    static
    {
        final int size=256;
        short[] r = new short[size];
        short[] g = new short[size];
        short[] b = new short[size];
        short[] a = new short[size];
        short[][] data = new short[][]{r,g,b,a};
        for (int i = 0; i < size; ++i)
        {
            r[i] = (short)(255);
            g[i] = (short)(0);
            b[i]= (short)(0);
            a[i] = (short)(i);
        }

        ShortLookupTable l=new ShortLookupTable(0,data);
        luo=new LookupOp(l, null);
    }

    Enemy(Wrinkle w,String str, int X, int Y)
    {
        super(str, X, Y);
        wrinkle=w;
        state=State.sleeping;
    }

    
    @Override
    void update(GameObjects go)
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
        if(vulnerable)
        {
            vulncount++;
        }
    }
    void bitten() 
    {

        if(vulnerable)
        {
           if(vulncount>waittime+10)
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
        return vulnerable&&(vulncount>waittime);
    }
    @Override
    void draw(Graphics2D g)
    {
        if(vulnerable&&vulncount/5%5==0)
        {

            g.drawImage(curSprite, luo, (int)x, (int)y);
        }
        else
        {
            super.draw(g);
        }
    }
    void handleWrinkleCollisionX(Wrinkle w)
    {
        if(w.isBiting())
        {
            bitten();
        }
        
    }
    void handleWrinkleCollisionY(Wrinkle w)
    {

    }
    boolean isPlayer(){return false;}
    boolean isEnemy(){return true;}
    boolean isBiting(){return false;}
   
    abstract protected void activeScript();
    abstract protected void idleScript();
}
