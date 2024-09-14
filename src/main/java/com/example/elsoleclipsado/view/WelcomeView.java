package com.example.elsoleclipsado.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * It loads the corresponding FXML file and sets up the main scene.
 */
public class WelcomeView extends Stage {

    /**
     * Constructor for the WelcomeView class.
     * @throws IOException if the FXML file cannot be found or loaded.
     */
    public WelcomeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/elsoleclipsado/welcome-view.fxml")
        );
        Parent root = loader.load();
        this.setTitle("El Sol Eclipsado");
        Scene scene =  new Scene(root);
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }


    /**
     * Static inner class that acts as a holder for the singleton instance of WelcomeView.
     */
    private static class WelcomeViewHolder {
        private static WelcomeView INSTANCE;
    }


    /**
     * Retrieves the singleton instance of WelcomeView.
     * Implements the Singleton pattern to ensure only one instance of WelcomeView exists.
     *
     * @return The single instance of WelcomeView.
     * @throws IOException
     */
    public static WelcomeView getInstance() throws IOException {
        if (WelcomeViewHolder.INSTANCE == null) {
            return WelcomeViewHolder.INSTANCE = new WelcomeView();
        } else {
            return WelcomeViewHolder.INSTANCE;
        }
    }

}
