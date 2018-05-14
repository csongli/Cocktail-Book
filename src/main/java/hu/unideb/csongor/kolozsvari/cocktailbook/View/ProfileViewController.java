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

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.SearchController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Cocktail;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Coordinate;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ProfileViewController implements Initializable {
    Profile profile;
    ProfileController profileController;
    SearchController searchController;

    private final int xAxisMax = 10;
    private final int xAxisMin = -10;
    private final int xAxisTick = 1;
    private final int yAxisMax = 10;
    private final int yAxisMin = -10;
    private final int yAxisTick = 1;
    private final int gridColNumber = 3;
    public static final Logger logger = LoggerFactory.getLogger(ProfileViewController.class);

    @FXML
    GridPane favoriteCocktailsGridPane;;
    @FXML
    Button profileBackButton;
    @FXML
    Canvas flavorMapCanvas;
    @FXML
    AnchorPane flavorMapAnchorPane;
    @FXML
    Label favoritedCocktailCountLabel;
    @FXML
    Button recommendationRadiusDecreaseButton;
    @FXML
    Button recommendationRadiusIncreaseButton;
    @FXML
    Label recommendationRadiusLabel;
    @FXML
    Label flavorPointCoordinateLabel;
    @FXML
    Button showRecommendationsButton;

    @FXML
    private void handleProfileBackButtonAction(ActionEvent event){
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/MainWindow.fxml"));
            root = loader.load();
            stage = (Stage) profileBackButton.getScene().getWindow();
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

    private void setupFlavorMap(){
        NumberAxis xAxis = new NumberAxis("Values for X-Axis", xAxisMin, xAxisMax, xAxisTick);
        NumberAxis yAxis = new NumberAxis("Values for Y-Axis", yAxisMin, yAxisMax, yAxisTick);

        final BubbleChart<Number,Number> bc = new BubbleChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Not Dryness");
        yAxis.setLabel("Sweetness");
         XYChart.Data<Number,Number> flavorMapPoint = new XYChart.Data<Number,Number>(profile.getMyFlavorMapPoint().getX(),
                        profile.getMyFlavorMapPoint().getY());

        XYChart.Series<Number,Number> flavorPointSeries = new XYChart.Series<Number,Number>();
        flavorPointSeries.getData().add(flavorMapPoint);

        bc.getData().addAll(flavorPointSeries);
        bc.setLegendVisible(false);
        bc.setPrefWidth(500);
        bc.setPrefHeight(500);
        flavorMapAnchorPane.getChildren().add(bc);
    }

    private void setupProfileLabels(){
        favoritedCocktailCountLabel.setText("You have favorited " + profile.getFavouriteCocktails().size() + " cocktails.");
        recommendationRadiusLabel.setText("Recommendation threshold: " + profile.getRecommendationRadius());
        Coordinate flavorMapPoint = profile.getMyFlavorMapPoint();

        DecimalFormat df = new DecimalFormat("#.00");
        flavorPointCoordinateLabel.setText("Coordinates: (" + df.format(flavorMapPoint.getX()) + ", "
                + df.format(flavorMapPoint.getY()) + ")");
    }

    @FXML
    private void handleShowRecommendationsButtonAction(ActionEvent event){
        //go to CocktailList.fxml by recommended parameters;
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("FXML/CocktailList.fxml"));
            root = loader.load();
            stage = (Stage) recommendationRadiusLabel.getScene().getWindow();
            Scene scene = new Scene(root);

            CocktailListController cocktailListController = loader.getController();
            cocktailListController.initializeProfile(profile);
            cocktailListController.updateSearchController(searchController);
            cocktailListController.setCocktailList(searchController.searchByRadius(profile.getMyFlavorMapPoint(),
                    profile.getRecommendationRadius()));

            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("IOException on Search button: " + e.getMessage());
        }
    }

    @FXML
    private void handleRecommendationRadiusDecreaseButtonAction(ActionEvent event){
        logger.info("Clicked less recommendations.");
        profileController.showLessRecommendations();
        recommendationRadiusLabel.setText("Recommendation threshold: " + profile.getRecommendationRadius());
    }

    @FXML
    private void handleRecommendationRadiusIncreaseButtonAction(ActionEvent event){
        logger.info("Clicked more recommendations.");
        profileController.showMoreRecommendations();
        recommendationRadiusLabel.setText("Recommendation threshold: " + profile.getRecommendationRadius());
    }

    private void loadFavoriteCocktails(){
        int rowIndex = 0, colIndex = 0;
        for (Cocktail cocktail : profile.getFavouriteCocktails()) {
            BorderPane borderPane = new BorderPane();
            HBox nameHbox = new HBox();
            ImageView imageView = new ImageView();
            ScrollPane scrollPane = new ScrollPane();
            Label label = new Label();
            borderPane.getStyleClass().add("cocktail-pane");
            borderPane.setId("cocktail-pane");
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

            label.setText(cocktail.getName());
            label.setId("cocktail-name");
            nameHbox.setAlignment(Pos.TOP_CENTER);

            nameHbox.getChildren().add(label);
            borderPane.setBottom(nameHbox);

            borderPane.setPrefHeight(240);
            borderPane.setPrefWidth(240);
            favoriteCocktailsGridPane.add(borderPane, colIndex, rowIndex);

            colIndex++;
            if (colIndex == gridColNumber) {
                colIndex = 0;
                rowIndex++;
            }
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public void initializeProfile(Profile profile) {
        logger.info("initializeProfile " + profile);
        this.profile = profile;
        profileController = new ProfileController(profile);
        setupFlavorMap();
        setupProfileLabels();
        loadFavoriteCocktails();
    }

    public SearchController getSearchController() {
        return searchController;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
