package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerCreatePurchaseOrderController {

    @FXML private Label prawnLabel;
    @FXML private Label amountLabel;
    @FXML private Label priceLabel;
    @FXML private Label purchaseTypeLabel;
    @FXML private Label customerLabel;
    @FXML private Label phoneLabel;

    private PurchaseOrderList purchaseOrderList;
    private DataSource<PurchaseOrderList> purchaseOrderListDataSource;
    private CustomerList customerList;
    private DataSource<CustomerList> customerListDataSource;
    private FarmingList farmingList;
    private DataSource<FarmingList> farmingListDataSource;
    private DataSource<PrawnList> prawnDataSource;
    private PrawnList prawnList;
    private PondList pondList ;
    private DataSource<PondList> pondListDataSource;

    private ArrayList<String> getItem;

    @FXML
    public void initialize() {

        getItem = (ArrayList<String>) com.github.saacsos.FXRouter.getData();
        String name  = getItem.get(0);
        String phone  = getItem.get(1);
        String id  = getItem.get(2);
        String address = getItem.get(3);
        String purchaseType = getItem.get(4);
        String farmingID  = getItem.get(5);
        String price  = getItem.get(6);
        String sellDate  = getItem.get(7);

        purchaseOrderListDataSource = new PurchaseOrderDataSource();
        purchaseOrderList = purchaseOrderListDataSource.readData();
        customerListDataSource = new CustomerDataSource();
        customerList = customerListDataSource.readData();
        farmingListDataSource = new FarmingDataSource();
        farmingList = farmingListDataSource.readData();prawnDataSource = new PrawnDataSource();
        prawnList = prawnDataSource.readData();
        pondListDataSource = new PondDataSource();
        pondList = pondListDataSource.readData();

        showData();

    }

    private void showData() {
        String name  = getItem.get(0);
        String phone  = getItem.get(1);
        String id  = getItem.get(2);
        String address = getItem.get(3);
        String purchaseType = getItem.get(4);
        String farmingID  = getItem.get(5);
        String price  = getItem.get(6);

        Farming farming = farmingList.getFarmingById(farmingID);
        String prawnString = farming.getPrawnID();
        Prawn prawn = prawnList.getPrawnById(prawnString);

        prawnLabel.setText(prawn.getSpecies());
        amountLabel.setText(Integer.toString(farming.getPrawnAmount()));
        priceLabel.setText(price);
        purchaseTypeLabel.setText(purchaseType);
        customerLabel.setText(name);
        phoneLabel.setText(phone);
    }

    private void handleSql(){

        int customerID = customerList.count()+1;
        String customerIDString = "C" + customerID;
        String name  = getItem.get(0);
        String phone  = getItem.get(1);
        String address = getItem.get(3);
        String farmingID  = getItem.get(5);

        Customer customer = new Customer(customerIDString, name, phone, address);
        if (customerList.getCustomerByName(customer.getName()) == null){
            customer.insertToSql();
        }

        Farming farming = farmingList.getFarmingById(farmingID);
        String sellDate  = getItem.get(7);
        farming.setSellDate(sellDate);
        farming.setFarmingStatus("ขายแล้ว");
        farming.updateToSql();

        int orderId = purchaseOrderList.count()+1;
        String orderIDString = "OR"+ orderId;
        String purchaseType = getItem.get(4);
        String priceString  = getItem.get(6);
        int price = Integer.parseInt(priceString);

        PurchaseOrder purchaseOrder = new PurchaseOrder(orderIDString, purchaseType, price, "ยังไม่ส่ง", customer.getId(), farmingID);
        purchaseOrder.insertToSql();

        Pond pond = pondList.getPondById(farming.getPondID());
        pond.setStatus("ยังไม่ดำเนินการ");
        pond.updateToSql();



    }

    @FXML
    public void enterButton(ActionEvent actionEvent) {
        try {
            handleSql();
            com.github.saacsos.FXRouter.goTo("managerHome");
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.err.println("ไม่สามารถเข้าหน้า managerHome");
        }
    }

    @FXML
    public void backBtn(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("managerCheckStock");
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.err.println("ไม่สามารถเข้าหน้า managerHome");
        }
    }
}
