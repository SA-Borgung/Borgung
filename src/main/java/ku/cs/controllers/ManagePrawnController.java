package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.ManagePrawnDataSource;

import java.util.ArrayList;

public class ManagePrawnController {
    @FXML private ChoiceBox<String> foodStatusChoiceBox;
    @FXML private ChoiceBox<String> medicineStatusChoiceBox;
    @FXML private ChoiceBox<String> isDeadChoiceBox;
    @FXML private TextArea textNote;
    @FXML private ListView<String> managePrawnListView;

    private ManagePrawn managePrawn;
    private ManagePrawnList managePrawnList;
    private DataSource<ManagePrawnList> dataSource = new ManagePrawnDataSource();
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        this.managePrawnList = dataSource.readData();
        this.showMangePrawnListView();
        this.addFoodStatusChoiceBox();
        this.addMedicineStatusChoiceBox();
        this.addIsDeadChoiceBox();
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

    private void addFoodStatusChoiceBox() {
        ArrayList<ManagePrawn> giveFoodStatus = new ArrayList<ManagePrawn>();
        for (int i = managePrawnList.count()-1; i>=0; i--){
            managePrawn = managePrawnList.getManagePrawnNumber(i);
            giveFoodStatus.add(managePrawn);
            this.observableList.add(Integer.toString(managePrawn.getGiveFoodStatus()));
        }
        this.foodStatusChoiceBox.setItems(observableList);
    }

    private void addMedicineStatusChoiceBox() {
        ArrayList<ManagePrawn> giveMedicineStatus = new ArrayList<ManagePrawn>();
        for (int i = managePrawnList.count()-1; i>=0; i--){
            managePrawn = managePrawnList.getManagePrawnNumber(i);
            giveMedicineStatus.add(managePrawn);
            this.observableList.add(Integer.toString(managePrawn.getGivePillsStatus()));
        }
        this.medicineStatusChoiceBox.setItems(observableList);
    }

    private void addIsDeadChoiceBox() {
        ArrayList<ManagePrawn> isDeadStatus = new ArrayList<ManagePrawn>();
        for (int i = managePrawnList.count()-1; i>=0; i--){
            managePrawn = managePrawnList.getManagePrawnNumber(i);
            isDeadStatus.add(managePrawn);
            this.observableList.add(Integer.toString(managePrawn.getIsDead()));
        }
        this.isDeadChoiceBox.setItems(observableList);
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