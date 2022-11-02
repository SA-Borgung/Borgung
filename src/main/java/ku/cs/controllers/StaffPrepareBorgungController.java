package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.models.*;
import ku.cs.services.*;

import java.util.ArrayList;

public class StaffPrepareBorgungController {

    @FXML
    private ListView<String> preparePondListView;
    @FXML
    private Label pondIdLabel;
    @FXML
    private Label pondStatusLabel;
    @FXML
    private Label preparePondStatusLabel;

    private ObservableList<String> ObservableList;
    private PondList pondList;
    private Pond pond;
    private DataSource<PondList> pondListDataSource;
    private PreparePondList preparePondList;
    private DataSource<PreparePondList> preparePondListDataSource;

    @FXML
    public void initialize() {
        pondListDataSource = new PondDataSource();
        pondList = pondListDataSource.readData();
        preparePondListDataSource = new PreparePondDataSource();
        preparePondList = preparePondListDataSource.readData();

        showListView();
        clearSelectedProduct();
        handleSelectedListView();
    }

    @FXML
    public void enterInProgressButton(ActionEvent actionEvent){

        try {
            pond.setStatus("กำลังดำเนินการ");
            showPond();
            System.out.println(pond.getStatus());
        }catch (Exception e) {
            System.err.println("ใส่ข้อมูลผิดพลาด");
        }
    }

    @FXML
    public void enterFinishProgressButton(ActionEvent actionEvent){

        try {
            pond.setStatus("เตรียมบ่อเสร็จสิ้น");
            showPond();
            System.out.println(pond.getStatus());
        }catch (Exception e) {
            System.err.println("ใส่ข้อมูลผิดพลาด");
        }
    }

    private void showListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<PreparePond> tempPreparePondList = new ArrayList<PreparePond>();
        for (int i = preparePondList.count()-1; i>=0; i--){
            PreparePond preparePond = preparePondList.getPreparePondNumber(i);
            tempPreparePondList.add(preparePond);
            ObservableList.add(preparePond.getPrepareID());

        }
        preparePondListView.setItems(ObservableList);
    }

    private void handleSelectedListView() {
        preparePondListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        PreparePond selectedPreparePond = preparePondList.getPreparePondById(newValue);
                        System.out.println(selectedPreparePond + " is selected");

                        selectedPreparePond();
                        showSelectedPond(selectedPreparePond);
                    }
                });
    }

    private PreparePond selectedPreparePond(){
        String selectedPreparePondString = preparePondListView.getSelectionModel().selectedItemProperty().get();
        PreparePond preparePond = preparePondList.getPreparePondById(selectedPreparePondString);
        String pondString = preparePond.getPrepareID();
        pond = pondList.getPondById(pondString);
        return preparePond;
    }

    private void showSelectedPond(PreparePond preparePond) {
        pondIdLabel.setText(preparePond.getPrepareID());
//        String pondString = preparePond.getPrepareID();
//        pond = pondList.getPondById(pondString);
        pondStatusLabel.setText(pond.getStatus());
        preparePondStatusLabel.setText(preparePond.getStatus());
    }

    private void showPond(){
        pondStatusLabel.setText(pond.getStatus());
    }

    private void clearSelectedProduct() {
        pondIdLabel.setText("");
        pondStatusLabel.setText("");
        preparePondStatusLabel.setText("");
    }


}
