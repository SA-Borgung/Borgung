package ku.cs.services;

import ku.cs.models.Prawn;
import ku.cs.models.PrawnList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrawnDataSource implements DataSource<PrawnList> {

    private DatabaseConnection databaseConnection;

    public PrawnDataSource() {}

    public PrawnDataSource(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public PrawnList managerReadData() {
        PrawnList list = new PrawnList();

        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getManagerConnection();
        String connectQuery = "SELECT * FROM prawn";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String id = queryOutput.getString("P_ID");
                String species = queryOutput.getString("P_SPECIES");
                String detail = queryOutput.getString("P_DETAILS");


                list.addPrawn(
                        new Prawn(
                                id,
                                species,
                                detail

                        )
                );

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public PrawnList staffReadData() {
        PrawnList list = new PrawnList();

        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getStaffConnection();
        String connectQuery = "SELECT * FROM prawn";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String id = queryOutput.getString("P_ID");
                String species = queryOutput.getString("P_SPECIES");
                String detail = queryOutput.getString("P_DETAILS");


                list.addPrawn(
                        new Prawn(
                                id,
                                species,
                                detail

                        )
                );

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
