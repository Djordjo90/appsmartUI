package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitConfigurator{
    WebDriver driver;
    public WaitConfigurator(WebDriver driver){
        this.driver = driver;
    }
    public void waitElement(By locator, Integer seconds){
        seconds = seconds != null ? seconds : 30;

        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until((ExpectedConditions.presenceOfElementLocated(locator)));
    }

    public void waitElement(WebElement element, Integer seconds){
        seconds = seconds != null ? seconds : 30;
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(element));
    }
}
