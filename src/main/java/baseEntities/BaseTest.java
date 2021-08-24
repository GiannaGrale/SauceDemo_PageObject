package baseEntities;

import core.BrowserService;
import core.ReadProperties;

import models.CustomerBuilder;
import models.LoginBuilder;
import models.ProductBuilder;
import models.SauceValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import utils.Listener;


@Listeners(Listener.class)
public class BaseTest {
    public WebDriver driver;
    protected ReadProperties properties;
    protected final Logger logger = LogManager.getLogger(this);
    public ProductBuilder product;
    public CustomerBuilder newCustomer;
    public LoginBuilder login;


    @BeforeSuite
    protected SauceValue setUpSauceDemo(){
        SauceValue sauceValue = new SauceValue();
        sauceValue.setRightLogin("standard_user");
        sauceValue.setProblemLogin("problem_user");
        sauceValue.setLockedLogin("locked_out_user");
        sauceValue.setRandomCharsLogin("incorrect_user");
        sauceValue.setGlitchLogin("performance_glitch_user");
        sauceValue.setPassword("secret_sauce");
        sauceValue.setProductName("Sauce Labs Backpack");
        sauceValue.setFirstName("Anna");
        sauceValue.setNullName("");
        sauceValue.setLastName("Surname");
        sauceValue.setZipcode("19732");
        return sauceValue;
    }

    @BeforeTest
    public void setupTest() {
        properties = ReadProperties.getInstance();
    }

    @BeforeMethod
    public void setupMethod() {
        driver = new BrowserService().getDriver();
    }

    @AfterMethod
    public void teardownMethod() {
        driver.quit();
    }
}