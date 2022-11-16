package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerOrderShrimpController {
    @FXML
    TextField prawnAmountField,shopNameField;
    @FXML
    private Label employeeLabel;
    @FXML
    private Label prawnTypeLabel;

    @FXML private TableView<Employee> employeeTableView;
    @FXML private TableView<Prawn> prawnTableView;

    private ObservableList<Employee> ObservableList;
    private ObservableList<Prawn> ObservableList2;
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



        showEmployeeData();
        showPrawnData();

        employeeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedEmployee(newValue);
            }
        });
        prawnTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedPrawnType(newValue);
            }
        });


    }
    private void showEmployeeData() {
        employeeTableView.getItems().clear();
        employeeTableView.getColumns().clear();
        ObservableList = FXCollections.observableArrayList(employeeList.getEmployees());
        employeeTableView.setItems(ObservableList);
        ///แสดงแถวแนวตรง
        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID", "field:id"));
//        configs.add(new StringConfiguration("title:หมายเลข", "field:pondID"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            employeeTableView.getColumns().add(col);
        }
    }
    private void showPrawnData() {
        prawnTableView.getItems().clear();
        prawnTableView.getColumns().clear();
        ObservableList2 = FXCollections.observableArrayList(prawnList.getPrawns());
        prawnTableView.setItems(ObservableList2);
        ///แสดงแถวแนวตรง
        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID", "field:id"));
//        configs.add(new StringConfiguration("title:หมายเลข", "field:pondID"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            prawnTableView.getColumns().add(col);
        }
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




//    private void showEmployeeListView() {
//        ListView<String> listView = new ListView<>();
//        ObservableList = FXCollections.observableArrayList();
//        ArrayList<Employee> tempEmployeeList = new ArrayList<Employee>();
//        for (int i = employeeList.count()-1; i>=0; i--){
//
//            Employee employee = employeeList.getEmployeeNumber(i);
//            tempEmployeeList.add(employee);
//            ObservableList.add(employee.getId());
//
//        }
//        employeeListView.setItems(ObservableList);
//    }

//    private void handleSelectedEmployeeListView() {
//        employeeListView.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observableValue,
//                                        String oldValue, String newValue) {
//                        Employee selectedEmployee = employeeList.getEmployeeById(newValue);
//                        System.out.println(selectedEmployee + " is selected");
//                        showSelectedEmployee(selectedEmployee);
//                        selectedEmployee();
//                    }
//                });
//    }



//    private void showPrawnListView() {
//        ListView<String> listView = new ListView<>();
//
//        ObservableList = FXCollections.observableArrayList();
//        ArrayList<Prawn> tempPrawnList = new ArrayList<Prawn>();
//        for (int i = prawnList.count()-1; i>=0; i--){
//            Prawn prawn = prawnList.getPrawnNumber(i);
//            tempPrawnList.add(prawn);
//            ObservableList2.add(prawn.getId());
//
//        }
//        prawnListView.setItems(ObservableList2);
//    }

//    private void handleSelectedPrawnListView() {
//        prawnListView.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observableValue,
//                                        String oldValue, String newValue) {
//                        Prawn selectedPrawn = prawnList.getPrawnById(newValue);
//                        System.out.println(selectedPrawn + " is selected");
//                        showSelectedPrawnType(selectedPrawn);
//                        selectedPrawn();
//                    }
//                });
//    }

    private Employee selectedEmployee(){
        Employee selectedEmployeeString = employeeTableView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
//        Employee employee = employeeList.getEmployeeById(selectedEmployeeString);
        return selectedEmployeeString;
    }
    private Prawn selectedPrawn(){
        Prawn selectedPrawnString = prawnTableView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
//        Prawn prawn = prawnList.getPrawnById(selectedPrawnString);
        return selectedPrawnString;
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
