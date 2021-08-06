package pages;

import baseEntities.BasePage;
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
        driver.get(properties.getURL() + endpoint);
    }

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
        pageLogger.debug("The constructor worked successfully...");
    }

    public String displayErrorButtonMessage() { return checkoutErrorButton.getText(); }

    public CheckoutOverviewPage fillInFormsWithRightInfo(String firstName, String lastName, String zipCode){
        pageLogger.debug("Initialization of fillInFormsWithRightInfo");
        pageLogger.debug("Input of "+ firstName);
        checkoutFirstName.sendKeys(firstName);
        pageLogger.debug("Input of "+ lastName);
        checkoutLastName.sendKeys(lastName);
        pageLogger.debug("Input of "+ zipCode);
        checkoutZipCode.sendKeys(zipCode);
        checkoutContinueButton.click();
        pageLogger.debug("Finish of fillInFormsWithRightInfo");
        return new CheckoutOverviewPage (driver, true);
    }

    public CheckoutInfoPage fillInFormsWithWrongInfo(String firstName, String lastName, String zipCode){
        pageLogger.debug("Initialization of fillInFormsWithWrongInfo");
        pageLogger.debug("Input of "+ firstName);
        checkoutFirstName.sendKeys(firstName);
        pageLogger.debug("Input of "+ lastName);
        checkoutLastName.sendKeys(lastName);
        pageLogger.debug("Input of "+ zipCode);
        checkoutZipCode.sendKeys(zipCode);
        checkoutContinueButton.click();
        pageLogger.debug("Finish of fillInFormsWithWrongInfo");
        return this;
    }
}

