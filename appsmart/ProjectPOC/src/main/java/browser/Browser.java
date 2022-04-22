package browser;

import org.openqa.selenium.WebDriver;

public interface Browser {
    String getBrowserName();
    WebDriver setWebDriver();
}
