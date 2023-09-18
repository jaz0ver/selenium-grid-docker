package framework.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

        static FileReader configFile;
        static Properties prop = new Properties();

    public static String getProperty(String key) {
        String val = "";
        try {
            configFile = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\config\\config.properties");
            prop.load(configFile);
            val =  prop.getProperty(key);
            configFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }
    
}
