package ku.cs.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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

    public ManagePrawn getManagePrawnById (String id) {
        for (ManagePrawn managePrawn: managePrawns) {
            if (managePrawn.checkPrawnId(id)) {
                return managePrawn;
            }
        }
        return null;
    }

    public static boolean validateJavaDate(String strDate)
    {
        if (strDate.trim().equals(""))
        {
            return false;
        }
        else
        {

            SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
            sdfrmt.setLenient(false);

            try
            {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate+" is valid date format");
            }
            catch (ParseException e)
            {
                System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            return true;
        }
    }

    public ManagePrawn latestDate(String farmingID){
        ArrayList<String> date = new ArrayList<>();
        ArrayList<ManagePrawn> getManagePrawnByDate = new ArrayList<>();
        for (ManagePrawn managePrawn : managePrawns) {
            if (managePrawn.getFarmingID().equals(farmingID)){
                date.add(managePrawn.getDate());
            }
        }
        String maxDate = Collections.max(date);
        for (ManagePrawn managePrawn : managePrawns){
            if (managePrawn.getFarmingID().equals(farmingID)){
                if (managePrawn.getDate().equals(maxDate)){
                    getManagePrawnByDate.add(managePrawn);
                }
            }
        }
        ManagePrawn getManagePrawn = getManagePrawnByDate.get(getManagePrawnByDate.size());
        return getManagePrawn;
    }
}
