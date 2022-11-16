package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.*;
import ku.cs.services.*;
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
    @FXML
    private TableView<Farming> farmingTableView;

    private ObservableList<Farming> ObservableList;
    private FarmingList farmingList;
    private DataSource<FarmingList> farmingListDataSource;
    private ManagePrawnList managePrawnList;
    private DataSource<ManagePrawnList> managePrawnListDataSource;
    private QCList qcList;
    private DataSource<QCList> qcListDataSource;

    private String qcStatus;
    private ArrayList<String> getItem;

    @FXML
    public void initialize() {
        getItem = (ArrayList<String>) com.github.saacsos.FXRouter.getData();
        String userID  = getItem.get(0);

        farmingListDataSource = new FarmingDataSource();
        farmingList = farmingListDataSource.readData();
        managePrawnListDataSource = new ManagePrawnDataSource();
        managePrawnList = managePrawnListDataSource.readData();
        qcListDataSource = new QCDataSource();
        qcList = qcListDataSource.readData();

        showProductData();
        clearSelectedProduct();
        farmingTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedFarming(newValue);
            }
        });
    }

    private void showProductData() {
        farmingTableView.getItems().clear();
        farmingTableView.getColumns().clear();
        ObservableList = FXCollections.observableArrayList(farmingList.getFarmings());
        farmingTableView.setItems(ObservableList);
        ///แสดงแถวแนวตรง
        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID", "field:farmingID"));
        configs.add(new StringConfiguration("title:รอบ", "field:round"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            farmingTableView.getColumns().add(col);
        }
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

        ManagePrawn managePrawn = managePrawnList.latestManagePrawn(farming.getFarmingID());
//        System.out.println("this is: " + managePrawn);
        measureWeightLabel.setText(managePrawn.getNote());
    }

    private void clearSelectedProduct() {
        pondIdLabel.setText("");
        manageStatusLabel.setText("");
        measureWeightLabel.setText("");
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
        String userID  = getItem.get(0);

        QC qc = new QC(qcIDString, date, qcStatus, note, userID, farmingId);
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
