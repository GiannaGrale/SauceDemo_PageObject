package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
    private final static String endpoint = "checkout-step-two.html";

    @FindBy(xpath ="//span[@class ='title']")
    public WebElement checkoutOverviewTitle;

    @FindBy(id = "finish")
    public WebElement checkoutFinish;

    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return checkoutOverviewTitle.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public CheckoutOverviewPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
        pageLogger.debug("The constructor worked successfully...");
    }

    public String displayOverviewMessage() { return checkoutOverviewTitle.getText().toUpperCase(); }

    public CheckoutCompletionPage overviewOrder (){
        pageLogger.debug("Initialization of overviewOrder");
        checkoutFinish.click();
        pageLogger.debug("Finish of overviewOrder");
        return  new CheckoutCompletionPage (driver, true);
    }
}
