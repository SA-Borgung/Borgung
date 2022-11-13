package ku.cs.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PurchaseOrder {

    private String id,weight,species;
    private int age;
    private Connection connection;
    private PreparedStatement pst;
    private String databaseName = "borgung";

    public PurchaseOrder(String id, String weight, String species, int age) {
        this.id = id;
        this.weight = weight;
        this.species = species;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void insertToSql() {
        try {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(url , "root","");
            pst = connection.prepareStatement("Insert into purchase_order(O_ID,P_WEIGHT,P_SPECIES,P_AGE )values(?,?,?,?)");
            pst.setString(1, this.id);
            pst.setString(2, this.weight);
            pst.setString(3, this.species);
            pst.setString(4, this.age+"");

            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //String id, String weight, String species, int age



    public void updateToSql() {
        try {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(url , "root","");
            pst = connection.prepareStatement("UPDATE purchase_order SET P_WEIGHT = ?,P_SPECIES = ?,P_AGE= ?  WHERE O_ID=?");
            pst.setString(1, this.id);
            pst.setString(2, this.weight);
            pst.setString(3, this.species);
            pst.setString(4, this.age+"");

            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
