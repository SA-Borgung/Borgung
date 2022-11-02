package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.ManagePrawn;
import ku.cs.models.ManagePrawnList;
import ku.cs.models.Sample;
import ku.cs.services.DataSource;
import ku.cs.services.DatabaseConnection;
import ku.cs.services.ManagePrawnDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SampleController {
    @FXML
    private Label nameLabel;

    private Sample sample;
    private DatabaseConnection databaseConnection;
    private ManagePrawnList managePrawnList;
    private ManagePrawn managePrawn;
    private DataSource<ManagePrawnList> dataSource;

    @FXML
    public void initialize() {
//
//        dataSource = new ManagePrawnDataSource();
//        managePrawnList = dataSource.readData();
//        showArrayListView();


//        sample = new Sample("test1", 1);
        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();
        String connectQuery = "SELECT * FROM vender_order";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String id = queryOutput.getString("R_ID");
                String amount = queryOutput.getString("R_AMOUNT");
                String sellerName = queryOutput.getString("R_SELLER");
                String status = queryOutput.getString("R_STATUS");
                String employeeID = queryOutput.getString("E_ID");
                // ทดสอบ print ลง cmdline
                System.out.println(id + "\t\t" + amount
                        + "\t\t" + sellerName
                        + "\t\t" + status
                        + "\t\t" + employeeID
                        );
                // อันเก่า
                //showSampleView(careID);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSampleView(String string){
        nameLabel.setText(string);
    }

    private void showArrayListView(){
        for (int i = managePrawnList.count()-1; i>=0; i--){
            ManagePrawn managePrawn = managePrawnList.getManagePrawnNumber(i);
            System.out.println(managePrawn.getId() + managePrawn.getGiveFoodStatus() + managePrawn.getMeasureWeight());
        }

    }
}
