/*
 * Terrain.java
 *
 * Created on February 8, 2011, 8:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.geom.*;
import java.awt.*;


/**
 * Class for the game world's terrain
 * @author a.bresee
 */
public class Terrain extends StaticCollidable
{
   int width;
   int height;
   
   Color color;

   @Override
   int getWidth(){return width;}
   @Override
   int getHeight(){return height;}

   @Override
   void generateBoundingBox(){}

   @Override
   void draw(Graphics2D g)
   {
       g.setColor(color); 
       g.setStroke(new BasicStroke(5));
       g.fillRect(Math.round(x),Math.round(y),width,height);
   }
   Terrain()
   {
       this(0,0,10,10,Color.GRAY);
   }
   Terrain(int X, int Y, int Width, int Height)
   {
       this(X,Y,Width,Height,Color.GRAY);
   }
   
   Terrain(int X, int Y, int Width, int Height, Color c)
   {
       x=X;
       y=Y;
       width=Width;
       height=Height;
       color=c;
       collideShape=new Rectangle2D.Float(x,y,width,height);
   }
   boolean isDeadly()
   {
       return false;
   }
}
