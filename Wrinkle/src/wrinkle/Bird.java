/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

/**
 * Class defining the Bird enemy
 * @author a.bresee
 */
public final class Bird extends Enemy
{
    Bird()
    {
        this(0,0);
    }
    Bird(int X, int Y)
    {
        super("bird",X,Y);
    }   
    
}
