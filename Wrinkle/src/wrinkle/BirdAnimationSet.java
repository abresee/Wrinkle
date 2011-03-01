/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
/**this class contains a static animation collection -- this way, no matter how many
 * enemies are spawned, the disk is only accessed once per sprite. this pattern is repeated for
 * all enemy animation sets.
 *
 * @author alex
 */
public class BirdAnimationSet extends AnimationSet{
    static private AnimationCollection a;
    static private String str="Data/images/bird/";
    static
    {
        a=new AnimationCollection(str);
    }
    
    BufferedImage getNextSprite(State s, boolean facingLeft)
    {
        return a.getNextSprite(s, facingLeft);
    }

}
