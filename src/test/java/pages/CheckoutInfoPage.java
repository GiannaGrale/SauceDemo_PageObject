package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutInfoPage extends BasePage {
    private final static String endpoint = "checkout-step-one.html";

    private final static By checkout_Title_Label_By = By.xpath("//div/span[@class ='title']");
    private final static By checkout_First_Name_By = By.id("first-name");
    private final static By checkout_Last_Name_By = By.id("last-name");
    private final static By checkout_ZipCode_By = By.id("postal-code");
    private final static By checkout_Continue_Button_By = By.id("continue");
    private final static By checkout_Error_Button_By = By.className("error-message-container");

    @Override
    protected void openPage() {
        driver.get(properties.getURL()+endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitleMessage().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public CheckoutInfoPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    public WebElement getTitleMessage() {
        return driver.findElement(checkout_Title_Label_By);
    }

    public WebElement getFirstName() {
        return driver.findElement(checkout_First_Name_By);
    }

    public WebElement getLastName() {
        return driver.findElement(checkout_Last_Name_By);
    }

    public WebElement getZipcode() {
        return driver.findElement(checkout_ZipCode_By);
    }

    public WebElement getContinueButton() {
        return driver.findElement(checkout_Continue_Button_By);
    }

    public WebElement getErrorButton(){
        return driver.findElement(checkout_Error_Button_By);
    }

    //atomic methods
    public void setFirstNameInput(String firstNameInput) {
        getFirstName().sendKeys(firstNameInput);
    }

    public void setLastNameInput(String lastNameInput) {
        getLastName().sendKeys(lastNameInput);
    }

    public void setZipcodeInput(String zipcodeInput) {
        getZipcode().sendKeys(zipcodeInput);
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    public String displayErrorButtonMessage(){
        return  getErrorButton().getText();
    }
}