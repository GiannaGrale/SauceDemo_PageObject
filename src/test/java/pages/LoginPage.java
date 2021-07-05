package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    //selectors
    private final static By username_Input_By = By.id("user-name");
    private final static By password_Input_By = By.id("password");
    private final static By login_Button_By = By.id("login-button");
    private final static By error_Message_By = By.tagName("h3");

    //constructors
    public LoginPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    @Override
    protected void openPage() {
       driver.get(properties.getURL());
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getLoginButton().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    //getters
    public WebElement getUsernameInput() {
        return driver.findElement(username_Input_By);
    }

    public WebElement getPasswordInput() {
        return driver.findElement(password_Input_By);
    }

    public WebElement getLoginButton() {
        return driver.findElement(login_Button_By);
    }

    public WebElement getErrorMessage() {
        return driver.findElement(error_Message_By);
    }

    //atomic methods with elements
    public void setUsername(String text) {
        getUsernameInput().sendKeys(text);
    }

    public void setPassword(String text) {
        getPasswordInput().sendKeys(text);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }
}
