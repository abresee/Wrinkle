/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
/**
 *
 * @author a.bresee
 */
final class Bird extends Enemy
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
