/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
/**
 *
 * @author alex
 */
abstract public class AnimationSet {
    abstract BufferedImage getNextSprite(State s, boolean facingLeft);
    BufferedImage getNextSprite(State s,JobMode m,boolean facingLeft)
    {
        return getNextSprite(s, facingLeft);
    }
}
