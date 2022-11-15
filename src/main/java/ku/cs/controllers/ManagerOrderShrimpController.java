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
import ku.cs.services.VendorOrderDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerOrderShrimpController {
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

    private javafx.collections.ObservableList<String> ObservableList;
    private DataSource<VendorOrderList> vendorDataSource;
    private VendorOrderList vendorOrderList;
    private DataSource<EmployeeList> employeeDataSource;
    private EmployeeList employeeList;
    private DataSource<PrawnList> prawnDataSource;
    private PrawnList prawnList;

    @FXML
    public void initialize() {
        employeeDataSource = new EmployeeDataSource();
        employeeList = employeeDataSource.readData();

        prawnDataSource = new PrawnDataSource();
        prawnList = prawnDataSource.readData();

        vendorDataSource = new VendorOrderDataSource();
        vendorOrderList = vendorDataSource.readData();

        showEmployeeListView();
        clearSelectedRow();
        handleSelectedEmployeeListView();

        showPrawnListView();
        handleSelectedPrawnListView();



//        handleSelectedListView();



    }
    @FXML
    private void clickFinishedButton() {
        int vendorOrderID = vendorOrderList.count()+1;
        String vendorOrderIDString = "GET"+vendorOrderID;
        int amount = Integer.parseInt(prawnAmountField.getText());
        String sellerName = shopNameField.getText();
        String prawnID = selectedPrawn().getId();
        String employeeID = selectedEmployee().getId();

        VendorOrder vendorOrder = new VendorOrder(vendorOrderIDString, amount,sellerName,"รอดำเนินการ",prawnID,employeeID);
        vendorOrder.insertToSql();

//        finishLB.setText("กรอกข้อมูลสำเร็จ ");
//        finishLB.setTextFill(Color.web("#ff0000", 1));

    }




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

    }

    private void showSelectedPrawnType(Prawn prawn) {

        prawnTypeLabel.setText(prawn.getSpecies());
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
