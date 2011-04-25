/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wrinkle;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import javax.media.*;

/**
 *
 * @author alex
 */
final public class BGMHandler {
    private String current;

    private HashMap<String,Integer> map;
    private ArrayList<Player> bgm;
    private int steps;
    private int step;
    private GainControl fadein;
    private GainControl fadeout;
    private Player base;
    
    private float gainDelIn;
    private float gainLevelIn;

    private float gainDelOut;
    private float gainLevelOut;
    
    private boolean transitioning=false;

    private Time startTime;

    BGMHandler(ArrayList<String> strings)
    {
        this(strings,2000);
    }
    
    BGMHandler(ArrayList<String> strings, int crossfade)
    {
       steps=crossfade/Global.timeStep;
       map=new HashMap<String,Integer>();
       bgm=new ArrayList<Player>();
       String dir = strings.get(0);
       try
           {
       File file=new File(dir+strings.get(1)+".wav");
       base=Manager.createRealizedPlayer(file.toURI().toURL());
       base.prefetch();

       for(int i=2;i<strings.size();++i)
       {
           file=new File(dir+strings.get(i)+".wav");
           Player player=Manager.createRealizedPlayer(file.toURI().toURL());
           player.prefetch();
           map.put(strings.get(i),i-2);
           bgm.add(player);
       }
          
        }
        catch(Exception e){
               e.printStackTrace();
           }
       
       base.getGainControl().setLevel(1);
       startTime=base.getMediaTime();
       for(Player i:bgm)
       {
           i.getGainControl().setLevel(0);
       }
       base.getGainControl().setLevel(1);
       playAll();
       current="normal";
       step=0;
    }
    void playAll()
    {
        for(Player i:bgm)
        {
            i.setMediaTime(startTime);
            i.start();
        }
        base.start();
    }
    void change(String str)
    {
        if(str.equals(current))
        {
            return;
        }
        System.out.println(current);
        transitioning=true;
        if(!current.equals("normal"))
        {
        fadeout=bgm.get(map.get(current)).getGainControl();
        gainLevelOut=fadeout.getLevel();
        gainDelOut=gainLevelOut/steps;
        step=0;
        }
        current=str;

        if(str.equals("normal"))
        {
            return;
        }
        fadein=bgm.get(map.get(current)).getGainControl();
        gainLevelIn=fadein.getLevel();
        gainDelIn=1.0f/steps;
        
    }
    void unSet()
    {
        change("normal");
    }
    void step()
    {
        if(base.getState()==Player.Prefetched)
        {
            for(Player i:bgm)
            {
                i.setMediaTime(startTime);
            }
            base.setMediaTime(startTime);
            playAll();
        }
        if(transitioning)
        {
            transition();
        }

    }
    void transition()
    {
        
        if(step==steps)
        {
            transitioning=false;
            gainDelOut=0;
            gainLevelOut=0;
            gainDelIn=0;
            gainLevelIn=1;
        }
       fadeOut();
       fadeIn();
       if(step==steps)
       {
           fadeout=null;
           fadein=null;
       }
       step+=1;
    }
    void fadeOut()
    {
        if(fadeout!=null)
        {
        gainLevelOut-=gainDelOut;
        fadeout.setLevel(gainLevelOut);
        }
    }
    void fadeIn()
    {
        if(fadein!=null)
        {
        gainLevelIn+=gainDelIn;
        fadein.setLevel(gainLevelIn);
        }
    }
    void closeAll()
    {
        for(Player i:bgm)
        {
            i.close();
        }
        base.close();
    }
}
