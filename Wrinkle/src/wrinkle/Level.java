/*
 * Game.java
 *
 * Created on February 8, 2011, 9:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package wrinkle;

import java.awt.*;


import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Class representing the actual game simulation itself. 
 * @author a.bresee
 */
public class Level {

    private Wrinkle wrinkle;
    private GameObjects gameObjects;
    private BufferedImage[] backgrounds;
    private BufferedImage buff;
    private BufferedImage heartFilled;
    private BufferedImage heartUnfilled;
    private Graphics2D buffg;
    private AffineTransform at;
    BGMHandler bgm;

    public Level() {

        at = new AffineTransform();

        gameObjects = new GameObjects();
        ArrayList<String> bgms=new ArrayList<String>();
        bgms.add("Data/audio/bgm/");
        bgms.add("base");
        bgms.add("dragon");
        bgms.add("bird");
        String prefix = "Data/Images/";
        backgrounds = new BufferedImage[3];
        try {
            backgrounds[2] = ImageIO.read(new File(prefix + "background/bg1.png"));
            backgrounds[1] = ImageIO.read(new File(prefix + "background/bg2.png"));
            backgrounds[0] = ImageIO.read(new File(prefix + "background/bg3.png"));
            heartFilled = ImageIO.read(new File(prefix + "hud/heartFilled.png"));
            heartUnfilled = ImageIO.read(new File(prefix + "hud/heartUnfilled.png"));
            bgm=new BGMHandler(bgms);
        } catch (Exception e) {
            e.printStackTrace();
        }

        buff = new BufferedImage(Global.WinX, Global.WinY, BufferedImage.TYPE_INT_RGB);
        buffg = buff.createGraphics();
        buffg.setBackground(Color.cyan);


        wrinkle = new Wrinkle(Global.WinX / 4 + 1, Global.WinY - 200);

        gameObjects.add(new Terrain(0, Global.GroundLevel,400, 400, Color.GREEN));
        gameObjects.add(new DieBox(0, Global.WinY, 5*Global.WinX, Global.WinY));
        gameObjects.add(new Terrain(0, Global.GroundLevel,400, 400, Color.GREEN));
        gameObjects.add(new Terrain(500, Global.GroundLevel, 300, 400, Color.RED));
        gameObjects.add(new Terrain(300, 400, 100, 100));
        gameObjects.add(new Terrain(200, 200, 50, 50));
        gameObjects.add(new Terrain(Global.WinX, Global.GroundLevel, 400, 400, Color.MAGENTA));
        gameObjects.add(new Terrain(1200, Global.GroundLevel, 400, 400, Color.GREEN));
        gameObjects.add(new Terrain(1800, Global.GroundLevel, 400, 400, Color.GREEN));
        gameObjects.add(new Bird(wrinkle, 600, 300));
        gameObjects.add(new Dragon(wrinkle, 1200, Global.GroundLevel));
        gameObjects.add(new Terrain(-400, -Global.WinY, 400, 3*Global.WinY, Color.DARK_GRAY));
    }

    Wrinkle getWrinkle() {
        return wrinkle;
    }

    void Jump() {
        wrinkle.jump();
    }

    void goRight() {
        wrinkle.goRight();
    }

    void goLeft() {
        wrinkle.goLeft();
    }

    void jump() {
        wrinkle.jump();
    }

    void clickAction(Point p) {
        wrinkle.clickAction(p);
    }
    void keyAction()
    {
        wrinkle.keyAction();
    }
    void unKeyAction()
    {
        wrinkle.unKeyAction();
    }
    void unJob()
    {
        wrinkle.unJob();
    }

    void setMouseLoc(Point p)
    {
        wrinkle.setMouseLoc(p);
    }
    void unBreatheFire() {
        wrinkle.unBreatheFire();
    }

    void unGoRight() {
        wrinkle.unGoRight();
    }

    void unGoLeft() {
        wrinkle.unGoLeft();
    }

    public BufferedImage getImage() {
        return buff;
    }

    void drawBackground() {

        //buffg.clearRect(0,0,Global.WinX,Global.WinY);

        for (int i = 0; i < backgrounds.length; ++i) {
            float x = -(Global.coeff[i] * Global.OffsetX) % Global.WinX;
            float y = -(Global.coeff[i] * Global.OffsetY);


            if (x >= 0) {
                x -= Global.WinX;
            }
            at.setToTranslation(x, y);
            buffg.setTransform(at);
            buffg.drawImage(backgrounds[i], 0, 0, null);
        }
        at.setToIdentity();
        buffg.setTransform(at);

    }

    void drawForeground() {
        //buffg.clearRect(0, 0, Global.WinX, Global.WinY);
        at.setToTranslation(-Global.OffsetX, -Global.OffsetY);
        buffg.setTransform(at);

        gameObjects.draw(buffg);

        wrinkle.draw(buffg);
        drawHUD();

    }

    void drawHUD()
    {
        int hearts=wrinkle.getHealth();
        int maxHearts=wrinkle.getMaxHealth();
        at.setToIdentity();
        buffg.setTransform(at);
        for(int i=0;i<maxHearts;++i)
        {
            BufferedImage draw=(i<hearts)?heartFilled:heartUnfilled;
            buffg.drawImage(draw,0+i*draw.getWidth(),0,null);
        }
    }

    void go() throws DeadException {

        try{
        gameObjects.update();
        wrinkle.update(gameObjects);
        }
        catch(DeadException e)
        {
            bgm.closeAll();
            throw e;
        }
        bgm.change(wrinkle.getModeString());
        bgm.step();
        drawBackground();
        drawForeground();

    }
}
