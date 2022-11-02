package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.DatabaseConnection;
import ku.cs.services.EmployeeDataSource;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoginController {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    private EmployeeList employeeList;
    private Employee employee;

//    private DatabaseConnection databaseConnection;

    private DataSource<EmployeeList> dataSource;


    private ArrayList<String> passItem;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    @FXML private Label warningUsernameLabel;
    @FXML private Label warningLabel;



    @FXML
    public void initialize() {

        dataSource = new EmployeeDataSource();
        employeeList = dataSource.readData();


//        sample = new Sample("test1", 1);
//        databaseConnection = new DatabaseConnection();
//        Connection connectDB = databaseConnection.getConnection();
//        String connectQuery = "SELECT * FROM manage_prawn";
//
//        try{
//            Statement statement = connectDB.createStatement();
//            ResultSet queryOutput = statement.executeQuery(connectQuery);
//
//            while (queryOutput != null && queryOutput.next()){
//                String careID = queryOutput.getString("D_ID");
//                String giveFoodStatus = queryOutput.getString("DF_STATUS");
//                String givePillsStatus = queryOutput.getString("DP_STATUS");
//                String measureWeight = queryOutput.getString("P_MEASUREWEIGHT");
//                String prawnID = queryOutput.getString("P_ID");
//                String pondID = queryOutput.getString("W_NO");
//                // ทดสอบ print ลง cmdline
//                System.out.println(careID + "\t\t" + giveFoodStatus
//                        + "\t\t" + givePillsStatus
//                        + "\t\t" + measureWeight
//                        + "\t\t" + prawnID
//                        + "\t\t" + pondID);
//                // อันเก่า
//                showSampleView(careID);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    public void loginButton(ActionEvent event) {

        if (employeeList.isCorrectPair(usernameField.getText(), passwordField.getText() )) {

            try {
                System.out.println(ANSI_GREEN +  "Log in finished !" + ANSI_RESET);




//                com.github.saacsos.FXRouter.goTo("main");

//                setPassItem("main"); // will do later
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        else if (usernameField.getText().equals("") && passwordField.getText().equals("")){
            warningLabel.setText("Please Enter Username and Password");
            warningLabel.setTextFill(Color.web("#FFA500", 1));
            warningUsernameLabel.setText("");
        }
        else if (usernameField.getText().equals("")){
            warningUsernameLabel.setText("Please Enter Username");
            warningUsernameLabel.setTextFill(Color.web("#FFA500", 1));
            warningLabel.setText("");



        }
        else if (passwordField.getText().equals("")){
            warningLabel.setText("Please Enter Password");
            warningLabel.setTextFill(Color.web("#FFA500", 1));
            warningUsernameLabel.setText("");

        }


        else {
            warningLabel.setText("username หรือ password ไม่ถูกต้อง");
            warningLabel.setTextFill(Color.web("#ff0000", 1));
            warningUsernameLabel.setText("");
            System.err.println("username หรือ password ไม่ถูกต้อง");
            System.err.println("โปรดตรวจสอบ username และ password ");
        }
    }
    private void setPassItem(String location) throws IOException {
        passItem.add(usernameField.getText());
        com.github.saacsos.FXRouter.goTo(location,passItem);
    }









}
