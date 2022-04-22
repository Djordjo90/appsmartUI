package configuration;

import java.security.InvalidParameterException;
import java.util.Properties;

public class ConfigurationFileReader extends ConfigurationFileLoader {

    private static Properties properties;
    private static final String BROWSER_NAME_KEY = "browser";
    private static final String BASE_URL = "baseURL";

    private ConfigurationFileReader(){
        super();
        properties = LoadConfigurationFile(new Properties());
    }

    public static void setup(){
        new ConfigurationFileReader();
    }

    private static void checkIfConfigurationKeyExists(String key){
        if(properties.getProperty(key) == null){
            throw new InvalidParameterException(String.format("Not found %s configuration key.", key));
        }
    }

    private static String getConfigurationValueByKey(String key){
        checkIfConfigurationKeyExists(key);
        return properties.getProperty(key) != null ? properties.getProperty(key) : "";
    }

    public static String getBrowserName(){
        return getConfigurationValueByKey(BROWSER_NAME_KEY);
    }

    public static String getBaseURL() {return getConfigurationValueByKey(BASE_URL);}
}
