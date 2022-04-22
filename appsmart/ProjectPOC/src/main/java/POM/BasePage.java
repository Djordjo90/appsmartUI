package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public WebDriver driver;
    WaitConfigurator waitConfigurator;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitConfigurator = new WaitConfigurator(driver);
        PageFactory.initElements(driver, this);
    }
}
