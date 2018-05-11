package hu.unideb.csongor.kolozsvari.cocktailbook.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainWindowController {

    public static final Logger logger = LoggerFactory.getLogger(MainWindowController.class);

    @FXML
    Button buttonCocktailList;
    @FXML
    Button buttonIngredientList;
    @FXML
    Button buttonProfileView;
    @FXML
    Button buttonSearch;

    @FXML
    private void handleButtonCocktailListAction(ActionEvent event) {
        //go to CocktailList.fxml;
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/CocktailList.fxml"));
            root = loader.load();
            stage = (Stage) buttonCocktailList.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on CocktailList button: " + e.getMessage());
        }
    }

    @FXML
    private void handleButtonIngredientListAction(ActionEvent event) {
        //go to IngredientList.fxml
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/IngredientList.fxml"));
            root = loader.load();
            stage = (Stage) buttonIngredientList.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on IngredientList button: " + e.getMessage());
        }
    }

    @FXML
    private void handleButtonProfileViewAction(ActionEvent event) {
        //go to Profile.fxml
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/Profile.fxml"));
            root = loader.load();
            stage = (Stage) buttonProfileView.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on ProfileView button: " + e.getMessage());
        }
    }

    @FXML
    private void handleButtonSearchAction(ActionEvent event) {
        //go to CocktailList.fxml by search parameters;
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/CocktailList.fxml"));
            root = loader.load();
            stage = (Stage) buttonCocktailList.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on Search button: " + e.getMessage());
        }
    }
}
