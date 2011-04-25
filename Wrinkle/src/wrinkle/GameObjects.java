/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author a.bresee
 */
class NoWrinkleException extends Exception
{

}

class GameObjects
{
    Wrinkle wrinkle=null;

    private final float offX=Global.OffsetX;
    private final float offY=Global.OffsetY;
    private class ActorPattern
    {
        public final ActorType a;
        public final int x;
        public final int y;
        ActorPattern(ActorType a_, int x_, int y_)
        {
            a=a_;
            x=x_;
            y=y_;
        }
    }
    ActorPattern wrinklePattern;
    private ArrayList<Terrain> terrains;
    private ArrayList<Actor> actors;
    private ArrayList<DieBox> dieboxes;
    private ArrayList<ActorPattern> actorpatterns;
    private ArrayList<SpawnActor> spawns;
    
    GameObjects()
    {
        terrains=new ArrayList<Terrain>();
        actors=new ArrayList<Actor>();
        dieboxes=new ArrayList<DieBox>();
        spawns=new ArrayList<SpawnActor>();
    }
    void reset() throws NoWrinkleException
    {
        for(Actor i:actors)
        {
            i.die();
        }
        actors=new ArrayList<Actor>();
        Global.OffsetX=offX;
        Global.OffsetY=offY;
        for(SpawnActor i:spawns)
        {
            i.spawn();
        }
    }
    
    public void add(SpawnActor a)
    {
        spawns.add(a);
    }
    Actor add(ActorType a_, int x, int y)
    {

        ActorPattern a=new ActorPattern(a_,x,y);
        Actor actor=null;
        try{
                actor=getActor(a);
            }
        catch(NoWrinkleException e){e.printStackTrace();}
        if(a_==ActorType.Wrinkle)
        {
            wrinklePattern=a;
            wrinkle=(Wrinkle)actor;
        }
        else
        {
            actors.add(actor);
        }
        return actor;
    }
    private Actor getActor(ActorPattern a) throws NoWrinkleException
    {
        
        switch(a.a)
        {
            case Wrinkle:
                return new Wrinkle(a.x,a.y);
            case Bird:
                if(wrinkle==null)
                {
                    throw new NoWrinkleException();
                }
                System.out.println("new bird");
                return new Bird(wrinkle,a.x,a.y);
            case Dragon:
                if(wrinkle==null)
                {
                    throw new NoWrinkleException();
                }
                return new Dragon(wrinkle,a.x,a.y);
            case Chameleon:
            default:
                if(wrinkle==null)
                {
                    throw new NoWrinkleException();
                }
                return new Chameleon(wrinkle,a.x,a.y);
        }
    }
    void addGround(int x, int y)
    {
        this.addGround(x, y, 1);
    }
    void addGround(int x, int y, int Times)
    {
        for(int i=0;i<Times;++i)
        {
            terrains.add(new Ground(x+Math.round(i*Ground.sWidth()),y));
        }
    }
    void add(Terrain t)
    {
        terrains.add(t);
    }
    private void add(Wrinkle w)
    {
        wrinkle=w;
    }
    private void add(Actor a)
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
    void update() 
    {
        ArrayList<Actor> newActors=new ArrayList<Actor>();
        for(SpawnActor i:spawns)
        {
            i.update();
        }
        for(Actor i:actors)
        {
            if(!i.isDead())
            {
                i.update(this);
                newActors.add(i);
            }
        }
        
        actors=newActors;
        System.out.println(actors.size());
    }
    Wrinkle getWrinkle() throws NoWrinkleException
    {
        if(wrinkle==null)
        {
            throw new NoWrinkleException();
        }
        return wrinkle;
    }
}
