package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.models.*;
import ku.cs.services.*;

import java.util.ArrayList;

public class ManagerCreatePurchaseOrderController {

    private PurchaseOrderList purchaseOrderList;
    private DataSource<PurchaseOrderList> purchaseOrderListDataSource;
    private CustomerList customerList;
    private DataSource<CustomerList> customerListDataSource;

    private ArrayList<String> getItem;

    @FXML
    public void initialize() {

        getItem = (ArrayList<String>) com.github.saacsos.FXRouter.getData();
        String name  = getItem.get(0);
        String phone  = getItem.get(1);
        String id  = getItem.get(2);
        String farmingID  = getItem.get(4);
        String price  = getItem.get(5);

        purchaseOrderListDataSource = new PurchaseOrderDataSource();
        purchaseOrderList = purchaseOrderListDataSource.readData();
        customerListDataSource = new CustomerDataSource();
        customerList = customerListDataSource.readData();
    }

    private void showData() {
        
    }
}
