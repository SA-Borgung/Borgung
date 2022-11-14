package ku.cs.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.beans.binding.MapExpression;
import ku.cs.models.ManagePrawn;
import ku.cs.models.ManagePrawnList;
import ku.cs.services.DatabaseConnection;

public class ManagePrawnDataSource implements DataSource<ManagePrawnList> {

    private DatabaseConnection databaseConnection;

    public ManagePrawnDataSource() {}

    public ManagePrawnDataSource(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public ManagePrawnList readData() {
        ManagePrawnList list = new ManagePrawnList();

        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();
        String connectQuery = "SELECT * FROM manage_prawn";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String careID = queryOutput.getString("D_ID");
                String giveFoodStatus = queryOutput.getString("DF_STATUS");
                String givePillsStatus = queryOutput.getString("DP_STATUS");
                String manageStatus = queryOutput.getString("D_MANANGESTATUS");
                String measureWeight = queryOutput.getString("P_MEASUREWEIGHT");
                String date = queryOutput.getString("D_DATE");
                String farmingID = queryOutput.getString("F_ID");

                list.addManagePrawn(
                        new ManagePrawn(
                                careID,
                                Boolean.parseBoolean(giveFoodStatus),
                                Boolean.parseBoolean(givePillsStatus),
                                manageStatus,
                                measureWeight, ///ต้องเป็น double
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
    public void insertData(ManagePrawnList managePrawnList) {

    }
}
