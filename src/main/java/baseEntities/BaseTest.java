package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import models.Login;
import models.NewCustomer;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.Listener;

@Listeners(Listener.class)
public class BaseTest {
    public WebDriver driver;
    protected ReadProperties properties;
    public Product product;
    public NewCustomer newCustomer;
    public Login login;

    @BeforeSuite
    public void prepareData() {
        login = Login.newBuilder()
                .withLogin("standard_user")
                .withGlitchLogin("performance_glitch_user")
                .withLockedLogin("locked_out_user")
                .withRandomCharsLogin("wek3elk")
                .withProblemLogin("problem_user")
                .withPassword("secret_sauce")
                .build();
        product = Product.newBuilder()
                .withProduct("Sauce Labs Backpack")
                .build();
        newCustomer = NewCustomer.newBuilder()
                .withFirstName("Name")
                .withEmptyFirstName("")
                .withLastName("Surname")
                .withZipcode("00001")
                .build();
    }

    @BeforeTest
    public void setupTest() {
        properties = new ReadProperties();
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