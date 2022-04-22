package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements Browser{

    private final String browserName = "chrome";

    @Override
    public WebDriver setWebDriver() {
        return new ChromeDriver();
    }

    @Override
    public String getBrowserName(){
        return browserName;
    }
}
