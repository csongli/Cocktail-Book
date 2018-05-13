//CHECKSTYLE:OFF
package hu.unideb.csongor.kolozsvari.cocktailbook.View;

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.LoadController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

/**
 * The main class.
 */
public class Main extends Application {

    /** Logger. */
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    /** Profile that contains user settings. */
    private Profile profile;

    /**
     * The Start function.
     * @param primaryStage the {@code Stage} to show.
     * @throws Exception when the needed FXML files aren't found.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        LoadController loadController = new LoadController();
        loadController.readIngredients();
        loadController.readCocktails();
        profile = loadController.initializeProfile();
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation((getClass().getClassLoader().getResource("FXML/MainWindow.fxml")));
            root = fxmlLoader.load();

            MainWindowController mainWindowController = fxmlLoader.getController();
            mainWindowController.initializeProfile(profile);

            primaryStage.setTitle("Cocktail Book");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            loadAssets();

            primaryStage.setOnCloseRequest(event -> {
                logger.info("Clicked exit button.");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(yesButton, noButton);
                alert.setTitle("Save Profile");
                alert.setContentText("Do you wish to save changes before exiting?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == yesButton) {
                    try {
                        loadController.saveProfile(profile);
                        logger.debug("Saved profile, exiting.");
                    } catch (Exception e) {
                        logger.error("Exception while saving profile!");
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Save Error");
                        errorAlert.setContentText("Cannot save changes!");
                        errorAlert.showAndWait();
                    }
                } else {
                    logger.info("Exited without saving profile!");
                }

                Platform.exit();
            });


            primaryStage.show();
        } catch (IOException e) {
            logger.error("IOException on start.");
        }
    }

    /** The main function.
     * @param args the arguments.
     */
    public static void main(String[] args) {
        logger.info("App started.");
        launch(args);
    }

    /**
     * Loads fonts for the application.
     */
    public void loadAssets(){
        Font.loadFont(getClass().getResourceAsStream("fonts/Lobster.ttf"), 44);
        }

}

