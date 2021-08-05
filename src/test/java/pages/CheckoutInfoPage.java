package pages;

import baseEntities.BasePage;
import models.Customer;
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
    }

    public String displayErrorButtonMessage() { return checkoutErrorButton.getText(); }

    public CheckoutOverviewPage fillInFormsWithRightInfo(String firstName, String lastName, String zipCode){
        checkoutFirstName.sendKeys(firstName);
        checkoutLastName.sendKeys(lastName);
        checkoutZipCode.sendKeys(zipCode);
        checkoutContinueButton.click();
        return  new  CheckoutOverviewPage (driver, true);
    }


    public CheckoutInfoPage fillInFormsWithWrongInfo(String firstName, String lastName, String zipCode){
        checkoutFirstName.sendKeys(firstName);
        checkoutLastName.sendKeys(lastName);
        checkoutZipCode.sendKeys(zipCode);
        checkoutContinueButton.click();
        return  this;
    }
    public CheckoutInfoPage fillInForms(Customer customer){
        checkoutFirstName.sendKeys(customer.getFirstName());
        checkoutLastName.sendKeys(customer.getLastName());
        checkoutZipCode.sendKeys(customer.getZipcode());
        checkoutContinueButton.click();
        return  this;
    }
}

