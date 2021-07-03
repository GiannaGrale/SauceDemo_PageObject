package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage {
    private final static String endpoint = "checkout-step-two.html";

    private final static By checkout_Overview_Title_By = By.xpath("//span[@class ='title']");
    private final static By checkout_Finish_By = By.id("finish");

    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getOverviewMessage().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public CheckoutOverviewPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    public WebElement getOverviewMessage() {
        return driver.findElement(checkout_Overview_Title_By);
    }

    public WebElement getFinishButton() {
        return driver.findElement(checkout_Finish_By);
    }

    public String displayOverviewMessage() {
        return getOverviewMessage().getText().toUpperCase();
    }

    public void clickFinishButton() {
        getFinishButton().click();
    }
}

