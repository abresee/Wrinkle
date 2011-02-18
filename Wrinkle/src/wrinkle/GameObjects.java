/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author a.bresee
 */
class GameObjects
{
    private ArrayList<Terrain> terrains;
    private ArrayList<Actor> actors;

    GameObjects()
    {
        terrains=new ArrayList<Terrain>();
        actors=new ArrayList<Actor>();
    }

    void add(Terrain t)
    {
        terrains.add(t);
    }

    void add(Actor a)
    {
        actors.add(a);
    }

    List<Terrain> getTerrains()
    {
        return Collections.unmodifiableList(terrains);
    }

    List<Actor> getActors()
    {
        return Collections.unmodifiableList(actors);
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
    void update()
    {
        ArrayList<Actor> newActors=new ArrayList<Actor>();
        for(Actor i:actors)
        {
            if(!i.isDead())
            {
            i.update(this);
            newActors.add(i);
            }
        }
        actors=newActors;
    }
}
