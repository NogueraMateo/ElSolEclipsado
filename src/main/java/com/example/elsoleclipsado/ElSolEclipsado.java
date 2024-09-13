package com.example.elsoleclipsado;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ElSolEclipsado extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/elsoleclipsado/welcome-view.fxml")
        );
        Parent root = loader.load();
        primaryStage.setTitle("El Sol Eclipsado");
        Scene scene =  new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
