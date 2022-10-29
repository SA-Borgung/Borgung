package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.Sample;
import ku.cs.services.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SampleController {
    @FXML
    private Label nameLabel;

    private Sample sample;
    private DatabaseConnection databaseConnection;

    @FXML
    public void initialize() {
        sample = new Sample("test1", 1);

        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();
        String connectQuery = "SELECT * FROM care";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String careID = queryOutput.getString("D_ID");
                String giveFoodStatus = queryOutput.getString("DF_STATUS");
                String givePillsStatus = queryOutput.getString("DP_STATUS");
                String measureWeight = queryOutput.getString("P_MEASUREWEIGHT");
                String prawnID = queryOutput.getString("P_ID");
                String pondID = queryOutput.getString("W_NO");
                // ทดสอบ print ลง cmdline
                System.out.println(careID + "\t\t" + giveFoodStatus
                        + "\t\t" + givePillsStatus
                        + "\t\t" + measureWeight
                        + "\t\t" + prawnID
                        + "\t\t" + pondID);
                // อันเก่า
                showSampleView(careID);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSampleView(String string){
        nameLabel.setText(string);
    }
}
