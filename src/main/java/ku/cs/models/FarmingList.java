package ku.cs.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public Farming getFarmingById(String id){
        for (Farming farming : farmings) {
            if (farming.checkId(id)) {
                return farming;
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

    public boolean addFarmingInputCheck(String round){
        try
        {
            Integer.parseInt(round);
            System.out.println(round + " is a valid integer");
        }
        catch (NumberFormatException e)
        {
            System.out.println(round + " is not a valid integer");
            return false;
        }
        return true;
    }
}
