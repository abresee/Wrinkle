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
    private BufferedImage lifebird;
    private BufferedImage lifedragon;
    private BufferedImage lifenormal;
    private Graphics2D buffg;
    private AffineTransform at;
    private int lives;
    private BGMHandler bgm;

    public Level(int lives_) {
        lives=lives_;
        at = new AffineTransform();

        gameObjects = new GameObjects();
        ArrayList<String> bgms=new ArrayList<String>();
        bgms.add("Data/audio/bgm/level1/");
        bgms.add("base");
        bgms.add("dragon");
        bgms.add("bird");
        bgms.add("chameleon");
        String prefix = "Data/Images/";
        backgrounds = new BufferedImage[3];
        try {
            backgrounds[2] = ImageIO.read(new File(prefix + "background/bg1.png"));
            backgrounds[1] = ImageIO.read(new File(prefix + "background/bg2.png"));
            backgrounds[0] = ImageIO.read(new File(prefix + "background/bg3.png"));
            heartFilled = ImageIO.read(new File(prefix + "hud/heartFilled.png"));
            heartUnfilled = ImageIO.read(new File(prefix + "hud/heartUnfilled.png"));
            lifebird = ImageIO.read(new File(prefix + "hud/lifebird.png"));
            lifedragon = ImageIO.read(new File(prefix+"hud/lifedragon.png"));
            lifenormal = ImageIO.read(new File(prefix+"hud/lifenormal.png"));
            bgm=new BGMHandler(bgms);
        } catch (Exception e) {
            e.printStackTrace();
        }

        buff = new BufferedImage(Global.WinX, Global.WinY, BufferedImage.TYPE_INT_RGB);
        buffg = buff.createGraphics();
        buffg.setBackground(Color.cyan);


        gameObjects.add(new Terrain(-50,-5000,50,5000+Global.WinY));
        gameObjects.add(new DieBox(0, Global.WinY+300, 100*Global.WinX, Global.WinY));

        gameObjects.addGround(0,Global.GroundLevel,3);
        gameObjects.addGround(800,Global.GroundLevel,3);
        gameObjects.addLogs(1000,300,2);
        gameObjects.addGround(1600, Global.GroundLevel, 8);
        gameObjects.add(new SpawnActor(2050,Global.GroundLevel,ActorType.Bird,gameObjects));
        gameObjects.addGround(4200,Global.GroundLevel,17);
        gameObjects.add(new SpawnActor(5200,Global.GroundLevel,ActorType.Dragon,gameObjects));
        gameObjects.addLogs(5500,300,2);
        gameObjects.addGround(7600,Global.GroundLevel,6);
        gameObjects.addLogs(7900,300,3);
        gameObjects.add(new SpawnActor(8700,100,ActorType.Dragon,gameObjects));
        gameObjects.addLogs(9000,300,2);
        gameObjects.addLogs(10000,300,3);
        gameObjects.addLogs(11000,300,2);
        gameObjects.addGround(11500,300,6);
        gameObjects.add(new Terrain(12700,0,200,Global.WinY,new Color(99,44,27)));
        gameObjects.addGround(12700,-100);

        
        gameObjects.add(ActorType.Wrinkle,Global.WinX / 4 + 1, Global.WinY - 200);

        try{
            wrinkle=gameObjects.getWrinkle();
        }
        catch(NoWrinkleException e)
        {
            e.printStackTrace();
        }
       
        
    }

    Wrinkle getWrinkle() {
        return wrinkle;
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

        buffg.setBackground(Color.GRAY);
        buffg.clearRect(Math.round(Global.OffsetX),
                        Math.round(Global.OffsetY),
                        Global.WinX,Global.WinY);

        for (int i = 0; i < backgrounds.length; ++i) {
            float x = (Global.coeff[i] * Global.OffsetX) % Global.WinX;
            float y = (Global.coeff[i] * Global.OffsetY)+1000;


            if (x < 0) {
                x += Global.WinX;
            }
            buffg.drawImage(backgrounds[i],0,0,Global.WinX, Global.WinY,
                    Math.round(x),Math.round(y),
                    Math.round(x+Global.WinX),Math.round(y+Global.WinY), null);
        }
    }

    void drawForeground() {
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

        BufferedImage l;
        switch(wrinkle.getMode())
        {
            case dragon:
                l=lifedragon;
                break;
            case bird:
                l=lifebird;
                break;
            case normal:
                l=lifenormal;
                break;
            default:
                l=lifenormal;
                break;
        }
        buffg.drawImage(l, 670,0, null);
        buffg.setColor(Color.BLACK);
        buffg.setFont(new Font("Serif", 0, 35));
        buffg.drawString(""+lives, 747, 26);
    }

    boolean go()
    {
        gameObjects.update();
        wrinkle.update(gameObjects);

        if(wrinkle.isDead())
        {
            if(lives==0)
            {
                bgm.closeAll();
                return false;
            }
            --lives;
            try{
            wrinkle=gameObjects.reset();
            wrinkle=gameObjects.getWrinkle();
            }
            catch(NoWrinkleException e)
            {
                e.printStackTrace();
            }
        }
        bgm.change(wrinkle.getModeString());
        bgm.step();
        drawBackground();
        drawForeground();
        return true;
    }
}
