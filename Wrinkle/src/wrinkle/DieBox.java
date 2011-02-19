/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.Graphics2D;
/**
 *
 * @author a.bresee
 */
public class DieBox extends Terrain {
    DieBox(int x_, int y_,int width, int height)
    {
        super(y_, y_, width, height);
    }
    @Override
    void draw(Graphics2D g){}

    @Override
    boolean isDeadly()
    {
        System.out.println("LMAO");
        return true;
    }


}
