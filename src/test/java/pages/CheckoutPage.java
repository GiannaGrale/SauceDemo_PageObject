package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckoutPage extends BasePage {
    private final static By checkout_Title_Label_By = By.xpath("//div/span[@class ='title']");
    private final static By checkout_First_Name_By = By.id("first-name");
    private final static By checkout_Last_Name_By = By.id("last-name");
    private final static By checkout_ZipCode_By = By.id("postal-code");
    private final static By checkout_Continue_Button_By = By.id("continue");
    private final static By checkout_Error_Button_By = By.className("error-message-container");
    private final static By checkout_Overview_Title_By = By.xpath("//span[@class ='title']");
    private final static By checkout_Completion_By = By.className("complete-header");
    private final static By checkout_Finish_By = By.id("finish");


    @Override
    protected void openPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitleMessage().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public CheckoutPage(WebDriver driver, boolean openPageByURL) {
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

    public WebElement getOverviewMessage() {
        return driver.findElement(checkout_Overview_Title_By);
    }

    public WebElement getFinishButton(){
        return driver.findElement(checkout_Finish_By);
    }

    public WebElement getCompletionTitle(){
        return driver.findElement(checkout_Completion_By);
    }

    public WebElement getErrorButton(){
        return driver.findElement(checkout_Error_Button_By);
    }

    //atomic methods
    public void setFirstNameInput() {
        getFirstName().sendKeys("Name");
    }

    public void setLastNameInput() {
        getLastName().sendKeys("Surname");
    }

    public void setZipcodeInput() {
        getZipcode().sendKeys("00001");
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    public String displayOverviewMessage() {
        return getOverviewMessage().getText().toUpperCase();
    }

    public void clickFinishButton(){
         getFinishButton().click();
    }

    public String displayCompletionMessage(){
       return getCompletionTitle().getText();
    }

    public String displayErrorButtonMessage(){
        return  getErrorButton().getText();
    }
}
