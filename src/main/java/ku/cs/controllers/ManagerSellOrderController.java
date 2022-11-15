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

//    @FXML
//    private TextField nameTextField,phoneTextField,idCardTextField;
//    @FXML
//    private TextArea addressTextArea;
//    @FXML
//    private ListView<String> customerListView;
//
//    public static final String ANSI_RESET = "\u001B[0m";
//
//    private ObservableList<String> ObservableList;
//    private CustomerList customerList;
//    private DataSource<CustomerList> customerListDataSource;
//
//    @FXML
//    public void initialize() {
//
//        customerListDataSource = new CustomerDataSource();
//        customerList = customerListDataSource.readData();
//
//        showListView();
//        clearSelectedProduct();
//        handleSelectedListView();
//    }
//
//    private void showListView() {
//        ListView<String> listView = new ListView<>();
//        ObservableList = FXCollections.observableArrayList();
//        ArrayList<Customer> tempCustomerList = new ArrayList<Customer>();
//        for (int i = customerList.count()-1; i>=0; i--){
//            Customer customer = customerList.getCustomerNumber(i);
//            tempCustomerList.add(customer);
//            String showList = customer.getId();
//            ObservableList.add(showList);
//
//        }
//        customerListView.setItems(ObservableList);
//    }
//
//    private void handleSelectedListView() {
//        customerListView.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observableValue,
//                                        String oldValue, String newValue) {
//                        Customer selectedCustomer = customerList.getFarmingById(newValue);
//                        System.out.println(selectedFarming + " is selected");
//                        showSelectedFarming(selectedFarming);
//                        selectedFarming();
//                    }
//                });
//    }
//
//    public Farming selectedFarming(){
//        String selectedFarmingString = farmingListView.getSelectionModel().selectedItemProperty().get();
//        System.out.println(selectedFarmingString);
//        Farming farming = farmingList.getFarmingById(selectedFarmingString);
//        return farming;
//    }
//
//    public void  showSelectedFarming(Farming farming){
//        pondLabel.setText(farming.getPondID());
//        prawnLabel.setText("ไปเรียกข้อมูลซะ");
//        weightLabel.setText("ไปเรียกข้อมูลซะ");
//        amountLabel.setText(Integer.toString(farming.getPrawnAmount()));
//        dateLabel.setText(farming.getGetDate());
//    }
//
//    private void clearSelectedProduct() {
//        pondLabel.setText("");
//        prawnLabel.setText("");
//        weightLabel.setText("");
//        amountLabel.setText("");
//        dateLabel.setText("");
//    }


    @FXML
    private void clickFinishedButton() {
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
