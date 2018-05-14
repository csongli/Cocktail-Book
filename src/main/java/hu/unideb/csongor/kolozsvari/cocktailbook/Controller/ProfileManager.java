package hu.unideb.csongor.kolozsvari.cocktailbook.Controller;

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
