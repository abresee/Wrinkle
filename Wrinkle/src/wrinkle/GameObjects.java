/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.util.ArrayList;
import java.awt.Graphics2D;
/**
 *
 * @author a.bresee
 */
class GameObjects
{
    ArrayList<Terrain> terrains;
    ArrayList<Actor> actors;

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

    ArrayList<Terrain> getTerrains()
    {
        return terrains;
    }

    ArrayList<Actor> getActors()
    {
        return actors;
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
        for(Actor i:actors)
        {
            i.update(this);
        }
    }
}
