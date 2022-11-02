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
}
