package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

import hu.unideb.csongor.kolozsvari.cocktailbook.Controller.Persistance.JSONUtils;
import hu.unideb.csongor.kolozsvari.cocktailbook.Model.Profile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Saves and loads profile information
 */
//todo javadoc
public class ProfileManager {

    //data/saveData.json
    public void saveProfile(Profile profile, File file){
        JSONUtils jsonUtils = new JSONUtils();
        String jsonToWrite = jsonUtils.serializeProfile(profile);

        try {
            FileUtils.writeStringToFile(file, jsonToWrite, StandardCharsets.UTF_8, false);
        } catch (IOException e) {
            //logger.warn("IOException occured while writing: {}", file.getPath());
            //logger.warn(e.getMessage());
        }
    }

    //belepsz kodba
    public void loadProfile(File file){

    }



}
