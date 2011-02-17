/*
 * Collidable.java
 *
 * Created on February 8, 2011, 8:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.geom.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Abstract superclass of anything that can collide with anything else
 * @author a.bresee
 */
public abstract class Collidable
{
    /** x position */
    float x;
    /** y position */
    float y;
    protected BufferedImage curSprite;
    Rectangle2D collideShape;
    float getX(){return x;}
    float getY(){return y;}
    int getWidth(){return curSprite.getWidth();}
    int getHeight(){return curSprite.getHeight();}
    void draw(Graphics2D g)
    {
        g.drawImage(curSprite, Math.round(x), Math.round(y), null);
    }
    void generateBoundingBox()
    {
      collideShape=new Rectangle2D.Float(x,y,curSprite.getWidth(),curSprite.getHeight());
    }
    public Rectangle2D getbBox(){return collideShape;}
   
            
}
