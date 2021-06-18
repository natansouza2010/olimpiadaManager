package br.edu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent graph = FXMLLoader.load(getClass().getResource("app.fxml"));
        Scene scene = new Scene(graph,399,352);
        stage.setScene(scene);
        stage.show();
    }
}
