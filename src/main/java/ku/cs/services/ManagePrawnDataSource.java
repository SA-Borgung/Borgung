package ku.cs.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ku.cs.models.ManagePrawn;
import ku.cs.models.ManagePrawnList;

public class ManagePrawnDataSource implements DataSource<ManagePrawnList> {

    private DatabaseConnection databaseConnection;

    public ManagePrawnDataSource() {}

    public ManagePrawnDataSource(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public ManagePrawnList managerReadData() {
        ManagePrawnList list = new ManagePrawnList();

        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getManagerConnection();
        String connectQuery = "SELECT * FROM manage_prawn";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String careID = queryOutput.getString("D_ID");
                String manageType = queryOutput.getString("D_TYPE");
                String manageNote = queryOutput.getString("D_NOTE");
                String date = queryOutput.getString("D_DATE");
                String farmingID = queryOutput.getString("F_ID");

                list.addManagePrawn(
                        new ManagePrawn(
                                careID,
                                manageType,
                                manageNote,
                                date,
                                farmingID
                        )
                );

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ManagePrawnList staffReadData() {
        ManagePrawnList list = new ManagePrawnList();

        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getStaffConnection();
        String connectQuery = "SELECT * FROM manage_prawn";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String careID = queryOutput.getString("D_ID");
                String manageType = queryOutput.getString("D_TYPE");
                String manageNote = queryOutput.getString("D_NOTE");
                String date = queryOutput.getString("D_DATE");
                String farmingID = queryOutput.getString("F_ID");

                list.addManagePrawn(
                        new ManagePrawn(
                                careID,
                                manageType,
                                manageNote,
                                date,
                                farmingID
                        )
                );

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
