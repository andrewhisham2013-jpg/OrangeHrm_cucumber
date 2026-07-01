package tests.reuse;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
    Properties properties;
    public ConfigManager(String path) {
        properties = new Properties();
        try
        {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getValue(String key){
        return properties.getProperty(key);
    }
}
