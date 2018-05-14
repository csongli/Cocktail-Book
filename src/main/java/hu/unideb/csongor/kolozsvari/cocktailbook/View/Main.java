//CHECKSTYLE:OFF
package hu.unideb.csongor.kolozsvari.cocktailbook.View;

/*-
MIT License

Copyright (c) 2018 Csongor Kolozsvári

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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

