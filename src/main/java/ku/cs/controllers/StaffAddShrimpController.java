package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class StaffAddShrimpController {

    @FXML
    private ListView<String> addPondListView;
    @FXML
    private Label pondIdLabel;
    @FXML
    private Label pondStatusLabel;
    @FXML
    private Label shrimpQuantityLabel;
    @FXML
    private Label shrimpIdLabel;
    @FXML
    private Label vendorOrderLabel;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField roundTextField;

    private javafx.collections.ObservableList<String> ObservableList;
    private PondList pondList;
    private VendorOrder vendorOrder;
    private VendorOrderList vendorOrderList;
    private DataSource<VendorOrderList> vendorOrderListDataSource;
    private DataSource<PondList> dataSource;
    private DataSource<PrawnList> prawnListDataSource;
    private PrawnList prawnList;
    private DataSource<FarmingList> farmingListDataSource;
    private FarmingList farmingList;
    private Farming farming;

    private ArrayList<String> getItem;

    @FXML
    public void initialize() {

        getItem = (ArrayList<String>) com.github.saacsos.FXRouter.getData();
        String R_ID  = getItem.get(0);

        dataSource = new PondDataSource();
        pondList = dataSource.readData();
        vendorOrderListDataSource = new VendorOrderDataSource();
        vendorOrderList = vendorOrderListDataSource.readData();
        vendorOrder = vendorOrderList.getVendorOrderById(R_ID);
        farmingListDataSource = new FarmingDataSource();
        farmingList = farmingListDataSource.readData();

        prawnListDataSource = new PrawnDataSource();
        prawnList = prawnListDataSource.readData();
        Prawn prawn =prawnList.getPrawnById("1");
        showListView();
        clearSelectedProduct();
        handleSelectedListView();
        showVendorOrder();

    }

    private void handleAddFarming(String date, int round){
        if (farming.addFarmingInputCheck(date, round)){
            farmingList.addFarming(farming);
        }
        else {
            warningLabel.setText("ข้อมูลผิดพลาด");
        }
    }

    @FXML
    public void enterButton(ActionEvent actionEvent){

        try {
            vendorOrderList.updateVendorOrder(vendorOrder, "user01");
            String farmId = "";
            String roundString = roundTextField.getText();
            int round = Integer.parseInt(roundString);
            int amount = vendorOrder.getAmount();
            System.out.println(roundString);
            String prawnId = vendorOrder.getOrderType();
            String dateString = dateTextField.getText();
            String vendorId = vendorOrder.getId();

            farming = new Farming(farmId, round, amount, prawnId, dateString,"NULL", "NULL", vendorId);
            System.out.println("ก่อนเพิ่ม");
            handleAddFarming(dateString, round);
            System.out.println("เพิ่มแล้ว");

        }catch (Exception e) {
            System.err.println("ใส่ข้อมูลผิดพลาด");
        }


    }

    private void showListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<Pond> tempPondList = new ArrayList<Pond>();
        for (int i = pondList.count()-1; i>=0; i--){
            Pond pond = pondList.getPondNumber(i);
            tempPondList.add(pond);
            ObservableList.add(pond.getId());

        }
        addPondListView.setItems(ObservableList);
    }

    private void handleSelectedListView() {
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

    private Pond selectedPond(){
        String selectedPondString = addPondListView.getSelectionModel().selectedItemProperty().get();
        Pond pond = pondList.getPondById(selectedPondString);
        return pond;
    }

    private void clearSelectedProduct() {
        pondIdLabel.setText("");
        pondStatusLabel.setText("");
    }

    private void showSelectedPond(Pond pond) {
        pondIdLabel.setText(pond.getId());
        pondStatusLabel.setText(pond.getStatus());


    }

    private void showVendorOrder(){
        shrimpQuantityLabel.setText(String.valueOf(vendorOrder.getAmount()));
        String prawnName = prawnList.getPrawnById(vendorOrder.getOrderType()).getSpecies();
        shrimpIdLabel.setText(prawnName);
        vendorOrderLabel.setText(vendorOrder.getId());
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
