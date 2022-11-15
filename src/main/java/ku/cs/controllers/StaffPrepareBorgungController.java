package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class StaffPrepareBorgungController {

    @FXML private TableView<PreparePond> preparePondTableView;
    @FXML private ListView<String> preparePondListView;
    @FXML private Label pondIdLabel;
    @FXML private Label pondStatusLabel;
    @FXML private Label preparePondStatusLabel;

    private ObservableList<PreparePond> ObservableList;
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

        showProductData();
        clearSelectedProduct();
        preparePondTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedPond(newValue);
            }
        });
    }

    @FXML
    public void enterInProgressButton(ActionEvent actionEvent){
        try {
            pond.setStatus("กำลังดำเนินการ");
            pond.updateToSql();
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
            pond.updateToSql();
            showPond();
            System.out.println(pond.getStatus());
        }catch (Exception e) {
            System.err.println("ใส่ข้อมูลผิดพลาด");
        }
    }

    private void showProductData() {
        preparePondTableView.getItems().clear();
        preparePondTableView.getColumns().clear();
        ObservableList = FXCollections.observableArrayList(preparePondList.getPreparePonds());
        preparePondTableView.setItems(ObservableList);
        ///แสดงแถวแนวตรง
        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID", "field:prepareID"));
        configs.add(new StringConfiguration("title:รหัสพนักงาน", "field:employeeID"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            preparePondTableView.getColumns().add(col);
        }
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
        String pondStatus = pondList.getPondById(preparePond.getPondID()).getStatus();
        pondStatusLabel.setText(pondStatus);
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
