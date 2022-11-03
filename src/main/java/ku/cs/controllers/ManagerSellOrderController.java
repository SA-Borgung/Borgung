package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.models.Customer;
import ku.cs.models.Employee;
import ku.cs.models.Prawn;
import ku.cs.models.VendorOrder;

import java.io.IOException;

public class ManagerSellOrderController {

    @FXML
    TextField nameTextField,addressTextField,phoneTextField,idCardTextField;

    @FXML
    Label finishLB;

    public static final String ANSI_RESET = "\u001B[0m";



    @FXML
    private void clickFinishedButton() {
        //String id, String name, String phoneNumber, String address


        Customer customer = new Customer(idCardTextField.getText(),nameTextField.getText(),phoneTextField.getText(),idCardTextField.getText());

        customer.insertToSql();

        finishLB.setText("กรอกข้อมูล "+nameTextField.getText()+" สำเร็จ");
        finishLB.setTextFill(Color.web("#ff0000", 1));
//        if (!checkBox && failedReason.getText().equals("")) {
//            System.out.println("กรุณากรอกสาเหตุที่ไม่ผ่าน Q.C");
//        } else {
//            System.out.println("ไปหน้าต่อไป");
//        }

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
