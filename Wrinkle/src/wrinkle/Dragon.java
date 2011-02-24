/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

/**
 * Class defining the Dragon enemy
 * @author a.bresee
 */
public final class Dragon extends Enemy{

    
    Dragon(Wrinkle wrinkle, int X, int Y)
    {
        super(wrinkle, "dragon",X,Y);
        set=new DragonAnimationSet();
        m=JobMode.dragon;
        curSprite = set.getNextSprite(state, facingLeft);
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
    protected void idleScript()
    {

    }
    protected void activeScript()
    {
        
    }
   
}
