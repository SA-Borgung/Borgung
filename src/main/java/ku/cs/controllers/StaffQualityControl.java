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

public class StaffQualityControl {
    @FXML private Button passedButton;
    @FXML private Button failedButton;
    @FXML private TextField failedReason;
    @FXML private Label textMessage;
    @FXML private ListView<QC> QcListView;

    private boolean failedCheck;
    private QCList qualityControl;

    @FXML
    public void initialize() {
        DataSource<QCList> datasource= new QCDataSource();
        this.qualityControl = datasource.readData();
        this.showQualityControlList();
        this.addListViewListener();
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

    private void showQualityControlList() {
        ObservableList<QC> items = FXCollections.observableArrayList();
        for (QC qualityControl: qualityControl.getAllManagePrawn()){
            items.add(qualityControl);
        }
        this.QcListView.setItems(items);
        this.QcListView.refresh();
    }

    private void addListViewListener() {
        QcListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<QC>() {
            @Override
            public void changed(ObservableValue<? extends QC> observableValue, QC qc, QC t1) {
                //
            }
        });
    }
}
