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

public class StaffQualityControl {
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
    private QCList qualityControlList;
    private boolean failedCheck;

    @FXML
    public void initialize() {
        DataSource<QCList> datasource = new QCDataSource();
        this.qualityControlList = datasource.readData();
        this.clearData();
        this.showQualityControlListView();
        this.handleSelectedListView();
    }

    private void clearData() {
        this.failedReason.setText("");
        this.textMessage.setText("");
    }

    @FXML
    private void clickOnFailedButton() {
        this.failedCheck = true;
        this.failedReason.setDisable(false);
        this.failedButton.setStyle("-fx-background-color: #FF8C00;");
        this.passedButton.setStyle("-fx-background-color: #FFD700;");
        System.out.println(failedCheck);
    }

    @FXML
    private void clickOnPassedButton() {
        this.failedCheck = false;
        this.failedReason.setDisable(true);
        this.passedButton.setStyle("-fx-background-color: #FF8C00;");
        this.failedButton.setStyle("-fx-background-color: #FFD700;");
        this.failedReason.clear();
        System.out.println(failedCheck);
    }

    @FXML
    private void clickFinishedButton() {
        if (failedCheck && failedReason.getText().equals("")) {
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

    private void showQualityControlListView() {
        ListView<String> listView = new ListView<>();
        observableList = FXCollections.observableArrayList();
        ArrayList<QC> tempQcList = new ArrayList<QC>();
        for (int i = qualityControlList.count()-1; i>=0; i--){
            QC qualityControl = qualityControlList.getQCNumber(i);
            tempQcList.add(qualityControl);
            this.observableList.add(qualityControl.getId());
        }
        this.qcListView.setItems(observableList);
    }

    private void handleSelectedListView() {
        qcListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                QC selectedQc = qualityControlList.searchQcOrderById(newValue);
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
