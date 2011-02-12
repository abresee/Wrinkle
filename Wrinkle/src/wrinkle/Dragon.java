/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

/**
 *
 * @author a.bresee
 */
final class Dragon  extends Enemy{

    Dragon()
    {
        this(0,0);
    }
    Dragon(int X, int Y)
    {
        super("dragon",X,Y);
    }
}
