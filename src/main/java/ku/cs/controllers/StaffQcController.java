package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.FarmingDataSource;
import ku.cs.services.ManagePrawnDataSource;
import ku.cs.services.QCDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffQcController {
    @FXML private Button passedButton;
    @FXML private Button failedButton;
    @FXML private TextField failedReason;
    @FXML private TextField qcTimeTextField;
    @FXML private Label employeeIdLabel;
    @FXML private Label pondIdLabel;
    @FXML private Label measureWeightLabel;
    @FXML private Label manageStatusLabel;
    @FXML private ListView<String> farmingListView;

    private ObservableList<String> ObservableList;
    private FarmingList farmingList;
    private DataSource<FarmingList> farmingListDataSource;
    private ManagePrawnList managePrawnList;
    private DataSource<ManagePrawnList> managePrawnListDataSource;
    private QCList qcList;
    private DataSource<QCList> qcListDataSource;

    private String qcStatus;

    @FXML
    public void initialize() {
        farmingListDataSource = new FarmingDataSource();
        farmingList = farmingListDataSource.readData();
        managePrawnListDataSource = new ManagePrawnDataSource();
        managePrawnList = managePrawnListDataSource.readData();
        qcListDataSource = new QCDataSource();
        qcList = qcListDataSource.readData();

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
        pondIdLabel.setText(farming.getPondID());
        manageStatusLabel.setText(farming.getFarmingStatus());

//        ManagePrawn managePrawn = managePrawnList.latestDate(farming.getFarmingID());
//        measureWeightLabel.setText(managePrawn.getNote());
    }

    private void clearSelectedProduct() {
    }


    @FXML
    private void clickOnFailedButton() {
        qcStatus = "ไม่ผ่าน";
        this.failedReason.setDisable(false);
        this.failedButton.setStyle("-fx-background-color: #FF8C00;");
        this.passedButton.setStyle("-fx-background-color: #FFD700;");

    }

    @FXML
    private void clickOnPassedButton() {
        qcStatus = "ผ่าน";
        this.failedReason.setDisable(true);
        this.passedButton.setStyle("-fx-background-color: #FF8C00;");
        this.failedButton.setStyle("-fx-background-color: #FFD700;");
        this.failedReason.clear();
    }

    @FXML
    private void clickFinishedButton() {
        int qcID = qcList.count()+1;
        String qcIDString =  "QC"+ qcID;
        String date = qcTimeTextField.getText();
        String note = failedReason.getText();
        String farmingId = selectedFarming().getFarmingID();


        QC qc = new QC(qcIDString, date, qcStatus, note, "EP001", farmingId);
        qc.insertToSql();
        qcList.addQC(qc);
    }


    @FXML
    private void clickBackButton() {
        System.out.println("กลับไปหน้าก่อนหน้า");
    }

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
