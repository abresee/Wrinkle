/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author a.bresee
 */
class GameObjects
{
    private LinkedList<Terrain> terrains;
    private LinkedList<Actor> actors;
    private LinkedList<DieBox> dieboxes;

    GameObjects()
    {
        terrains=new LinkedList<Terrain>();
        actors=new LinkedList<Actor>();
        dieboxes=new LinkedList<DieBox>();
    }
    void add(Terrain t)
    {
        terrains.add(t);
    }
    void add(Actor a)
    {
        actors.add(a);
    }
    void add(DieBox d)
    {
        dieboxes.add(d);
    }
    List<Terrain> getTerrains()
    {
        return Collections.unmodifiableList(terrains);
    }

    List<Actor> getActors()
    {
        return Collections.unmodifiableList(actors);
    }
    List<DieBox> getDieBoxes()
    {
        return Collections.unmodifiableList(dieboxes);
    }

    void draw(Graphics2D g)
    {
        for(Terrain i:terrains)
        {
            i.draw(g);
        }
        for(Actor i:actors)
        {
            i.draw(g);
        }
    }
    void update() throws DeadException
    {
        LinkedList<Actor> newActors=new LinkedList<Actor>();
        for(Actor i:actors)
        {
            if(!i.isDead())
            {
                i.update();
                newActors.add(i);
            }
        }
        actors=newActors;
    }
    void moveX()
    {
        for(Actor i:actors)
        {
            i.moveX();
        }
    }
}
