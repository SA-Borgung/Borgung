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
    private Label warningLabel;
    @FXML
    private Label prawnTypeLabel;

    @FXML private TableView<Prawn> prawnTableView;

    private ObservableList<Employee> ObservableList;
    private ObservableList<Prawn> ObservableList2;
    private DataSource<VendorOrderList> vendorDataSource;
    private VendorOrder vendorOrder;
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



        prawnTypeLabel.setText("");

        showPrawnData();
        clearSelectedRow();

        prawnTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                clearSelectedRow();
                showSelectedPrawnType(newValue);
            }
        });


    }

    private void showPrawnData() {
        prawnTableView.getItems().clear();
        prawnTableView.getColumns().clear();
        ObservableList2 = FXCollections.observableArrayList(prawnList.getPrawns());
        prawnTableView.setItems(ObservableList2);
        ///แสดงแถวแนวตรง
        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:species", "field:species"));
//        configs.add(new StringConfiguration("title:หมายเลข", "field:pondID"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            prawnTableView.getColumns().add(col);
        }
    }



    @FXML
    private void clickFinishedButton() {
        try{
            if (prawnAmountField.getText().isEmpty() || shopNameField.getText().isEmpty()) {
                System.out.println("โปรดกรอกข้อมูลให้ครบ");
                warningLabel.setText("กรุณากรอกข้อมูลให้ครบ");
            } else {
                int vendorOrderID = vendorOrderList.count()+1;
                String vendorOrderIDString = "GET"+vendorOrderID;
                int amount = Integer.parseInt(prawnAmountField.getText());
                String sellerName = shopNameField.getText();
                String prawnID = selectedPrawn().getId();

                VendorOrder vendorOrder = new VendorOrder(vendorOrderIDString, amount,sellerName,"รอดำเนินการ",prawnID,null);
                vendorOrder.insertToSql();
                vendorOrderList.addVendorOrder(vendorOrder);
                warningLabel.setText("สั่งกุ้งเสร็จสิ้น");
            }
        }catch (Exception e){
            warningLabel.setText("โปรดใส่เป็นตัวเลข");
        }




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

    private Prawn selectedPrawn(){
        Prawn selectedPrawnString = prawnTableView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
//        Prawn prawn = prawnList.getPrawnById(selectedPrawnString);
        return selectedPrawnString;
    }

    private void clearSelectedRow() {
        prawnTypeLabel.setText("");
        warningLabel.setText("");
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
