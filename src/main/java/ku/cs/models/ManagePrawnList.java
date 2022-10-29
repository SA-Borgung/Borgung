package ku.cs.models;

import java.util.ArrayList;

public class ManagePrawnList {

    private ArrayList<ManagePrawn> managePrawns;

    public ManagePrawnList() {
        managePrawns = new ArrayList<>();
    }

    public void addManagePrawn(ManagePrawn managePrawn){managePrawns.add(managePrawn);}

    public ArrayList<ManagePrawn> getAllManagePrawn(){return managePrawns;}

    public int count() {return managePrawns.size();}

    public ManagePrawn getManagePrawnNumber(int num){
        ManagePrawn managePrawn = managePrawns.get(num);
        return managePrawn;
    }
}
