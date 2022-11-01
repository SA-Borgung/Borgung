package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.models.Pond;
import ku.cs.models.PondList;
import ku.cs.services.DataSource;
import ku.cs.services.PondDataSource;

import java.util.ArrayList;

public class StaffAddShrimpController {

    @FXML
    private ListView<String> addPondListView;

    private javafx.collections.ObservableList<String> ObservableList;
    private PondList pondList;
    private Pond pond;
    private DataSource<PondList> dataSource;

    @FXML
    public void initialize() {

        dataSource = new PondDataSource();
        pondList = dataSource.readData();


        showListView();
        clearSelectedProduct();
        handleSelectedListView();

    }

    private void showListView() {
        ListView<String> listView = new ListView<>();
        ObservableList = FXCollections.observableArrayList();
        ArrayList<Pond> tempPondList = new ArrayList<Pond>();
        for (int i = pondList.count()-1; i>=0; i--){
            Pond pond = pondList.getPondNumber(i);
            tempPondList.add(pond);
            ObservableList.add(pond.getId());

        }
        addPondListView.setItems(ObservableList);
    }

    private void handleSelectedListView() {
        addPondListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue,
                                        String oldValue, String newValue) {
                        Pond selectedPond = pondList.getPondById(newValue);
                        System.out.println(selectedPond + " is selected");
                        showSelectedPond(selectedPond);
                        selectedPond();
                    }
                });
    }

    private Pond selectedPond(){
        String selectedPondString = addPondListView.getSelectionModel().selectedItemProperty().get();
        //System.out.println(selectedVendorOrderString);
        Pond pond = pondList.getPondById(selectedPondString);
        return pond;
    }

    private void clearSelectedProduct() {
//        idLabel.setText("");
//        amountLabel.setText("");
//        SellerIdLabel.setText("");
//        StatusLabel.setText("");
//        ShrimpNameLabel.setText("");
    }

    private void showSelectedPond(Pond pond) {
//        idLabel.setText(vendorOrder.getId());
//        amountLabel.setText(String.valueOf(vendorOrder.getAmount()));
//        SellerIdLabel.setText(vendorOrder.getSellerName());
//        StatusLabel.setText(vendorOrder.getStatus());
//        String prawnName = prawnList.getPrawnById(vendorOrder.getOrderType()).getSpecies();
//        ShrimpNameLabel.setText(prawnName);
    }
}
