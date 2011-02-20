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

/**
 * Abstract superclass of anything that can collide with anything else
 * @author a.bresee
 */
public abstract class Collidable
{
    /** x position */
    protected float x;
    /** y position */
    protected float y;
    protected Rectangle2D collideShape;
    float getX(){return x;}
    float getY(){return y;}
    abstract int getWidth();
    abstract int getHeight();
    abstract void draw(Graphics2D g);
    abstract void generateBoundingBox();
    public Rectangle2D getbBox(){return collideShape;}
   
            
}
