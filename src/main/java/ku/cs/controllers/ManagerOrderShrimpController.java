package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.EmployeeDataSource;
import ku.cs.services.PrawnDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerOrderShrimpController {

    private VendorOrderList vendorOrderList;
    private EmployeeList employeeList;
    private VendorOrder vendorOrder;

    private javafx.collections.ObservableList<String> ObservableList;
    private DataSource<VendorOrderList> vendorDataSource;
    private DataSource<EmployeeList> employeeDataSource;

    private PrawnList prawnList;
    private DataSource<PrawnList> prawnDataSource;

    @FXML
    ChoiceBox<String> employeeCB = new ChoiceBox<>();
    ChoiceBox<String> prawnTypeCB = new ChoiceBox<>();


    @FXML
    TextField prawnAmountField,shopNameField;

    @FXML
    private ListView<String> employeeListView;
    @FXML
    private ListView<String> prawnListView;

    @FXML
    private Label employeeLabel;
    @FXML
    private Label prawnTypeLabel;

    @FXML
    Label finishLB;

    public static final String ANSI_RESET = "\u001B[0m";



    @FXML
    public void initialize() {



        employeeDataSource = new EmployeeDataSource();
        employeeList = employeeDataSource.readData();

        prawnDataSource = new PrawnDataSource();
        prawnList = prawnDataSource.readData();

        showEmployeeListView();
        clearSelectedRow();
        handleSelectedEmployeeListView();

        showPrawnListView();
        handleSelectedPrawnListView();



//        handleSelectedListView();



    }
    @FXML
    private void clickFinishedButton() {
        String selectedEmployeeString = employeeListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        Employee employee = employeeList.getEmployeeById(selectedEmployeeString);

        String selectedPrawnString = prawnListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        Prawn prawn = prawnList.getPrawnById(selectedPrawnString);

        VendorOrder vendorOrder = new VendorOrder(" ",Integer.parseInt(prawnAmountField.getText()) ,shopNameField.getText(),"",prawn.getId(),employee.getId());
        // String id, int amount, String sellerName, String status,String orderType, String employeeID
        vendorOrder.insertToSql();

        finishLB.setText("กรอกข้อมูลสำเร็จ ");
        finishLB.setTextFill(Color.web("#ff0000", 1));
//        if (!checkBox && failedReason.getText().equals("")) {
//            System.out.println("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
//        } else {
//            System.out.println("ไปหน้าต่อไป");
//        }
    }

//    @FXML
//    private void clickUpdatedButton() {
//        QC qc = new QC("QC0012", "2020-11-01", "ผ่าน", "Yes", "EP0022", "W0013");
//        qc.updateToSql();
//        if (!checkBox && failedReason.getText().equals("")) {
//            System.out.println("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
//        } else {
//            System.out.println("ไปหน้าต่อไป");
//        }
//    }


    private void showEmployeeListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<Employee> tempEmployeeList = new ArrayList<Employee>();
        for (int i = employeeList.count()-1; i>=0; i--){

            Employee employee = employeeList.getEmployeeNumber(i);
            tempEmployeeList.add(employee);
            ObservableList.add(employee.getId());

        }
        employeeListView.setItems(ObservableList);
    }

    private void handleSelectedEmployeeListView() {
        employeeListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        Employee selectedEmployee = employeeList.getEmployeeById(newValue);
                        System.out.println(selectedEmployee + " is selected");
                        showSelectedEmployee(selectedEmployee);
                        selectedEmployee();
                    }
                });
    }



    private void showPrawnListView() {
        ListView<String> listView = new ListView<>();

        ObservableList = FXCollections.observableArrayList();
        ArrayList<Prawn> tempPrawnList = new ArrayList<Prawn>();
        for (int i = prawnList.count()-1; i>=0; i--){
            Prawn prawn = prawnList.getPrawnNumber(i);
            tempPrawnList.add(prawn);
            ObservableList.add(prawn.getId());

        }
        prawnListView.setItems(ObservableList);
    }

    private void handleSelectedPrawnListView() {
        prawnListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        Prawn selectedPrawn = prawnList.getPrawnById(newValue);
                        System.out.println(selectedPrawn + " is selected");
                        showSelectedPrawnType(selectedPrawn);
                        selectedPrawn();
                    }
                });
    }

    private Employee selectedEmployee(){
        String selectedEmployeeString = employeeListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        Employee employee = employeeList.getEmployeeById(selectedEmployeeString);
        return employee;
    }
    private Prawn selectedPrawn(){
        String selectedPrawnString = prawnListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        Prawn prawn = prawnList.getPrawnById(selectedPrawnString);
        return prawn;
    }







    private void clearSelectedRow() {
        employeeLabel.setText("");
        prawnTypeLabel.setText("");


    }

    private void showSelectedEmployee(Employee employee) {
        employeeLabel.setText(employee.getName());


//        StatusLabel.setText(vendorOrder.getStatus());
    }

    private void showSelectedPrawnType(Prawn prawn) {

        prawnTypeLabel.setText(prawn.getSpecies());

//        StatusLabel.setText(vendorOrder.getStatus());
    }




    public void saveBtn(ActionEvent event){

//        String employee = employeeCB.getValue();
//        String prawnType = prawnTypeCB.getValue();
//
//
//        String prawnAmount =  prawnAmountField.getText().trim().;
//        String shopName = shopNameField.getText().trim();
//
//        VendorOrder order = new VendorOrder(employee, Integer.parseInt(prawnAmount), prawnAmount, shopName);
//
//
//
//        vendorOrderList.addVendorOrder(order);

//        VendorOrderDataSource.insertData(); //insert data



        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void backBtn(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("managerHome");
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.err.println("ไม่สามารถเข้าหน้า managerHome");
        }
    }











}
