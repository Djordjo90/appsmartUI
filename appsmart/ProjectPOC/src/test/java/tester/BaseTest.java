package tester;

import browser.BrowserCollector;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import configuration.DriverConfiguration;
import configuration.ConfigurationFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    private static final String reportPath = String.format("%s%s",System.getProperty("user.dir"), "//reports//index.html") ;

    private ExtentReports extentReport;
    private ExtentSparkReporter extentSparkReporter;

    public WebDriver driver;

    @BeforeTest
    public void setup() { driver = getDriver();
        DriverConfiguration.maximizeWindow(driver);

        extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setReportName("Order Tests Report");
        extentSparkReporter.config().setDocumentTitle("Test Results");

        extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
    }

    private WebDriver getDriver() {
        ConfigurationFileReader.setup();
        return BrowserCollector.getDriver();
    }

    public ExtentReports getExtentReport() {
        return extentReport;
    }

    public boolean isAttributePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {}

        return result;
    }
}
