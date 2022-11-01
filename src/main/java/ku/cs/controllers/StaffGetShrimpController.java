package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.VendorOrder;
import ku.cs.models.VendorOrderList;
import ku.cs.services.DataSource;
import ku.cs.services.VendorOrderDataSource;

import java.util.ArrayList;

public class StaffGetShrimpController {

    @FXML
    private ListView<String> addVendorListView;
    @FXML
    private Label idLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label SellerIdLabel;
    @FXML
    private Label StatusLabel;

    private ObservableList<String> ObservableList;
    private VendorOrderList vendorOrderList;
    private VendorOrder vendorOrder;
    private DataSource<VendorOrderList> dataSource;

    @FXML
    public void initialize() {

        dataSource = new VendorOrderDataSource();
        vendorOrderList = dataSource.readData();

        showListView();
        clearSelectedProduct();
        handleSelectedListView();

    }

    private void showListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<VendorOrder> tempVendorList = new ArrayList<VendorOrder>();
        for (int i = vendorOrderList.count()-1; i>=0; i--){
            VendorOrder vendorOrder = vendorOrderList.getVendorOrderNumber(i);
            tempVendorList.add(vendorOrder);
            ObservableList.add(vendorOrder.getId());

        }
        addVendorListView.setItems(ObservableList);
    }

    private void handleSelectedListView() {
        addVendorListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        VendorOrder selectedVendorOrder = vendorOrderList.getVendorOrderById(newValue);
                        System.out.println(selectedVendorOrder + " is selected");
                        showSelectedVendor(selectedVendorOrder);
                        selectedVendorOrder();
                    }
                });
    }

    private VendorOrder selectedVendorOrder(){
        String selectedVendorOrderString = addVendorListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        VendorOrder vendorOrder = vendorOrderList.getVendorOrderById(selectedVendorOrderString);
        return vendorOrder;
    }

    @FXML
    private void handleSetWaitStatus(ActionEvent actionEvent){
        String addStatusString = "รอดำเนินการ";
        selectedVendorOrder().setStatus(addStatusString);
        showSelectedVendor(selectedVendorOrder());
        System.out.println(addStatusString);

    }

    @FXML
    private void handleSetFinishStatus(ActionEvent actionEvent){
        String addStatusString = "เสร็จสิ้น";
        selectedVendorOrder().setStatus(addStatusString);
        showSelectedVendor(selectedVendorOrder());
        System.out.println(addStatusString);

    }

    private void clearSelectedProduct() {
        idLabel.setText("");
        amountLabel.setText("");
        SellerIdLabel.setText("");
        StatusLabel.setText("");

    }

    private void showSelectedVendor(VendorOrder vendorOrder) {
        idLabel.setText(vendorOrder.getId());
        amountLabel.setText(String.valueOf(vendorOrder.getAmount()));
        SellerIdLabel.setText(vendorOrder.getSellerName());
        StatusLabel.setText(vendorOrder.getStatus());
    }

    @FXML
    public void Button(ActionEvent actionEvent){

    }
}
