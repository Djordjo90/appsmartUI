package tester;

import POM.HomePage;
import POM.OrderForm;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import configuration.DriverConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.text.ParseException;

public class OrderFunctionality extends BaseTest {
    HomePage homePage;
    OrderForm orderForm;

    @Test
    public void validateThatPizzaCanBeOrderedForDelivery() throws ParseException {
        ExtentTest test = getExtentReport().createTest("Validate that pizza can be ordered for delivery");

        DriverConfiguration.getInitialPage(driver);
        homePage = new HomePage(this.driver);

        test.log(Status.INFO, "Click on pizza product");
        homePage.clickOnPizzaProducts();
        test.log(Status.INFO, "Order first pizza item from product list");
        homePage.orderFirstProduct();

        orderForm = new OrderForm(this.driver);
        test.log(Status.INFO, "Select first postal code from the list");
        orderForm.selectFirstFromPostalCode();
        test.log(Status.INFO, "Confirm selected postal code and confirm selected product");
        orderForm.clickOnConfirmButton();
        orderForm.clickOnConfirmButton();
        test.pass("Product is selected");

        homePage = new HomePage(this.driver);
        test.log(Status.INFO, "Select product for the minimum price for the delivery");
        homePage.orderMinimumQuantityDeliveryCondition();
        test.log(Status.INFO, "Shop ordered product");
        homePage.clickOnShoppingCartOrderButton();
        test.pass("Product is ordered");

        homePage.fillDeliveryOrderFields();
        test.pass("Required delivery fields are filled");
        homePage.clickOnShoppingCartCheckoutButton();
        test.pass("Successfully checkout order");
        homePage.clickOnShoppingCartPlaceMyOrderButton();
        test.pass("Successfully selected 'Place my order'");

        Assert.assertEquals(homePage.getActualSuccessfullyOrderMessage(), homePage.getExpectedSuccessfullyOrderMessage());
    }

    @Test
    public void validateThatProductCannotBeOrderedForDeliveryIfOrderedPriceIsLessThanMinimum() throws ParseException {
        ExtentTest test = getExtentReport().createTest("Validate that product cannot be ordered for delivery if ordered price is less than minimum");

        DriverConfiguration.getInitialPage(driver);
        homePage = new HomePage(driver);

        test.log(Status.INFO, "Click on pizza product");
        homePage.clickOnPizzaProducts();
        test.log(Status.INFO, "Order first pizza item from product list");
        homePage.orderFirstProduct();

        orderForm = new OrderForm(this.driver);
        test.log(Status.INFO, "Select first postal code from the list");
        orderForm.selectFirstFromPostalCode();
        test.log(Status.INFO, "Confirm selected postal code and confirm selected product");
        orderForm.clickOnConfirmButton();
        orderForm.clickOnConfirmButton();
        test.pass("Product is selected");

        homePage = new HomePage(this.driver);
        Assert.assertTrue(isAttributePresent(homePage.getShoppingCartOrderButton(), "disabled"));
    }

    @Test
    public void validateThatPizzaCanBeOrderedForPickup() {
        ExtentTest test = getExtentReport().createTest("Validate that pizza can be ordered for pickup");

        DriverConfiguration.getInitialPage(driver);
        homePage = new HomePage(driver);

        homePage.setPickupAsDeliveryWay();
        homePage.clickOnPizzaProducts();
        homePage.orderFirstProduct();

        orderForm = new OrderForm(this.driver);
        orderForm.clickOnConfirmButton();

        homePage = new HomePage(this.driver);
        homePage.clickOnShoppingCartOrderButton();

        homePage.fillPickupOrderFields();
        homePage.clickOnShoppingCartCheckoutButton();
        homePage.clickOnShoppingCartPlaceMyOrderButton();

        Assert.assertEquals(homePage.getActualSuccessfullyOrderMessage(), homePage.getExpectedSuccessfullyOrderMessage());
    }

    @AfterMethod
    public void logOut(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @AfterTest
    public void cleanup(){
        getExtentReport().flush();
        driver.quit();
    }
}
