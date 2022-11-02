package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.services.PrawnDataSource;
import ku.cs.services.VendorOrderDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class StaffHomeController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void logoutBtn(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.err.println("ไม่สามารถเข้าหน้า login");
        }
    }

    @FXML
    public void staffGetShrimpButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffGetShrimp");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด main");
        }
    }

    @FXML
    public void staffPrepareButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffPrepareBorgung");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด main");
        }
    }

    @FXML
    public void staffShrimpFarmingButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffShrimpFarming");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด main");
        }
    }

    @FXML
    public void staffQCButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffQC");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด main");
        }
    }
}
