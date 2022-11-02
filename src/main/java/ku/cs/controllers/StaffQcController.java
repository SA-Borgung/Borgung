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
import ku.cs.models.ManagePrawnList;
import ku.cs.models.QC;
import ku.cs.models.QCList;
import ku.cs.services.DataSource;
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
    @FXML private ListView<String> qcListView;

    private ObservableList<String> observableList;
    private DataSource<QCList> qcListDataSource;
    private DataSource<ManagePrawnList> managePrawnListDataSource;
    private QCList qcList;
    private ManagePrawnList managePrawnList;
    private ArrayList<String> passItem;

    private boolean checkBox;

    @FXML
    public void initialize() {
        qcListDataSource = new QCDataSource();
        qcList = qcListDataSource.readData();
        managePrawnListDataSource = new ManagePrawnDataSource();
        managePrawnList = managePrawnListDataSource.readData();
        passItem = new ArrayList<>();

        this.showListView();
        this.clearData();
        this.handleSelectedListView();
    }


    @FXML
    private void clickOnFailedButton() {
        this.checkBox = false;
        this.failedReason.setDisable(false);
        this.failedButton.setStyle("-fx-background-color: #FF8C00;");
        this.passedButton.setStyle("-fx-background-color: #FFD700;");
        this.manageStatusLabel.setText("ไม่ผ่าน");
        System.out.println(checkBox);
    }

    @FXML
    private void clickOnPassedButton() {
        this.checkBox = true;
        this.failedReason.setDisable(true);
        this.passedButton.setStyle("-fx-background-color: #FF8C00;");
        this.failedButton.setStyle("-fx-background-color: #FFD700;");
        this.manageStatusLabel.setText("ผ่าน");
        this.failedReason.clear();
        System.out.println(checkBox);
    }

    @FXML
    private void clickFinishedButton() {
        QC qc = new QC("QC006", "20 ตัวโล", "20:00:00", "ผ่าน", "Yes", "EP0006", "W0006");
        qc.insertToSql();
//        if (!checkBox && failedReason.getText().equals("")) {
//            System.out.println("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
//        } else {
//            System.out.println("ไปหน้าต่อไป");
//        }
    }

    @FXML
    private void clickUpdatedButton() {
        QC qc = new QC("QC0012", "25 ตัวโล", "23:00:00", "ผ่าน", "Yes", "EP0022", "W0013");
        qc.updateToSql();
//        if (!checkBox && failedReason.getText().equals("")) {
//            System.out.println("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
//        } else {
//            System.out.println("ไปหน้าต่อไป");
//        }
    }



    @FXML
    private void clickBackButton() {
        System.out.println("กลับไปหน้าก่อนหน้า");
    }

    private void showListView() {
        observableList = FXCollections.observableArrayList();
        ArrayList<QC> tempQcList = new ArrayList<QC>();
        for (int i = qcList.count()-1; i>=0; i--){
            QC qualityControl = qcList.getQCNumber(i);
            tempQcList.add(qualityControl);
            this.observableList.add(qualityControl.getId());
        }
        this.qcListView.setItems(observableList);
    }

    private void handleSelectedListView() {
        qcListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                QC selectedQc = qcList.searchQcOrderById(newValue);
                System.out.println(selectedQc + " is selected");
                showSelectedQc(selectedQc);
                selectedQcOrder();
            }
        });
    }

    private QC selectedQcOrder() {
        String selectedQcOrderString = qcListView.getSelectionModel().selectedItemProperty().get();
        System.out.println(selectedQcOrderString);
        QC qc = qcList.searchQcOrderById(selectedQcOrderString);
        return qc;
    }

    private void showSelectedQc(QC qualityControl) {
        this.employeeIdLabel.setText(qualityControl.getEmployeeID());
        this.pondIdLabel.setText(qualityControl.getPondID());
        this.measureWeightLabel.setText(qualityControl.getRequirement());
    }

    private void clearData() {
        this.failedReason.setText("");
        this.employeeIdLabel.setText("");
        this.pondIdLabel.setText("");
        this.measureWeightLabel.setText("");
        this.manageStatusLabel.setText("");
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
