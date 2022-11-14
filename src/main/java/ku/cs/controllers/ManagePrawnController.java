package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class ManagePrawnController {
    @FXML private Button giveFoodButton;
    @FXML private Button notGiveFoodButton;
    @FXML private Button givePillsButton;
    @FXML private Button notGivePillsButton;
    @FXML private Button codeOneButton;
    @FXML private Button codeTwoButton;
    @FXML private Button codeThreeButton;
    @FXML private TextArea textNote;
    @FXML private ListView<String> farmingListView;

    private int statusCode;
    private ObservableList<String> ObservableList;
    private FarmingList farmingList;
    private Farming farming;
    private DataSource<FarmingList> farmingListDataSource;


//    @FXML
//    public void initialize() {
//        farmingListDataSource = new FarmingDataSource();
//        farmingList = farmingListDataSource.readData();
//
//        showListView();
//        clearSelectedProduct();
//        handleSelectedListView();
//    }
//
//
//    private void showListView() {
//        ListView<String> listView = new ListView<>();
//        ObservableList = FXCollections.observableArrayList();
//        ArrayList<Farming> tempFarmingList = new ArrayList<Farming>();
//        for (int i = farmingList.count()-1; i>=0; i--){
//            Farming farming = farmingList.getFarmingNumber(i);
//            if (!farming.getFarmingStatus().equals("ขายแล้ว")){
//                tempFarmingList.add(farming);
//                ObservableList.add(farming.getFarmingID());
//            }
//
//
//        }
//        farmingListView.setItems(ObservableList);
//    }
//
//    private void handleSelectedListView() {
//        farmingListView.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observableValue,
//                                        String oldValue, String newValue) {
//                        Farming selectedFarming = farmingList.getVendorOrderById(newValue);
//                        System.out.println(selectedFarming + " is selected");
//                        showSelectedVendor(selectedFarming);
//                        selectedVendorOrder();
//                    }
//                });
//    }
//
//    private void clearSelectedProduct() {
//        idLabel.setText("");
//        amountLabel.setText("");
//        SellerIdLabel.setText("");
//        StatusLabel.setText("");
//        ShrimpNameLabel.setText("");
//    }

    @FXML
    private void pressGoBack() {
        System.out.println("กลับไปหน้าก่อนหน้า");
    }

    @FXML
    private void pressSave() {
        System.out.println("บันทึกแล้ว");
    }

    @FXML
    private void pressYesOnFoodStatus() {
//        this.foodStatus = true;
        this.giveFoodButton.setStyle("-fx-background-color: #FF8C00;");
        this.notGiveFoodButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressNoOnFoodStatus() {
//        this.foodStatus = false;
        this.notGiveFoodButton.setStyle("-fx-background-color: #FF8C00;");
        this.giveFoodButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressYesOnPillsStatus() {
//        this.pillsStatus = true;
        this.givePillsButton.setStyle("-fx-background-color: #FF8C00;");
        this.notGivePillsButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressNoOnPillsStatus() {
//        this.pillsStatus = false;
        this.notGivePillsButton.setStyle("-fx-background-color: #FF8C00;");
        this.givePillsButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressOnCodeOneButton() {
        this.statusCode = 1;
        showStatusCode(statusCode);
        this.codeOneButton.setStyle("-fx-background-color: #FF8C00;");
        this.codeTwoButton.setStyle("-fx-background-color: #FFD700;");
        this.codeThreeButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressOnCodeTwoButton() {
        this.statusCode = 2;
        showStatusCode(statusCode);
        this.codeTwoButton.setStyle("-fx-background-color: #FF8C00;");
        this.codeOneButton.setStyle("-fx-background-color: #FFD700;");
        this.codeThreeButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressOnCodeThreeButton() {
        this.statusCode = 3;
        showStatusCode(statusCode);
        this.codeThreeButton.setStyle("-fx-background-color: #FF8C00;");
        this.codeOneButton.setStyle("-fx-background-color: #FFD700;");
        this.codeTwoButton.setStyle("-fx-background-color: #FFD700;");
    }

    private void showStatusCode(int statusCode) {
        System.out.println("Status Code is " + statusCode);
    }

    private void clearData() {
        this.textNote.setText("");
    }

//    private void showMangePrawnListView() {
//        ArrayList<ManagePrawn> tempManagePrawnList = new ArrayList<ManagePrawn>();
//        for (int i = managePrawnList.count()-1; i>=0; i--){
//            managePrawn = managePrawnList.getManagePrawnNumber(i);
//            tempManagePrawnList.add(managePrawn);
//            this.observableList.add(managePrawn.getId());
//        }
//        this.managePrawnListView.setItems(observableList);
//    }

    @FXML
    public void BackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffHome");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}