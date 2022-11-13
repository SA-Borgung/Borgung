package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import ku.cs.models.*;
import ku.cs.services.*;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerPrepareBorgungController {

    @FXML
    private ListView<String> addEmployeeListView;
    @FXML
    private ListView<String> addPondListView;

    @FXML
    private Label employeeLabel;

    @FXML
    private Label pondIdLabel;
    @FXML
    private Label statusLabel;

    @FXML
    Label finishLB;

    @FXML
    private TextArea additionInfoTA;


    private ObservableList<String> ObservableList;

    private PondList pondList;

    private EmployeeList employeeList;

    private DataSource<PondList> dataSource;
    private DataSource<EmployeeList> employeeDataSource;

    @FXML
    public void initialize() {

        dataSource = new PondDataSource();
        pondList = dataSource.readData();

        employeeDataSource = new EmployeeDataSource();
        employeeList = employeeDataSource.readData();


        showEmployeeListView();
        clearSelectedRow();
        handleSelectedEmployeeListView();

        showPondListView();
        handleSelectedPondListView();

    }

    @FXML
    private void clickFinishedButton() {
        //String id, String name, String phoneNumber, String address


//        Customer customer = new Customer(idCardTextField.getText(),nameTextField.getText(),phoneTextField.getText(),idCardTextField.getText());
//
//        customer.insertToSql();

        finishLB.setText("อัพเดทข้อมูลสำเร็จ");
        finishLB.setTextFill(Color.web("#ff0000", 1));
//        if (!checkBox && failedReason.getText().equals("")) {
//            System.out.println("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
//        } else {
//            System.out.println("ไปหน้าต่อไป");
//        }

    }

    private void showEmployeeListView() {
        ListView<String> listView = new ListView<>();

        ObservableList = FXCollections.observableArrayList();
        ArrayList<Employee> tempVendorList = new ArrayList<Employee>();
        for (int i = employeeList.count()-1; i>=0; i--){
            Employee employee = employeeList.getEmployeeNumber(i);
            tempVendorList.add(employee);
            ObservableList.add(employee.getId());

        }
        addEmployeeListView.setItems(ObservableList);
    }

    private void showPondListView() {
        ListView<String> listView = new ListView<>();

        ObservableList = FXCollections.observableArrayList();
        ArrayList<Pond> tempVendorList = new ArrayList<Pond>();
        for (int i = pondList.count()-1; i>=0; i--){
            Pond pond = pondList.getPondNumber(i);
            tempVendorList.add(pond);
            ObservableList.add(pond.getId());

        }
        addPondListView.setItems(ObservableList);
    }



    private void handleSelectedEmployeeListView() {
        addEmployeeListView.getSelectionModel().selectedItemProperty().addListener(
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

    private void handleSelectedPondListView() {
        addPondListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        Pond selectedPond = pondList.getPondById(newValue);
                        System.out.println(selectedPond + " is selected");
                        showSelectedPond(selectedPond);
                        selectedPond();
                    }
                });
    }

    private Employee selectedEmployee(){
        String selectedEmployeeString = addEmployeeListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        Employee employee = employeeList.getEmployeeById(selectedEmployeeString);
        return employee;
    }

    private Pond selectedPond(){
        String selectedPondString = addPondListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        Pond pond = pondList.getPondById(selectedPondString);
        return pond;
    }

//    @FXML
//    private void handleSetWaitStatus(ActionEvent actionEvent){
//        String addStatusString = "รอดำเนินการ";
//        selectedVendorOrder().setStatus(addStatusString);
//        showSelectedVendor(selectedVendorOrder());
//        System.out.println(addStatusString);
//
//    }
//
//    @FXML
//    private void handleSetFinishStatus(ActionEvent actionEvent){
//        String addStatusString = "เสร็จสิ้น";
//        selectedVendorOrder().setStatus(addStatusString);
//        showSelectedVendor(selectedVendorOrder());
//        System.out.println(addStatusString);
//
//    }

    private void clearSelectedRow() {
        employeeLabel.setText("");
        pondIdLabel.setText("");



    }

    private void showSelectedEmployee(Employee employee) {
        employeeLabel.setText(employee.getName());

    }

    private void showSelectedPond(Pond pond) {
        pondIdLabel.setText(pond.getId());
        statusLabel.setText(pond.getStatus()+""); //String.valueOf(pond.getStatus())

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
