package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.ManagePrawnDataSource;

import java.util.ArrayList;

public class ManagePrawnController {
    @FXML private Button giveFoodButton;
    @FXML private Button notGiveFoodButton;
    @FXML private Button givePillsButton;
    @FXML private Button notGivePillsButton;
    @FXML private Button deadButton;
    @FXML private Button notDeadButton;
    @FXML private TextArea textNote;
    @FXML private ListView<String> managePrawnListView;

    private boolean foodStatus;
    private boolean pillsStatus;
    private boolean isDead;
    private ManagePrawn managePrawn;
    private ManagePrawnList managePrawnList;
    private DataSource<ManagePrawnList> dataSource = new ManagePrawnDataSource();
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        this.managePrawnList = dataSource.readData();
        this.showMangePrawnListView();
        this.clearData();
    }

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
        this.foodStatus = true;
        this.giveFoodButton.setStyle("-fx-background-color: #FF8C00;");
        this.notGiveFoodButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressNoOnFoodStatus() {
        this.foodStatus = false;
        this.notGiveFoodButton.setStyle("-fx-background-color: #FF8C00;");
        this.giveFoodButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressYesOnPillsStatus() {
        this.pillsStatus = true;
        this.givePillsButton.setStyle("-fx-background-color: #FF8C00;");
        this.notGivePillsButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressNoOnPillsStatus() {
        this.pillsStatus = false;
        this.notGivePillsButton.setStyle("-fx-background-color: #FF8C00;");
        this.givePillsButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressYesOnIsDead() {
        this.isDead = true;
        this.deadButton.setStyle("-fx-background-color: #FF8C00;");
        this.notDeadButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void presNoOnIsDead() {
        this.isDead = false;
        this.notDeadButton.setStyle("-fx-background-color: #FF8C00;");
        this.deadButton.setStyle("-fx-background-color: #FFD700;");
    }

    private void clearData() {
        this.textNote.setText("");
    }

    private void showMangePrawnListView() {
        ArrayList<ManagePrawn> tempManagePrawnList = new ArrayList<ManagePrawn>();
        for (int i = managePrawnList.count()-1; i>=0; i--){
            managePrawn = managePrawnList.getManagePrawnNumber(i);
            tempManagePrawnList.add(managePrawn);
            this.observableList.add(managePrawn.getId());
        }
        this.managePrawnListView.setItems(observableList);
    }
}