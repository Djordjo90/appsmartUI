package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements Browser {

    private final String browserName = "firefox";

    @Override
    public String getBrowserName() {
        return browserName;
    }

    @Override
    public WebDriver setWebDriver() {
        return new FirefoxDriver();
    }

}
