package browser;

import configuration.ConfigurationFileReader;
import org.openqa.selenium.WebDriver;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BrowserCollector {
    private static final Stream<Browser> browsers = Stream.of(new ChromeBrowser(), new FirefoxBrowser());

    private BrowserCollector(){
    }

    public static WebDriver getDriver(){

        String configurationBrowserName = ConfigurationFileReader.getBrowserName();
        Predicate<Browser> predicate = browser -> configurationBrowserName.equalsIgnoreCase(browser.getBrowserName());

        Browser browserObject = browsers
                                        .filter(predicate)
                                        .findFirst()
                                        .orElseThrow(()  -> new IllegalStateException(String.format("Unsupported browser type %s.", configurationBrowserName)));
        return browserObject.setWebDriver();
    }
}
