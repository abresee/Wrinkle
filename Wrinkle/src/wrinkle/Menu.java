/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.media.*;
/**
 *
 * @author alex
 */
abstract class Pan extends JPanel implements KeyListener, MouseInputListener
{

}
public class Menu extends Pan
{
    private enum Selected{play, options, quit};
    Selected s=Selected.play;
    private final BufferedImage background;
    MenuOption play;
    MenuOption options;
    MenuOption quit;
    ArrayList<MenuOption> ar;
    Player bgm0;
    Player bgm1;

    boolean firstTrack=true;
    boolean musicInit=false;
    Time startTime;

    private int oldX=0;
    private int oldY=0;

    

    Menu() throws IOException
    {
        String prefix="Data/images/menu/title/";
        play=new MenuOption(prefix+"play",Global.WinX/2,Global.WinY/3,new Playor());
       // options=new MenuOption(prefix+"options",100,200,new Quittor());
        quit=new MenuOption(prefix+"quit",Global.WinX/2,Global.WinY*2/3,new Quittor());
        background=ImageIO.read(new File(prefix+"title.png"));
        ar=new ArrayList<MenuOption>();
        File file0=new File("Data/audio/bgm/menu0.wav");
        File file1=new File("Data/audio/bgm/menu1.wav");
        try{
        bgm0=Manager.createRealizedPlayer(file0.toURI().toURL());
        bgm1=Manager.createRealizedPlayer(file1.toURI().toURL());
        }
        catch(NoPlayerException e)
        {
            e.printStackTrace();
        }
        catch(CannotRealizeException e)
        {
            e.printStackTrace();
        }
        
        
        ar.add(play);
        //ar.add(options);
        ar.add(quit);
        startTime=bgm1.getMediaTime();
        System.out.println(startTime.getSeconds());
        bgm0.start();
        musicInit=true;
    }
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(background, 0,0, this);
        switch(s)
        {
            case play:
                play.drawSelected(g);
                //options.drawUnselected(g);
                quit.drawUnselected(g);
                break;
            case quit:
                play.drawUnselected(g);
                //options.drawUnselected(g);
                quit.drawSelected(g);
                break;
            case options:
                play.drawUnselected(g);
                //options.drawSelected(g);
                quit.drawUnselected(g);
                break;
            default:
                break;
        }
        
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
        
    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {
        System.out.println(e.getButton());
        if(e.getButton()==MouseEvent.BUTTON1)
        {
            for(MenuOption i:ar)
            {
                Rectangle2D.Float f=i.getRect();
                if(f.contains(e.getPoint()))
                {
                    System.out.println("CALL");
                    i.call();
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
        if(!Global.menu)
        {

        }
    }
    public boolean musicStopped()
    {
        if(!musicInit)
        {
            return false;
        }
        if(firstTrack)
        {
            boolean b=(bgm0.getState()==Player.Prefetched);
            System.out.println(b);
            if(b)
            {
                firstTrack=false;
            }
            return b;
        }
        return bgm1.getState()==Player.Prefetched;
    }
    public void startNextTrack()
    {
        bgm1.setMediaTime(startTime);
        bgm1.start();
    }
    public void stopMusic()
    {
        bgm0.stop();
        bgm1.stop();
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

    private final Object lock=new Object();

    private boolean listenDrag;

    int x;
    int y;

    Functor f;

    MenuOption(String str,int x_,int y_, Functor f_) throws IOException
    {
          imageS=ImageIO.read(new File(str+"Select.png"));
          imageU=ImageIO.read(new File(str+".png"));

          x=x_-imageS.getWidth()/2;
          y=y_-imageS.getHeight()/2;
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
    Rectangle2D.Float getRect()
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
class Playor extends Functor
{
    void call()
    {
        System.out.println("FALSIFIED");
        Global.menu=false;
        System.out.println(Global.menu);
    }
}

