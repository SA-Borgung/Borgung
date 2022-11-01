package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.models.QC;
import ku.cs.models.QCList;
import ku.cs.services.DataSource;
import ku.cs.services.QCDataSource;

import java.util.ArrayList;

public class StaffQcController {
    @FXML private Button passedButton;
    @FXML private Button failedButton;
    @FXML private TextField failedReason;
    @FXML private Label textMessage;
    @FXML private Label qcId;
    @FXML private Label qcTime;
    @FXML private Label qcRequirement;
    @FXML private Label employeeId;
    @FXML private Label pondId;
    @FXML private ListView<String> qcListView;

    private ObservableList<String> observableList;
    private DataSource<QCList> datasource;
    private QCList qcList;
    private boolean checkBox;

    @FXML
    public void initialize() {
        String qcId = "QC001";
        String qcTime = "12:00";
        String qcRequirement = "Hi";
        String employeeId = "EM001";
        String pondId = "W0001";
        QC qc = new QC(qcId,qcTime, qcRequirement, employeeId, pondId);
        datasource = new QCDataSource();
        qcList = datasource.readData();
        System.out.println(qcList.getAllManagePrawn());
        this.qcList.addQC(qc);
        this.showListView();
        this.clearData();
        this.handleSelectedListView();
    }

    private void clearData() {
        this.failedReason.setText("");
        this.textMessage.setText("");
        this.qcId.setText("");
        this.qcTime.setText("");
        this.qcRequirement.setText("");
        this.employeeId.setText("");
        this.pondId.setText("");
    }

    @FXML
    private void clickOnFailedButton() {
        this.checkBox = false;
        this.failedReason.setDisable(false);
        this.failedButton.setStyle("-fx-background-color: #FF8C00;");
        this.passedButton.setStyle("-fx-background-color: #FFD700;");
        System.out.println(checkBox);
    }

    @FXML
    private void clickOnPassedButton() {
        this.checkBox = true;
        this.failedReason.setDisable(true);
        this.passedButton.setStyle("-fx-background-color: #FF8C00;");
        this.failedButton.setStyle("-fx-background-color: #FFD700;");
        this.failedReason.clear();
        System.out.println(checkBox);
    }

    @FXML
    private void clickFinishedButton() {
        if (!checkBox && failedReason.getText().equals("")) {
            System.out.println("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
            this.textMessage.setText("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
        } else {
            System.out.println("ไปหน้าต่อไป");
        }
    }

    @FXML
    private void clickBackButton() {
        System.out.println("กลับไปหน้าก่อนหน้า");
    }

    private void showListView() {
        observableList = FXCollections.observableArrayList();
        ArrayList<QC> tempQcList = new ArrayList<>();
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
            }
        });
    }

    private void showSelectedQc(QC qualityControl) {
        this.qcId.setText(qualityControl.getId());
        this.qcTime.setText(qualityControl.getTime());
        this.qcRequirement.setText(qualityControl.getRequirement());
        this.employeeId.setText(qualityControl.getEmployeeID());
        this.pondId.setText(qualityControl.getPondID());
    }
}
