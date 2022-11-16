package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.models.*;
import ku.cs.services.CustomerDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.FarmingDataSource;
import ku.cs.services.ManagePrawnDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerSellOrderController {

    @FXML
    private TextField nameTextField,phoneTextField,idCardTextField;
    @FXML
    private TextArea addressTextArea;

    public static final String ANSI_RESET = "\u001B[0m";

    private ObservableList<String> ObservableList;
    private CustomerList customerList;
    private DataSource<CustomerList> customerListDataSource;

    private ArrayList<String> passItem;

    @FXML
    public void initialize() {

        customerListDataSource = new CustomerDataSource();
        customerList = customerListDataSource.readData();
        passItem = new ArrayList<>();
    }

    private void enterCustomerDetail(){
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String id = idCardTextField.getText();
        String address = addressTextArea.getText();
        String purchaseType = "test";
        passItem.add(name);
        passItem.add(phone);
        passItem.add(id);
        passItem.add(address);
        passItem.add(purchaseType);

    }


//    public Farming selectedFarming(){
//        String selectedFarmingString = farmingListView.getSelectionModel().selectedItemProperty().get();
//        System.out.println(selectedFarmingString);
//        Farming farming = farmingList.getFarmingById(selectedFarmingString);
//        return farming;
//    }

    public void  showSelectedFarming(Farming farming){

    }

    private void clearSelectedProduct() {
    }

    private void setPassItem(String location) throws IOException {

        com.github.saacsos.FXRouter.goTo(location,passItem);
    }

    @FXML
    public void checkStockButton(ActionEvent actionEvent) throws IOException {
        enterCustomerDetail();
        setPassItem("managerCheckStock");
        try {
            com.github.saacsos.FXRouter.goTo("managerCheckStock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า managerCheckStock ไม่ได้");
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
