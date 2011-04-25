/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import javax.sound.sampled.*;

/**
 *
 * @author alex
 */
public class SFX {
    private final Clip jumpsnd;
    private final Clip walk1;
    private final Clip walk2;
    private final Clip land;
    private final Clip die;
    SFX(String str) throws Exception
    {
        String prefix = "Data/audio/sfx/" + str + "/";

        try {
            jumpsnd = Global.makeClip(prefix + "jump.wav");
            walk1 = Global.makeClip(prefix + "walk1.wav");
            walk2 = Global.makeClip(prefix + "walk2.wav");
            land = Global.makeClip(prefix + "land.wav");
            die = Global.makeClip(prefix+"die.wav");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
    public void jump()
    {
        playClip(jumpsnd);
    }
    public void walk1()
    {
        playClip(walk1);
    }
    public void walk2()
    {
        playClip(walk2);
    }
    public void land()
    {
        playClip(land);
    }
    public void die()
    {
        playClip(die);
    }
    public void playClip(Clip clip)
    {
        clip.setFramePosition(0);
        clip.start();
    }
}

