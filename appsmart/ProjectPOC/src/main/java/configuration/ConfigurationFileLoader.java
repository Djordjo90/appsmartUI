package configuration;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigurationFileLoader {

    private final Object configurationFileURLObject;
    private static final String configurationFileName = "data.properties";
    private final Object configurationFileObject = getClass().getClassLoader().getResource(configurationFileName);

    public ConfigurationFileLoader(){
        configurationFileURLObject = configurationFileObject != null ? configurationFileObject : new Object();
    }

    public FileInputStream getConfigurationInputStream(){
        try {
            URL configurationFileURL = (URL) configurationFileURLObject;
            return new FileInputStream(configurationFileURL.getPath());
        }catch (FileNotFoundException | ClassCastException exception){
            throw new IllegalArgumentException(String.format("Configuration file '%s' is not found. Stack trace: %s",
                                                                                                                configurationFileName,
                                                                                                                exception.fillInStackTrace()));
        }
    }

    public Properties LoadConfigurationFile(Properties properties){
        try(InputStream stream = getConfigurationInputStream()) {
            properties.load(stream);
        }catch (IOException exception){
            throw new IllegalArgumentException(String.format("Error while loading configuration file. Stack trace: %s", exception.fillInStackTrace()));
        }
        return properties;
    }



}
