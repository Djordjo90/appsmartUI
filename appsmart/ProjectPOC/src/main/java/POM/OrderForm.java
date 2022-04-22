package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class OrderForm extends BasePage{

    @FindBy(css="button[type='submit']")
    WebElement confirmButton;

    @FindBy(css="input[placeholder='Postal Code']")
    WebElement postalCode;

    public OrderForm(WebDriver driver) {
        super(driver);
    }

    public void clickOnConfirmButton(){

        waitConfigurator.waitElement(new By.ByCssSelector("button[type='submit']"), null);
        confirmButton.click();
    }

    public void selectFirstFromPostalCode()
    {
        By selectElement = new By.ByXPath("//*[@id=\"right-side-block\"]/div[6]/div/div/form/label/div/div/div[2]/ul/li");

        waitConfigurator.waitElement(postalCode, 10);
        postalCode.click();

        waitConfigurator.waitElement(selectElement, 5);

        List<WebElement> productCodes = driver.findElements(selectElement);

        productCodes.get(0).click();
    }
}
