package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance.JSONUtils;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import hu.unideb.csongor.kolozsvari.cocktailbook.View.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

public class Main extends Application {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    private Profile profile;

    public void getjanos(){
       /* Set<Pair<Ingredient,Quantity>> myset = new HashSet<Pair<Ingredient,Quantity>>();
        Ingredient lime = new Ingredient(IngredientType.Fruit, IngredientSubType.Other, "lime", "someimage");
        Quantity quantity = new Quantity(QuantityType.Ounce, (float)4);
        myset.add(new Pair(lime, quantity));

        Coordinate flavorpoint = new Coordinate((float) 5, (float) 5);
        Cocktail mycock = new Cocktail(myset, flavorpoint,"Margartia", "shake without ice", "omega");


        JSONUtils jsonUtils = new JSONUtils();
        System.out.println("Reading margarita json: ");
        String json2 = jsonUtils.serializeCocktail(mycock);
        System.out.println("Deserialized: " + json2);

        Cocktail margarita = jsonUtils.deserializeCocktail(json2);
        System.out.println("The deserialized Margarita: " + margarita);*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        getjanos();
        LoadController loadController = new LoadController();
        loadController.readIngredients();
        loadController.readCocktails();
        profile = loadController.initializeProfile();
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            logger.info("getting fxml");

            fxmlLoader.setLocation((getClass().getClassLoader().getResource("FXML/MainWindow.fxml")));
            root = fxmlLoader.load();
            logger.info("got mainFXML");

            MainWindowController mainWindowController = fxmlLoader.getController();
            mainWindowController.setProfile(profile);

            primaryStage.setTitle("Cocktail Book");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            loadAssets(primaryStage);

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

    /** The main function. */
    public static void main(String[] args) {
        logger.info("App started.");
        launch(args);
    }

    public void loadAssets(Stage stage){

        Font.loadFont(getClass().getResourceAsStream("fonts/Lobster.ttf"), 44);
        Font.loadFont(getClass().getResourceAsStream("fonts/Cabin-Regular.ttf"), 12);

            /*
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icons/Icon32_runic.png")));
            Font.loadFont(getClass().getResourceAsStream("/styles/fonts/CurlyHogRunes-Body.ttf"), 16);
            Font.loadFont(getClass().getResourceAsStream("/styles/fonts/CurlyHogRunes-Medium.ttf"), 16);
            Font.loadFont(getClass().getResourceAsStream("/styles/fonts/CurlyHogRunes.ttf"), 16);
            */
        }

}

