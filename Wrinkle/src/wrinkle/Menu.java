/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
/**
 *
 * @author alex
 */
public class Menu extends Pan
{
    private enum Selected{play, options, quit};
    Selected s=Selected.play;
    private final BufferedImage background;
    MenuOption play;
    MenuOption options;
    MenuOption quit;
    ArrayList<MenuOption> ar;

    

    private int oldX=0;
    private int oldY=0;

    

    private boolean exists=true;
    Menu() throws IOException
    {
        String prefix="Data/images/menu/title/";
        play=new MenuOption(prefix+"play",400,300,new Quittor());
        options=new MenuOption(prefix+"options",100,200,new Quittor());
        quit=new MenuOption(prefix+"quit",500,400,new Quittor());
        background=ImageIO.read(new File(prefix+"title.png"));
        ar=new ArrayList<MenuOption>();

        ar.add(play);
        ar.add(options);
        ar.add(quit);
           
        
    }
    public void paint(Graphics g)
    {
        g.drawImage(background, 0,0, this);
        switch(s)
        {
            case play:
                play.drawSelected(g);
                options.drawUnselected(g);
                quit.drawUnselected(g);
                break;
            case quit:
                play.drawUnselected(g);
                options.drawUnselected(g);
                quit.drawSelected(g);
                break;
            case options:
                play.drawUnselected(g);
                options.drawSelected(g);
                quit.drawUnselected(g);
                break;
            default:
                break;
        }
        
    }
    boolean exists()
    {
        return exists;
    }
    public void keyTyped(KeyEvent e)
    {

    }
    public void keyPressed(KeyEvent e)
    {

    }
    public void keyReleased(KeyEvent e)
    {

    }
    public void mouseClicked(MouseEvent e)
    {
        System.out.println(e.getButton());
        if(e.getButton()==MouseEvent.BUTTON1)
        {
            for(MenuOption i:ar)
            {
                System.out.println("x1 "+i.getRect().getMinX());
                System.out.println("y1 "+i.getRect().getMinY());
                System.out.println("x2 "+i.getRect().getMaxX());
                System.out.println("y2 "+i.getRect().getMaxY());
                System.out.println("pntX "+e.getPoint().getX());
                System.out.println("pntY "+e.getPoint().getX());
                if(i.getRect().contains(e.getPoint()))
                {
                    System.out.println("CALL");
                    //i.call();
                }
            }
        }
        else if(e.getButton()==MouseEvent.BUTTON3)
        {
            for(MenuOption i:ar)
            {
                if(i.getRect().contains(e.getPoint()))
                {
                    i.listenDrag();
                }
            }
        }
    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {

    }
    public void mouseReleased(MouseEvent e)
    {
        for(MenuOption i:ar)
        {
            i.unListenDrag();
        }
    }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    public void mouseDragged(MouseEvent e)
    {
        for(MenuOption i:ar)
        {
            if(i.isListenDrag())
            {
                int delX=e.getX()-oldX;
                int delY=e.getY()-oldY;
                i.delX(delX);
                i.delY(delY);

            }
        }
        oldX=e.getX();
        oldY=e.getY();
    }
}
class MenuOption
{
    private final BufferedImage imageS;
    private final BufferedImage imageU;

    final Object lock=new Object();

    boolean listenDrag;

    int x;
    int y;

    Functor f;

    MenuOption(String str,int x_,int y_, Functor f_) throws IOException
    {
          imageS=ImageIO.read(new File(str+"Select.png"));
          imageU=ImageIO.read(new File(str+".png"));

          x=x_;
          y=y_;
          f=f_;

    }
    synchronized void drawSelected(Graphics g)
    {
        Graphics2D g1=(Graphics2D)g;
        g1.drawImage(imageS,x, y, null);
    }
    synchronized void drawUnselected(Graphics g)
    {
        Graphics2D g1=(Graphics2D)g;
        g1.drawImage(imageU,x, y, null);
    }
    Rectangle2D getRect()
    {
        return new Rectangle2D.Float(x, y, imageS.getWidth(), imageS.getHeight());
    }
    void call()
    {
        f.call();
    }
    void listenDrag()
    {
        listenDrag=true;
    }
    void unListenDrag()
    {
        listenDrag=false;
    }
    boolean isListenDrag()
    {
        return listenDrag;
    }
    synchronized void delX(int X)
    {
        x+=X;
    }
    synchronized void delY(int Y)
    {
        y+=Y;
    }

}

abstract class Functor
{
    abstract void call();
}
class Quittor extends Functor
{
    void call()
    {
        System.exit(0);
    }
}

