/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

/**
 *
 * @author alex
 */
public class Chameleon extends Enemy{
     static SFX sfx;
   static
   {
       try
       {
           sfx = new SFX("chameleon");
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
   SFX getSfx()
    {
        return sfx;
    }

    Chameleon(Wrinkle wrinkle, int x, int y)
    {
        super(wrinkle,"chameleon",x,y);
    }
    protected void idleScript()
    {

    }
    protected void activeScript()
    {

    }


}
