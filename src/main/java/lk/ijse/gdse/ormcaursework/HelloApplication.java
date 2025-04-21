package lk.ijse.gdse.ormcaursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(HelloApplication.class.getResource("/style/HomeStyle.css").toExternalForm());
        stage.setTitle("MENTAL HEALTH THERAPY CENTER");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}