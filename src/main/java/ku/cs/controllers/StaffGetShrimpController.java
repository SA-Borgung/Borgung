package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.Prawn;
import ku.cs.models.PrawnList;
import ku.cs.models.VendorOrder;
import ku.cs.models.VendorOrderList;
import ku.cs.services.DataSource;
import ku.cs.services.PrawnDataSource;
import ku.cs.services.VendorOrderDataSource;

import java.io.IOException;
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
    @FXML
    private Label ShrimpNameLabel;

    private ObservableList<String> ObservableList;
    private VendorOrderList vendorOrderList;
    private PrawnList prawnList;
    private DataSource<VendorOrderList> dataSource;
    private DataSource<PrawnList> prawnListDataSource;

    private ArrayList<String> passItem;

    @FXML
    public void initialize() {

        dataSource = new VendorOrderDataSource();
        vendorOrderList = dataSource.readData();
        prawnListDataSource = new PrawnDataSource();
        prawnList = prawnListDataSource.readData();
        passItem = new ArrayList<>();


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
        System.out.println(selectedVendorOrderString);
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
        ShrimpNameLabel.setText("");


    }

    private void showSelectedVendor(VendorOrder vendorOrder) {
        idLabel.setText(vendorOrder.getId());
        amountLabel.setText(String.valueOf(vendorOrder.getAmount()));
        SellerIdLabel.setText(vendorOrder.getSellerName());
        StatusLabel.setText(vendorOrder.getStatus());
        String prawnName = prawnList.getPrawnById(vendorOrder.getOrderType()).getSpecies();
        ShrimpNameLabel.setText(prawnName);
    }

    private void setPassItem(String location) throws IOException {

        String R_ID = selectedVendorOrder().getId();
        passItem.add(R_ID);

        com.github.saacsos.FXRouter.goTo(location,passItem);
    }

    @FXML
    public void addShrimpButton(ActionEvent actionEvent) throws IOException {
        setPassItem("staffAddShrimp");
        try {
            com.github.saacsos.FXRouter.goTo("staffAddShrimp");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staffAddShrimp ไม่ได้");
        }

    }

    @FXML
    public void Button(ActionEvent actionEvent){

    }
}
