package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep extends BaseStep {

    public LoginStep(WebDriver driver) {
        super(driver);
    }

    @Step("Log into system with credentials '{username}', '{password}'")
    public void loginWithCorrectCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Step
    public void loginWithInCorrectCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }
}