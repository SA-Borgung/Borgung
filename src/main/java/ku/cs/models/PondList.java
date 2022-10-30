package ku.cs.models;

import java.util.ArrayList;

public class PondList {

    private ArrayList<Pond> ponds;

    public PondList() {
        ponds = new ArrayList<>();
    }

    public void addPond(Pond pond){ponds.add(pond);}

    public ArrayList<Pond> getPonds(){return ponds;}

    public int count() {return ponds.size();}

    public Pond getPondNumber(int num){
        Pond pond = ponds.get(num);
        return pond;
    }
}
