package pages;

import baseEntities.BasePage;
import core.ReadProperties;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends BasePage {
    private final static String endpoint = "checkout-step-one.html";

    @FindBy(xpath = "//div/span[@class ='title']")
    public WebElement checkoutTitleLabel;

    @FindBy(id = "first-name")
    public WebElement checkoutFirstName;

    @FindBy(id = "last-name")
    public WebElement checkoutLastName;

    @FindBy(id = "postal-code")
    public WebElement checkoutZipCode;

    @FindBy(id = "continue")
    public WebElement checkoutContinueButton;

    @FindBy(className = "error-message-container")
    public WebElement checkoutErrorButton;

    @Override
    protected void openPage() {
        driver.get(ReadProperties.getInstance().getURL()+endpoint);    }

    @Override
    public boolean isPageOpened() {
        try {
            return checkoutTitleLabel.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public CheckoutInfoPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    public String displayErrorButtonMessage() { return checkoutErrorButton.getText(); }

    public CheckoutInfoPage setFirstName(String firstName) {
        pageLogger.debug("Input of first name");
        checkoutFirstName.sendKeys(firstName);
        return this;
    }

    public CheckoutInfoPage setLastName(String lastName) {
        pageLogger.debug("Input of last name");
        checkoutLastName.sendKeys(lastName);
        return this;
    }

    public CheckoutInfoPage setZipCode(String zipCode) {
        pageLogger.debug("Input of zipCode");
        checkoutZipCode.sendKeys(zipCode);
        return this;
    }

    public CheckoutInfoPage checkoutContinueBtnClick() {
        pageLogger.debug("Click checkout continue button and stay on CheckoutInfoPage");
        checkoutContinueButton.click();
        return this;
    }

    public CheckoutOverviewPage successContinueCheckoutBtnClick() {
        pageLogger.debug("Click checkout continue button and go to CheckoutOverviewPage");
        checkoutContinueButton.click();
        return new CheckoutOverviewPage(driver, false);
    }
}

