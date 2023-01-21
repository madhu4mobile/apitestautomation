package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager manager;
    private static final Properties prop = new Properties();

    //constructor
    private ConfigManager() throws IOException {
        InputStream inputStream = ConfigManager.class.getResourceAsStream("/config.properties");
        prop.load(inputStream);
    }

    // manager object is only get initiated onlcy when manager is not empty.
    public static ConfigManager getInstance() {
        if (manager == null) {
            synchronized (ConfigManager.class) {
                try {
                    manager = new ConfigManager();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return manager;
    }
    public String getString(String key){
        return System.getProperty(key,prop.getProperty(key));
    }

    public static void main(String[] args) { System.out.println(ConfigManager.getInstance().getString("baseUrl"));}
}
