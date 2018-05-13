package hu.unideb.csongor.kolozsvari.cocktailbook.View;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IngredientListController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    Profile profile;
    SearchController searchController;

    public static final Logger logger = LoggerFactory.getLogger(CocktailListController.class);
    private final int gridColNumber = 5;

    public SearchController getSearchController() {
        return searchController;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
        createIngredientBlocks(searchController.getSearchedIngredients());
    }

    @FXML
    GridPane ingredienListGridPane;

    @FXML
    Button buttonBack;

    @FXML
    private void handleButtonBackAction(ActionEvent event) {
        logger.info("Clicked Back button on Ingredients List. Going to MainWindow view.");
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/MainWindow.fxml"));
            root = loader.load();
            stage = (Stage) ingredienListGridPane.getScene().getWindow();
            Scene scene = new Scene(root);

            MainWindowController mainWindowController = loader.getController();
            mainWindowController.setProfile(profile);
            mainWindowController.setSearchController(searchController);

            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on IngredientList button: " + e.getMessage());
        }
    }


    private void createIngredientBlocks(List<Ingredient> searchedIngredients){
        int rowIndex = 0, colIndex = 0;
        ingredienListGridPane.getChildren().clear();
        for(Ingredient ingredient : Ingredient.getAllIngredients()){
            addIngredientBlock(ingredient, searchedIngredients.contains(ingredient), colIndex, rowIndex);

            colIndex++;
            if(colIndex == gridColNumber){
                colIndex = 0;
                rowIndex++;
            }
        }
        logger.info("Put in all the cocktails");
    }

    private void addIngredientBlock(Ingredient ingredient, boolean added, int colIndex, int rowIndex){
        BorderPane borderPane = new BorderPane();
        ImageView imageView = new ImageView();
        HBox hbox = new HBox();
        Label label = new Label();
        Button button = new Button();
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/placeholder.png"));

        if (getClass().getClassLoader().getResource("images/Ingredients/" + ingredient.getImgPath()) != null) {
            image = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/Ingredients/" + ingredient.getImgPath()));
        }
        //borderPane.getStyleClass().add("ingredient-pane");
        //borderPane.setId("ingredient-pane");

        GridPane.setFillWidth(label, true);

        imageView.setImage(image);
        imageView.setFitWidth(180);
        imageView.setPreserveRatio(true);

        borderPane.setCenter(imageView);
        borderPane.setMaxWidth(320);
        borderPane.setMaxHeight(320);

        label.setText(ingredient.getName());
        //label.setId("ingredient-name");
        //label.getStylesheets().add("font-button");

        button.setText(added ? "Remove" : "Add");

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if(searchController.getSearchedIngredients().contains(ingredient)){
                button.setText("Add");
                searchController.removeIngredient(ingredient);
            }else {
                searchController.addIngredient(ingredient);
                button.setText("Remove");
            }
            event.consume();
        });

        hbox.getChildren().add(label);
        hbox.getChildren().add(button);

        borderPane.setBottom(hbox);

        ingredienListGridPane.add(borderPane, colIndex, rowIndex);
    }

}
