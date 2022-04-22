package POM;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import static org.openqa.selenium.support.How.CLASS_NAME;

public class HomePage extends BasePage{

    private static final int MINIMUM_PRICE_ORDER = 11;
    private String deliveryOption = "delivery";
    private Faker faker;
    private String filledEmail;

    @FindBy(how = CLASS_NAME, using = "products-list-container") private WebElement productsParentDiv;

    @FindBy(css="input[placeholder='Product name']")
    private WebElement filterInput;

    @FindBy(xpath="//*[@id=\"category-menu-desktop-hermes-theme\"]/a[16]/span")
    private WebElement pizzaProducts;

    @FindBy(css="button[data-testid='basket-order-btn']")
    private WebElement shoppingCartOrderButton;

    @FindBy(css="button.Button_Button__17EPK:nth-child(2)")
    private WebElement shoppingCartCheckoutButton;

    @FindBy(css="button[class='button-order positive-action']")
    private WebElement shoppingCartPlaceMyOrderButton;

    @FindBy(css="input[placeholder='First name']")
    private WebElement deliveryInformationFirstNameField;

    @FindBy(css="input[placeholder='Last name']")
    private WebElement deliveryInformationLastNameField;

    @FindBy(css="input[placeholder='Street']")
    private WebElement deliveryInformationStreetField;

    @FindBy(css="input[placeholder='Number']")
    private WebElement deliveryInformationHouseNumberField;

    @FindBy(css="input[placeholder='Email']")
    private WebElement deliveryInformationEmailField;

    @FindBy(css="input[placeholder='Phone']")
    private WebElement deliveryInformationPhoneField;

    @FindBy(css="input[placeholder='City']")
    private WebElement deliveryInformationCity;

    @FindBy(css="input[placeholder='Postal Code']")
    private WebElement deliveryInformationPostalCode;

    @FindBy(css="p[data-testid='success-msg-checkout']")
    private WebElement successfullyOrderMessage;

    @FindBy(xpath="//*[@id=\"order-total\"]/div[3]/div[2]")
    private WebElement totalPrice;

    @FindBy(css="div[data-testid='basket-plus']")
    private WebElement addQuantityButton;


    public HomePage(WebDriver driver) {
        super(driver);
        faker = new Faker(Locale.GERMANY);
    }

    public WebElement getShoppingCartOrderButton() {
        return shoppingCartOrderButton;
    }

    public WebElement getShoppingCartCheckoutButton() {
        return shoppingCartCheckoutButton;
    }

    public void orderFirstProduct(){
        By productsByClassName = new By.ByCssSelector("div[class='product-small-picture-wrapper']");

        waitConfigurator.waitElement(productsByClassName, null);
        List<WebElement> allProducts = driver.findElements(productsByClassName);

        WebElement firstProduct = allProducts.stream().findFirst().get();

        firstProduct.click();
    }
    public void clickOnShoppingCartOrderButton(){

        shoppingCartOrderButton.click();
    }

    public void clickOnShoppingCartCheckoutButton(){
        shoppingCartCheckoutButton.click();
    }

    public void clickOnShoppingCartPlaceMyOrderButton(){
        shoppingCartPlaceMyOrderButton.click();
    }

    public void setDeliveryAsDeliveryWay()
    {
        driver.findElement(By.xpath("//span[text()='Delivery']")).click();
        deliveryOption = "delivery";
    }

    public void setPickupAsDeliveryWay()
    {
        driver.findElement(By.xpath("//span[text()='Pickup']")).click();
        deliveryOption = "pickup";
    }

    public void fillDeliveryOrderFields()
    {
        waitConfigurator.waitElement(deliveryInformationFirstNameField, 60);
        filledEmail = faker.internet().emailAddress();

        deliveryInformationFirstNameField.sendKeys(faker.address().firstName());
        deliveryInformationLastNameField.sendKeys(faker.address().lastName());
        deliveryInformationHouseNumberField.sendKeys(faker.address().streetAddressNumber());
        deliveryInformationPhoneField.sendKeys(faker.address().streetAddressNumber());
        deliveryInformationStreetField.sendKeys(faker.address().streetName());
        deliveryInformationEmailField.sendKeys(filledEmail);
    }

    public void fillPickupOrderFields()
    {
        waitConfigurator.waitElement(deliveryInformationFirstNameField, 60);
        filledEmail = faker.internet().emailAddress();

        deliveryInformationFirstNameField.sendKeys(faker.address().firstName());
        deliveryInformationLastNameField.sendKeys(faker.address().lastName());
        deliveryInformationHouseNumberField.sendKeys(faker.address().streetAddressNumber());
        deliveryInformationPhoneField.sendKeys(faker.address().streetAddressNumber());
        deliveryInformationStreetField.sendKeys(faker.address().streetName());
        deliveryInformationPostalCode.sendKeys(faker.address().zipCode());
        deliveryInformationCity.sendKeys(faker.address().cityName());
        deliveryInformationEmailField.sendKeys(filledEmail);
    }

    public String getExpectedSuccessfullyOrderMessage(){
        String message = "Your order has been successfully sent to us! An order confirmation has been sent to the following email address: ";

        return String.format("%s%s",message, filledEmail);
    }

    public String getActualSuccessfullyOrderMessage(){
        waitConfigurator.waitElement(successfullyOrderMessage, 20);
        return successfullyOrderMessage.getText();
    }

    public void filterProduct(String productName){
        filterInput.sendKeys(productName);
    }

    public void clickOnPizzaProducts(){
        pizzaProducts.click();
    }

    public void orderMinimumQuantityDeliveryCondition() throws ParseException {
        DecimalFormat numberFormat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.GERMANY);
        numberFormat.applyPattern("##,#0¤");

        double totalPriceNum = (double) numberFormat.parse(getTotalPriceWithoutSpaces());

        while (MINIMUM_PRICE_ORDER > totalPriceNum){
            addQuantityButton.click();
            totalPriceNum = (double) numberFormat.parse(getTotalPriceWithoutSpaces());
        }
    }

    private String getTotalPriceWithoutSpaces(){
        waitConfigurator.waitElement(totalPrice, 10);
        return totalPrice.getText().replaceAll("\\s+","");
    }
}
