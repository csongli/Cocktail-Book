package hu.unideb.csongor.kolozsvari.cocktailbook.View;

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.ProfileController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.SearchController;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Coordinate;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        logger.info("setProfile " + profile);
        this.profile = profile;
        profileController = new ProfileController(profile);
        setupFlavorMap();
        setupProfileLabels();
    }

    public SearchController getSearchController() {
        return searchController;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    public static final Logger logger = LoggerFactory.getLogger(ProfileViewController.class);

    private final int gridColNumber = 3;

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
            mainWindowController.setProfile(profile);
            mainWindowController.setSearchController(searchController);

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
            cocktailListController.setProfile(profile);
            cocktailListController.setSearchController(searchController);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
