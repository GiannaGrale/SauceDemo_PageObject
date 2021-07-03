package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckoutCompletionPage extends BasePage {
    private final static String endpoint = "checkout-complete.html";

    private final static By checkout_Completion_By = By.className("complete-header");

    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getCompletionTitle().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public CheckoutCompletionPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    public WebElement getCompletionTitle() {
        return driver.findElement(checkout_Completion_By);
    }

    //atomic methods
    public String displayCompletionMessage() {
        return getCompletionTitle().getText();
    }
}
