/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.util.ArrayList;
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
}
