package com.example.elsoleclipsado.view;

import com.example.elsoleclipsado.controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class in charge of setting the view and making sure the view is
 * instantiated once.
 * @author Mateo Noguera
 * @version 1.0
 */
public class GameView extends Stage {

    /**
     * Game controller
     * @serialField
     */
    private GameController gameController;


    /**
     * Class constructor: sets the view
     * @throws IOException
     */
    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass()
                        .getResource("/com/example/elsoleclipsado/game-view.fxml")
        );

        Parent root = loader.load();

        this.gameController = loader.getController();
        this.setScene(new Scene(root));
        this.setResizable(false);
        this.show();
    }


    /**
     *  Returns the gameController attribute
     * @return GameController
     */
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * Static inner class that acts as a holder for the singleton instance of GameView.
     */
    private static class GameViewHolder {
        private static GameView INSTANCE;
    }

    /**
     * Returns the GameView instance
     * @return GameView instance
     * @throws IOException
     */
    public static GameView getInstance() throws IOException {
        if (GameViewHolder.INSTANCE == null) {
            return GameViewHolder.INSTANCE = new GameView();
        } else {
            return GameView.GameViewHolder.INSTANCE;
        }
    }

}
