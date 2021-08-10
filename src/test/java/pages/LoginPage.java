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

    public LoginPage setLogin(String username){
        pageLogger.debug("Login input");
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password){
        pageLogger.debug("Password input");
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage loginBtnClick(){
        pageLogger.debug("Click login button and stay on LoginPage ");
        loginButton.click();
        return this;
    }

    public ProductsPage successLoginBtnClick(){
        pageLogger.debug("Click login button and go to on ProductsPage ");
        loginBtnClick();
        return new ProductsPage(driver, false);
    }
}