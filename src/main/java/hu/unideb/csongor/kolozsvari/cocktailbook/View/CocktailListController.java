//CHECKSTYLE:OFF
package hu.unideb.csongor.kolozsvari.cocktailbook.View;

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.SearchController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CocktailListController implements Initializable {

    Profile profile;
    ProfileController profileController;
    SearchController searchController;

    public static final Logger logger = LoggerFactory.getLogger(CocktailListController.class);

    private final int gridColNumber = 5;
    @FXML
    Pane transparentShadowPane;

    @FXML
    BorderPane cocktailDetailsPane;
    @FXML
    StackPane cocktailListStackPane;
    @FXML
    GridPane cocktailListGridPane;
    @FXML
    Label cocktailNameLabel;
    @FXML
    Button cocktailDetailsExitButton;
    @FXML
    TextArea ingredientNamesTextArea;
    @FXML
    TextArea ingredientQuantitiesTextArea;
    @FXML
    TextArea cocktailDescriptionTextArea;
    @FXML
    ImageView cocktailImageView;
    @FXML
    Button addCocktailToFavoritesButton;
    @FXML
    Button cocktailListBackButton;
    @FXML
    ScrollPane cocktailsScrollPane;
    @FXML
    private void handleCocktailListBackButtonAction(ActionEvent event) {
        logger.info("Clicked Back button on Cocktail List. Going to MainWindow view.");
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/MainWindow.fxml"));
            root = loader.load();
            stage = (Stage) cocktailListBackButton.getScene().getWindow();
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

    private void createCocktailBlocks(List<Cocktail> cocktails) {
        int rowIndex = 0, colIndex = 0;
        cocktailListGridPane.getChildren().clear();
        for (Cocktail cocktail : cocktails) {

            addCocktailBlock(cocktail, colIndex, rowIndex);

            colIndex++;
            if (colIndex == gridColNumber) {
                colIndex = 0;
                rowIndex++;
            }
        }
        logger.info("Put in all the cocktails");
    }

    private void addCocktailBlock(Cocktail cocktail, int colIndex, int rowIndex) {
        BorderPane borderPane = new BorderPane();
        ImageView imageView = new ImageView();
        ScrollPane scrollPane = new ScrollPane();
        HBox nameHbox = new HBox();

        Label label = new Label();
        borderPane.getStyleClass().add("cocktail-pane");
        borderPane.setId("cocktail-pane");

        GridPane.setFillWidth(label, true);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/placeholder.png"));
        if (getClass().getClassLoader().getResource("images/Cocktails/" + cocktail.getImgPath()) != null) {
            image = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/Cocktails/" + cocktail.getImgPath()));
        }

        scrollPane.setMaxHeight(180);
        scrollPane.setMaxWidth(180);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);
        scrollPane.setMouseTransparent(true);

        imageView.setImage(image);
        if (image.getHeight() > image.getWidth()) {
            imageView.setFitWidth(180);
        } else {
            imageView.setFitHeight(180);
        }


        imageView.setPreserveRatio(true);
        scrollPane.setContent(imageView);

        borderPane.setCenter(scrollPane);

        borderPane.setMaxWidth(320);
        borderPane.setMaxHeight(320);

        label.setText(cocktail.getName());
        label.setId("cocktail-name");
        nameHbox.setAlignment(Pos.CENTER);
        nameHbox.getChildren().add(label);
        borderPane.setBottom(nameHbox);

        borderPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            showCocktailDetail(cocktail);
            event.consume();
        });

        borderPane.setPrefHeight(240);
        borderPane.setPrefWidth(240);

        cocktailListGridPane.add(borderPane, colIndex, rowIndex);
    }

    private void showCocktailDetail(Cocktail cocktail) {
        logger.info("Showing cocktail details for " + cocktail.getName());
        logger.debug("Showing cocktail: " + cocktail);
        transparentShadowPane.setVisible(true);
        cocktailDetailsPane.setVisible(true);
        cocktailNameLabel.setText(cocktail.getName());

        String ingredientNames = new String();
        for (String ingredientName : cocktail.getIngredientNames()) {
            ingredientNames += ingredientName + "\n";
        }
        String ingredientQuanities = new String();
        for (String ingredientQuantity : cocktail.getIngredientQuanities()) {
            ingredientQuanities += ingredientQuantity + "\n";
        }
        ingredientNamesTextArea.setText(ingredientNames);
        ingredientQuantitiesTextArea.setText(ingredientQuanities);

        cocktailDescriptionTextArea.setText(cocktail.getDescription());

        Image cocktailImage = new Image(getClass().getClassLoader()
                .getResourceAsStream("images/placeholder.png"));

        if (getClass().getClassLoader().getResource("images/Cocktails/" + cocktail.getImgPath()) != null) {
            cocktailImage = new Image(getClass().getClassLoader()
                    .getResourceAsStream("images/Cocktails/" + cocktail.getImgPath()));
        }
        cocktailImageView.setTranslateX(-cocktailImage.getWidth() / 2 + 30);
        if (cocktailImage.getHeight() >= 860) {
            cocktailImageView.setFitWidth(cocktailImage.getWidth());
        }

        cocktailImageView.setImage(cocktailImage);
        addCocktailToFavoritesButton.setText(profile.getFavouriteCocktails().contains(cocktail) ? "Remove from favorites" : "Add to favorites");
        addCocktailToFavoritesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (profile.getFavouriteCocktails().contains(cocktail)) {
                logger.info("Favorited, removing from favorites:");
                profileController.removeCocktailFromFavourites(cocktail);
                addCocktailToFavoritesButton.setText("Add to favorites");
            } else {

                logger.info("Not favorited, favoriting:");
                profileController.addCocktailToFavourites(cocktail);
                addCocktailToFavoritesButton.setText("Remove from favorites");
            }
            event.consume();
        });
    }

    private void hideCocktailDetail() {
        cocktailDetailsPane.setVisible(false);
        transparentShadowPane.setVisible(false);
    }

    @FXML
    private void handleCocktailDetailsExitButton(ActionEvent event) {
        hideCocktailDetail();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setCocktailList(List<Cocktail> cocktails) {
        createCocktailBlocks(cocktails);
    }

    public Profile getProfile() {
        return profile;
    }

    public void initializeProfile(Profile profile) {
        this.profile = profile;
        this.profileController = new ProfileController(profile);
    }

    public SearchController getSearchController() {
        return searchController;
    }

    public void updateSearchController(SearchController searchController) {
        this.searchController = searchController;
    }
}
