package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperty {
    Properties prop = new Properties();
    InputStream input = null;

    public String getProperty (String propertyName){
        try {
            input = new FileInputStream("src/test/resources/config.properties");
            prop.load(input);
            return prop.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
