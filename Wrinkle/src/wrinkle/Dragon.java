/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

/**
 * Class defining the Dragon enemy
 * @author a.bresee
 */
public final class Dragon  extends Enemy{

    Dragon()
    {
        this(0,0);
    }
    Dragon(int X, int Y)
    {
        super("dragon",X,Y);
    }

    @Override
    void die()
    {

    }
    @Override
    void hurt()
    {

    }
}
