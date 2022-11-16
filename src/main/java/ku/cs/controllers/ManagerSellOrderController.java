package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerSellOrderController {


    @FXML private TextField nameTextField,phoneTextField,idCardTextField;
    @FXML private TableView<Customer> customerTableView;
    @FXML private TextArea addressTextArea;
    @FXML private Label warningLabel;
    @FXML private Button codeOneButton;
    @FXML private Button codeTwoButton;
    @FXML private Button codeThreeButton;

    public static final String ANSI_RESET = "\u001B[0m";

    private ObservableList<Customer> customerObservableList;
    private CustomerList customerList;
    private DataSource<CustomerList> customerListDataSource;
    private ArrayList<String> passItem;
    private String purchaseType;

    @FXML
    public void initialize() {
        customerListDataSource = new CustomerDataSource();
        customerList = customerListDataSource.readData();
        passItem = new ArrayList<>();
        showProductData();
        clearSelectedProduct();
        customerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedCustomer(newValue);
            }
        });
    }

    private void showProductData() {
        customerTableView.getItems().clear();
        customerTableView.getColumns().clear();
        customerObservableList = FXCollections.observableArrayList(customerList.getAllCustomers());
        customerTableView.setItems(customerObservableList);
        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID", "field:id"));
        configs.add(new StringConfiguration("title:ชื่อลูกค้า", "field:name"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            customerTableView.getColumns().add(col);
        }
    }

    private void enterCustomerDetail(){
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String id = idCardTextField.getText();
        String address = addressTextArea.getText();
        String purchaseType = this.purchaseType;
        passItem.add(name);
        passItem.add(phone);
        passItem.add(id);
        passItem.add(address);
        passItem.add(purchaseType);
    }

//    public Farming selectedFarming(){
//        String selectedFarmingString = farmingListView.getSelectionModel().selectedItemProperty().get();
//        System.out.println(selectedFarmingString);
//        Farming farming = farmingList.getFarmingById(selectedFarmingString);
//        return farming;
//    }

    public void showSelectedCustomer(Customer customer){
        this.nameTextField.setText(customer.getName());
        this.phoneTextField.setText(customer.getPhoneNumber());
        this.idCardTextField.setText(customer.getId());
        this.addressTextArea.setText(customer.getAddress());
    }

    private void clearSelectedProduct() {
        warningLabel.setText("");
    }

    private void setPassItem(String location) throws IOException {
        com.github.saacsos.FXRouter.goTo(location,passItem);
    }

    @FXML
    private void pressOnCodeOneButton() {
        this.purchaseType = "บัตรเครดิต";
        this.codeOneButton.setStyle("-fx-background-color: #FF8C00;");
        this.codeTwoButton.setStyle("-fx-background-color: #FFD700;");
        this.codeThreeButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressOnCodeTwoButton() {
        this.purchaseType = "โอนจ่าย";
        this.codeTwoButton.setStyle("-fx-background-color: #FF8C00;");
        this.codeOneButton.setStyle("-fx-background-color: #FFD700;");
        this.codeThreeButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    private void pressOnCodeThreeButton() {
        this.purchaseType = "เงินสด";
        this.codeThreeButton.setStyle("-fx-background-color: #FF8C00;");
        this.codeOneButton.setStyle("-fx-background-color: #FFD700;");
        this.codeTwoButton.setStyle("-fx-background-color: #FFD700;");
    }

    @FXML
    public void checkStockButton(ActionEvent actionEvent) throws IOException {
        if (nameTextField.getText().isEmpty() || phoneTextField.getText().isEmpty()
                || idCardTextField.getText().isEmpty() || addressTextArea.getText().isEmpty()) {
            System.out.println("กรอกข้อมูลให้ครบ");
            warningLabel.setText("กรุณากรอกข้อมูลให้ครบ");
        } else {
            enterCustomerDetail();
            setPassItem("managerCheckStock");
            try {
                com.github.saacsos.FXRouter.goTo("managerCheckStock");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า managerCheckStock ไม่ได้");
            }
        }
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