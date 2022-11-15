package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.ManagePrawn;
import ku.cs.models.ManagePrawnList;
import ku.cs.services.DataSource;
import ku.cs.services.ManagePrawnDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerCheckStockController {

    //still dont know

    @FXML
    private ListView<String> managePrawnListView;
    @FXML
    private Label prawnDetailLabel;
    @FXML
    private Label pondIDLabel;


    private javafx.collections.ObservableList<String> ObservableList;

    private ManagePrawnList managePrawnList;
    private ManagePrawn managePrawn;
    private DataSource<ManagePrawnList> dataSource;

    @FXML
    public void initialize() {

        dataSource = new ManagePrawnDataSource();
        managePrawnList = dataSource.readData();

        showListView();
        clearSelectedRow();
        handleSelectedManagePrawnListView();
//        handleSelectedListView();

    }

    private void showListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<ManagePrawn> tempVendorList = new ArrayList<ManagePrawn>();
        for (int i = managePrawnList.count()-1; i>=0; i--){
            ManagePrawn managePrawn = managePrawnList.getManagePrawnNumber(i);
            tempVendorList.add(managePrawn);
            ObservableList.add(managePrawn.getId());

        }
        managePrawnListView.setItems(ObservableList);
    }

    private void handleSelectedManagePrawnListView() {
        managePrawnListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        ManagePrawn selectedManagePrawn = managePrawnList.getManagePrawnById(newValue);
                        System.out.println(selectedManagePrawn + " is selected");
                        showSelectedManagePrawn(selectedManagePrawn);
                        selectedManagePrawn();
                    }
                });
    }



    private void clearSelectedRow() {
        prawnDetailLabel.setText("");
        pondIDLabel.setText("");


    }


    private void showSelectedManagePrawn(ManagePrawn managePrawn) {
//        prawnDetailLabel.setText(managePrawn.getMeasureWeight());
//        pondIDLabel.setText(managePrawn.getPondID());

    }

    private ManagePrawn selectedManagePrawn(){
        String selectedManagePrawnString = managePrawnListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        ManagePrawn managePrawn = managePrawnList.getManagePrawnById(selectedManagePrawnString);
        return managePrawn;
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
