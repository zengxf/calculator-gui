package calculator_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = Main.class.getResource("/calculator.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            Parent root = loader.load(url);
            // CalculatorController controller = loader.getController(); // 要先 load 才有 controller
            primaryStage.setTitle("计算器-Zxf");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}