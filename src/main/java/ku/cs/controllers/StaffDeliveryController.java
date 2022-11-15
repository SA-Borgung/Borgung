package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class StaffDeliveryController {
    @FXML
    private ListView<String> purchaseOrderListView;
    @FXML
    private Label orderLabel;
    @FXML
    private Label purchaseTypeLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label customerLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label locationLabel;

    private ObservableList<String> ObservableList;
    private PurchaseOrderList purchaseOrderList;
    private DataSource<PurchaseOrderList> purchaseOrderListDataSource;
    private CustomerList customerList;
    private DataSource<CustomerList> customerListDataSource;

    @FXML
    public void initialize() {
        purchaseOrderListDataSource = new PurchaseOrderDataSource();
        purchaseOrderList = purchaseOrderListDataSource.readData();
        customerListDataSource = new CustomerDataSource();
        customerList = customerListDataSource.readData();

        showListView();
        clearSelectedProduct();
        handleSelectedListView();

    }

    private void showListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<PurchaseOrder> tempVendorList = new ArrayList<PurchaseOrder>();
        for (int i = purchaseOrderList.count()-1; i>=0; i--){
            PurchaseOrder purchaseOrder = purchaseOrderList.getPurchaseOrderNumber(i);
            if (!purchaseOrder.getStatus().equals("จ่ายเงินแล้ว")){
                tempVendorList.add(purchaseOrder);
                ObservableList.add(purchaseOrder.getId());
            }


        }
        purchaseOrderListView.setItems(ObservableList);
    }

    private void handleSelectedListView() {
        purchaseOrderListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        PurchaseOrder selectedPurchaseOrder = purchaseOrderList.getPurchaseOrderById(newValue);
                        System.out.println(selectedPurchaseOrder + " is selected");
                        showSelectedPurchaseOrder(selectedPurchaseOrder);
                        selectedPurchaseOrder();
                    }
                });
    }



    private PurchaseOrder selectedPurchaseOrder(){
        String selectedPurchaseOrderString = purchaseOrderListView.getSelectionModel().selectedItemProperty().get();
        System.out.println(selectedPurchaseOrderString);
        PurchaseOrder purchaseOrder = purchaseOrderList.getPurchaseOrderById(selectedPurchaseOrderString);
        return purchaseOrder;
    }

    private void showSelectedPurchaseOrder(PurchaseOrder purchaseOrder) {
        orderLabel.setText(purchaseOrder.getId());
        purchaseTypeLabel.setText(purchaseOrder.getPurchaseType());
        priceLabel.setText(Integer.toString(purchaseOrder.getPrice()));
        Customer customer = customerList.getCustomerById(purchaseOrder.getCustomerID());
        customerLabel.setText(customer.getName());
        phoneLabel.setText(customer.getPhoneNumber());
        locationLabel.setText(customer.getAddress());
    }

    private void clearSelectedProduct() {
        orderLabel.setText("");
        purchaseTypeLabel.setText("");
        priceLabel.setText("");
        customerLabel.setText("");
        phoneLabel.setText("");
        locationLabel.setText("");
    }

    @FXML
    private void enterButton(ActionEvent actionEvent){
        try{
//            int purchaseOrderId = purchaseOrderList.count()+1;
//            String purchaseOrderIdString = "O"+ purchaseOrderId;
//            String purchaseType = selectedPurchaseOrder().getPurchaseType();
//            int price = selectedPurchaseOrder().getPrice();
//            String status = selectedPurchaseOrder().getStatus();
//            String customerID = selectedPurchaseOrder().getCustomerID();
//            PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderIdString,purchaseType,price, status, customerID);

            selectedPurchaseOrder().setStatus("จ่ายเงินแล้ว");
            selectedPurchaseOrder().updateToSql();
            System.out.println("กดติดแล้ว");
        }catch (Exception e) {
            System.err.println("ใส่ข้อมูลผิดพลาด");
        }

    }

    @FXML
    public void BackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffGetShrimp");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

}
