package dungeon.info;

import java.util.*;

public class AllVamps{
    private List<Slot> vamps;
    private int vampCount;

    // Constructor
    public AllVamps(){
        vamps = new ArrayList<Slot>();
        this.vampCount = 0;
    }

    // accessor
    public int getCount(){ return this.vampCount; }
    public List<Slot> getList(){ return this.vamps; }

    // add
    public void add(Slot v){
        this.vamps.add(v);
        this.vampCount++;
    }

    // remove
    public void remove(Slot v){
        this.vamps.remove(v);
        this.vampCount--;
    }

    // contains
    public boolean contains(Slot v){
        return this.vamps.contains(v);
    }

    // prehaps use this instead of using Map to track possible location
    // coordinateOccupied
    public boolean coordinateOccupied(int height, int width, Slot hero){
        boolean found = false;

        // check for occupied vampires
        for(Slot v : this.vamps){
            if(v.getHeight() == height && v.getWidth() == width)
                found = true;
        }

        // check for hero
        if(hero.getHeight() == height && hero.getWidth() == width)
            found = true;

        return found;
    }


}