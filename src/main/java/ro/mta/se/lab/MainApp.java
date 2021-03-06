package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import ro.mta.se.lab.model.Weather;
import ro.mta.se.lab.controller.WeatherC;
import java.net.URL;


public class MainApp extends Application {
    public static void main(String[] args) throws Exception {
        WeatherC x=new WeatherC();
        x.initialize();
        launch(args);
    }

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.setLocation(this.getClass().
                    getResource("/view/MeteoTime.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}