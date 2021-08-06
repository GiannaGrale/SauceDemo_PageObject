package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(tagName = "h3")
    public WebElement errorMessage;

    public LoginPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
        pageLogger.debug("The constructor worked successfully...");
    }

    @Override
    protected void openPage() {
        driver.get(properties.getURL());
    }

    @Override
    public boolean isPageOpened() {
        try {
            return loginButton.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public LoginPage loginWithIncorrectCredentials(String username, String password){
        pageLogger.debug("initialization of loginWithIncorrectCredentials");
        pageLogger.debug("Set login " + username);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        pageLogger.debug("Finish of loginWithIncorrectCredentials");
        return new LoginPage(driver, false);
    }

    public ProductsPage loginWithCorrectCredentials (String username, String password){
        pageLogger.debug("initialization of loginWithIncorrectCredentials");
        pageLogger.debug("Set login " + username);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        pageLogger.debug("Finish of loginWithIncorrectCredentials");
        return new ProductsPage(driver, true);
    }

}