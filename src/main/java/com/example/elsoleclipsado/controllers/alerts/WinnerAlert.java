package com.example.elsoleclipsado.controllers.alerts;

import javafx.scene.control.Alert;

/**
 * Alert intended to be shown when the user wins
 * @author Mateo Noguera Pinto
 * @version 1.0
 */
public class WinnerAlert implements AlertBoxInterface {

    /**
     * Method in charge of showing the alert
     * @param title: title of the alert
     * @param header: header of the alert
     * @param content: content of the alert
     */
    @Override
    public void showAlert (String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
