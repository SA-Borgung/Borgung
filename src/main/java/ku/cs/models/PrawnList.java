package ku.cs.models;

import java.util.ArrayList;
import java.util.List;

public class PrawnList {

    private ArrayList<Prawn> prawns;

    public PrawnList() {
        prawns = new ArrayList<>();
    }

    public void addPrawn(Prawn prawn){prawns.add(prawn);}

    public ArrayList<Prawn> getPrawns(){return prawns;}

    public int count() {return prawns.size();}

    public ArrayList<String> specialList = new ArrayList<>();

    public Prawn getPrawnNumber(int num){
        Prawn prawn = prawns.get(num);
        return prawn;
    }

    public List<String> getAllSpecies(){
        for (Prawn prawn : prawns){



            specialList.add(prawn.getSpecies());


        }
        return specialList;
    }
    public Prawn getPrawnById(String id){
        for (Prawn prawn : prawns) {
            if (prawn.checkId(id)) {
                return prawn;
            }
        }
        return null;
    }






}
