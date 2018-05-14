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

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.SearchController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Ingredient;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

    Profile profile;
    SearchController searchController;

    public static final Logger logger = LoggerFactory.getLogger(CocktailListController.class);
    private final int gridColNumber = 5;

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
            mainWindowController.initializeProfile(profile);
            mainWindowController.upadateSearchController(searchController);

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
        HBox nameHbox = new HBox();
        Label label = new Label();
        Button button = new Button();
        ScrollPane scrollPane = new ScrollPane();
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/placeholder.png"));

        if (getClass().getClassLoader().getResource("images/Ingredients/" + ingredient.getImgPath()) != null) {
            image = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/Ingredients/" + ingredient.getImgPath()));
        }
        scrollPane.setMaxHeight(180);
        scrollPane.setMaxWidth(180);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);
        scrollPane.setMouseTransparent(true);
        imageView.setImage(image);

        if(image.getHeight() > image.getWidth()){
            imageView.setFitWidth(180);
        } else{
            imageView.setFitHeight(180);
        }
        imageView.setPreserveRatio(true);
        scrollPane.setContent(imageView);
        borderPane.setCenter(scrollPane);
        borderPane.setMaxWidth(320);
        borderPane.setMaxHeight(320);
        label.setText(ingredient.getName());
        label.setId("ingredient-name");
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
        button.setScaleX(0.60);
        button.setScaleY(0.60);

        hbox.getChildren().add(label);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().add(button);
        borderPane.setBottom(hbox);
        ingredienListGridPane.add(borderPane, colIndex, rowIndex);
    }

    public SearchController getSearchController() {
        return searchController;
    }

    public void initializeProfile(Profile profile) {
        this.profile = profile;
    }

    public void updateSearchController(SearchController searchController) {
        this.searchController = searchController;
        createIngredientBlocks(searchController.getSearchedIngredients());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
