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
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.SearchController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Ingredient;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    Profile profile;
    SearchController searchController;
    ProfileController profileController;

    public static final Logger logger = LoggerFactory.getLogger(MainWindowController.class);

    @FXML
    FlowPane chosenIngredientsFlowPane;
    @FXML
    Button buttonCocktailList;
    @FXML
    Button buttonIngredientList;
    @FXML
    Button buttonProfileView;
    @FXML
    Button addIngredientButton;
    @FXML
    Button buttonSearch;
    @FXML
    TextField ingredientTextField;
    @FXML
    VBox vBox;
    @FXML
    FlowPane flowPane;
    @FXML
    TextField ingredientAddTextField;

    private void updateAddedIngredients(){
        logger.info("Updating added ingredients!");
        logger.info("Ingredients are:" + searchController.getSearchedIngredients());
        Label label;
        chosenIngredientsFlowPane.getChildren().clear();
        for(Ingredient ingredient: searchController.getSearchedIngredients()) {
            label = new Label();
            label.setText(ingredient.getName());
            chosenIngredientsFlowPane.getChildren().add(label); }
    }

    private void setupIngredientsDropdownBox(){
        TextFields.bindAutoCompletion(ingredientTextField, searchController.collectIngredientNames(Ingredient.getAllIngredients()));
        ingredientAddTextField = ingredientTextField;
    }

    @FXML
    private void handleButtonCocktailListAction(ActionEvent event) {
        //go to CocktailList.fxml;
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/CocktailList.fxml"));
            root = loader.load();

            CocktailListController controller = loader.getController();
            controller.initializeProfile(profile);
            controller.setCocktailList(Cocktail.getAllCocktails());
            controller.updateSearchController(searchController);

            stage = (Stage) buttonCocktailList.getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on CocktailList button: " + e.getMessage());
        }
    }

    @FXML
    private void handleButtonAddIngredientAction(ActionEvent event){
        //if(searchComboBox)
        logger.info("Clicked add");
        logger.info("ing text field is:" + ingredientTextField);

        if(searchController.isIngredient(ingredientTextField.getText())){
            Ingredient ingredient = LoadController.getIngredientFromName(ingredientTextField.getText());
            searchController.addIngredient(ingredient);
            updateAddedIngredients();
            ingredientTextField.setText("");
        }
        else{
            logger.info( ingredientTextField.getText() +  " is not an ingredient.");
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

            IngredientListController ingredientListController = loader.getController();
            ingredientListController.updateSearchController(searchController);
            ingredientListController.initializeProfile(profile);

            scene.getStylesheets().add("style.css");
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

            logger.info("Trying to calc flav point: " + profile);
            logger.info("prof controller: " + profileController);
            logger.info("fav cocktails " + profile.getFavouriteCocktails());

            profileController.calculateFlavorMapPoint(profile.getFavouriteCocktails());
            ProfileViewController profileViewController = loader.getController();
            profileViewController.setSearchController(searchController);
            profileViewController.initializeProfile(profile);

            scene.getStylesheets().add("style.css");
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

            CocktailListController cocktailListController = loader.getController();
            cocktailListController.initializeProfile(profile);
            cocktailListController.updateSearchController(searchController);
            cocktailListController.setCocktailList(searchController.search());

            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on Search button: " + e.getMessage());
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public void initializeProfile(Profile profile) {
        this.profile = profile;
        this.profileController = new ProfileController(profile);
        logger.info("MainWindow view, profile is: " + profile);
    }

    public void upadateSearchController(SearchController searchController) {
        this.searchController = searchController;
        if(!searchController.getSearchedIngredients().isEmpty()) {
            updateAddedIngredients();

        }
        logger.info("MainWindow view, profile is: " + profile);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("Mainwindow controller");
        searchController = new SearchController();
        setupIngredientsDropdownBox();

    }
}
