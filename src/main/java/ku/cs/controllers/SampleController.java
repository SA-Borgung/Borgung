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
        String connectQuery = "select * from เลี้ยงกุ้ง";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()){
                showSampleView(queryOutput.getString("รหัสพันธุ์กุ้ง"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void showSampleView(String string){
        nameLabel.setText(string);
    }
}
