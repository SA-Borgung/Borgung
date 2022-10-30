package ku.cs.models;

import java.util.ArrayList;

public class FarmingList {
    private ArrayList<Farming> farmings;

    public FarmingList() {
        farmings = new ArrayList<>();
    }

    public void addFarming(Farming farming){farmings.add(farming);}

    public ArrayList<Farming> getFarmings(){return farmings;}

    public int count() {return farmings.size();}

    public Farming getFarmingNumber(int num){
        Farming farming = farmings.get(num);
        return farming;
    }
}
