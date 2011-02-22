/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

/**
 * Abstract superclass for enemy characters 
 * @author a.bresee
 */
abstract public class Enemy extends Actor{

    protected boolean active=false;
    protected Wrinkle wrinkle;
    protected boolean vulnerable=false;
    Enemy(Wrinkle wrinkle_, String str, int X, int Y)
    {
        super(str, X, Y);
        wrinkle=wrinkle_;
        state=State.sleeping;
    }

    @Override
    void update(GameObjects go) throws DeadException
    {
        if(Math.abs(x-wrinkle.getX())<300)
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
            die();
            return;
        }
        else
        {
            vulnerable=true;
        }

    }
   
    abstract protected void activeScript();
    abstract protected void idleScript();
}
