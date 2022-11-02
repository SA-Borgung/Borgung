package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("member_card_detail"), 640, 480);
        //stage.setScene(scene);
        //stage.show();
        com.github.saacsos.FXRouter.bind(this, stage, "SA Project", 900, 700);
        configRoute();
        com.github.saacsos.FXRouter.goTo("login");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        com.github.saacsos.FXRouter.when("sample", packageStr+"sample.fxml");
        com.github.saacsos.FXRouter.when("managerOrderShrimp", packageStr+"managerOrderShrimp.fxml");
        com.github.saacsos.FXRouter.when("managerPrepareBorgung", packageStr+"managerPrepareBorgung.fxml");
        com.github.saacsos.FXRouter.when("managerCheckStock", packageStr+"managerCheckStock.fxml");
        com.github.saacsos.FXRouter.when("managerSellOrder", packageStr+"managerSellOrder.fxml");
        com.github.saacsos.FXRouter.when("login", packageStr+"login.fxml");
        com.github.saacsos.FXRouter.when("managerHome", packageStr+"managerHome.fxml");

    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}