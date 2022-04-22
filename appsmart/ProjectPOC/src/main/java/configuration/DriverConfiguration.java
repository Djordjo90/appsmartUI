package configuration;

import org.openqa.selenium.WebDriver;

public class DriverConfiguration {

    public static final String INIT_PAGE = ConfigurationFileReader.getBaseURL();

    public static void maximizeWindow(WebDriver driver){
        driver.manage().window().maximize();
    }

    public static void getInitialPage(WebDriver driver){
        driver.get(INIT_PAGE);
    }
}
