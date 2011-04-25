/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

/**
 *
 * @author alex
 */
public class SpawnActor
{
    int x;
    int y;
    ActorType a;
    GameObjects go;
    Actor act=null;
    SpawnActor(int x_, int y_, ActorType a_, GameObjects go_)
    {
        x=x_;
        y=y_;
        a=a_;
        go=go_;
    }
    void update()
    {
        if(!Global.inWindow(x,y) && (act==null || act.isDead()))
        {
            spawn();
        }
    }
    void spawn()
    {
        act=go.add(a, x, y);
        System.out.println("spawn");
    }
}
