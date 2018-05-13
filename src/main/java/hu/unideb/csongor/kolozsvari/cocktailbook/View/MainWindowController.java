package hu.unideb.csongor.kolozsvari.cocktailbook.View;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
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

    //todo refactor to match nameButton not buttonName;
    @FXML
    HBox chosenIngredientsHBox;
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
    Label ingredientListLabel;
    @FXML
    TextField ingredientTextField;
    @FXML
    VBox vBox;
    @FXML
    FlowPane flowPane;

    @FXML
    TextField ingredientAddTextField;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
        this.profileController = new ProfileController(profile);
        logger.info("MainWindow view, profile is: " + profile);
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
        if(!searchController.getSearchedIngredients().isEmpty()) {
            updateAddedIngredients();

        }
        logger.info("MainWindow view, profile is: " + profile);
    }

    private void updateAddedIngredients(){
        logger.info("Updating added ingredients!");
        logger.info("Ingredients are:" + searchController.getSearchedIngredients());
        String ingredients = new String();
        Label label;
        chosenIngredientsHBox.getChildren().clear();
        for(Ingredient ingredient: searchController.getSearchedIngredients()) {
            label = new Label();
            label.setText(ingredient.getName());
            chosenIngredientsHBox.getChildren().add(label);

            ingredients += ingredient.getName() + "\n";
        }
        //ingredientListLabel.setText(ingredients);
    }

    private void setupIngredientsDropdownBox(){
        //TextField textField = TextFields.createClearableTextField();
        TextFields.bindAutoCompletion(ingredientTextField, searchController.collectIngredientNames(Ingredient.getAllIngredients()));
        ingredientAddTextField = ingredientTextField;
        //flowPane.getChildren().add(ingredientTextField);
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
            controller.setProfile(profile);
            controller.setCocktailList(Cocktail.getAllCocktails());
            controller.setSearchController(searchController);

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
            ingredientListController.setSearchController(searchController);
            ingredientListController.setProfile(profile);

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
            profileViewController.setProfile(profile);

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
            cocktailListController.setProfile(profile);
            cocktailListController.setSearchController(searchController);
            cocktailListController.setCocktailList(searchController.search());

            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on Search button: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("Mainwindow controller");
        searchController = new SearchController();
        setupIngredientsDropdownBox();

    }
}
