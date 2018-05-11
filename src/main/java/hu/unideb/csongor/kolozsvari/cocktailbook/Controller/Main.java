package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance.JSONUtils;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;

import java.util.Set;

public class Main extends Application {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public void getjanos(){
        Set<Pair<Ingredient,Quantity>> myset = new HashSet<Pair<Ingredient,Quantity>>();
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
        System.out.println("The deserialized Margarita: " + margarita);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        getjanos();
        //readcocktails;    -- read into cocktail model
        //read ingredients; -- read into ingredeients model
        //profile-t passzolgatjuk
        //ha nem megy static az is
        Parent root = null;
        try {
            root = FXMLLoader.load((getClass().getClassLoader().getResource("FXML/MainWindow.fxml")));
            primaryStage.setTitle("Cocktail Book");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** The main function. */
    public static void main(String[] args) {
        logger.info("App started.");
        launch(args);
    }
}

