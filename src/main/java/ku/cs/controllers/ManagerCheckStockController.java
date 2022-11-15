package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.models.Farming;
import ku.cs.models.FarmingList;
import ku.cs.models.ManagePrawn;
import ku.cs.models.ManagePrawnList;
import ku.cs.services.DataSource;
import ku.cs.services.FarmingDataSource;
import ku.cs.services.ManagePrawnDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerCheckStockController {

    //still dont know

    @FXML
    private ListView<String> farmingListView;
    @FXML
    private Label pondLabel;
    @FXML
    private Label prawnLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private TextField priceTextField;

    private ObservableList<String> ObservableList;
    private FarmingList farmingList;
    private DataSource<FarmingList> farmingListDataSource;
    private ManagePrawnList managePrawnList;
    private DataSource<ManagePrawnList> managePrawnListDataSource;

    @FXML
    public void initialize() {

        farmingListDataSource = new FarmingDataSource();
        farmingList = farmingListDataSource.readData();
        managePrawnListDataSource = new ManagePrawnDataSource();
        managePrawnList = managePrawnListDataSource.readData();

        showListView();
        clearSelectedProduct();
        handleSelectedListView();
    }

    private void showListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<Farming> tempFarmingList = new ArrayList<Farming>();
        for (int i = farmingList.count()-1; i>=0; i--){
            Farming farming = farmingList.getFarmingNumber(i);
            if (!farming.getFarmingStatus().equals("ขายแล้ว")){
                if (!farming.getFarmingStatus().equals("เกิดปัญหา")){
                    tempFarmingList.add(farming);
                    String showList = farming.getFarmingID();
                    ObservableList.add(showList);
                }

            }
        }
        farmingListView.setItems(ObservableList);
    }

    private void handleSelectedListView() {
        farmingListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        Farming selectedFarming = farmingList.getFarmingById(newValue);
                        System.out.println(selectedFarming + " is selected");
                        showSelectedFarming(selectedFarming);
                        selectedFarming();
                    }
                });
    }

    public Farming selectedFarming(){
        String selectedFarmingString = farmingListView.getSelectionModel().selectedItemProperty().get();
        System.out.println(selectedFarmingString);
        Farming farming = farmingList.getFarmingById(selectedFarmingString);
        return farming;
    }

    public void  showSelectedFarming(Farming farming){
        pondLabel.setText(farming.getPondID());
        prawnLabel.setText("ไปเรียกข้อมูลซะ");
        weightLabel.setText("ไปเรียกข้อมูลซะ");
        amountLabel.setText(Integer.toString(farming.getPrawnAmount()));
        dateLabel.setText(farming.getGetDate());
    }

    private void clearSelectedProduct() {
        pondLabel.setText("");
        prawnLabel.setText("");
        weightLabel.setText("");
        amountLabel.setText("");
        dateLabel.setText("");
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
