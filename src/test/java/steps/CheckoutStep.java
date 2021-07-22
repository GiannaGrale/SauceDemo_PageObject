package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CheckoutInfoPage;
import pages.CheckoutOverviewPage;
import pages.ShoppingCartPage;


public class CheckoutStep extends BaseStep {
    public CheckoutStep(WebDriver driver) {
        super(driver);
    }
    @Step("Click to proceed with the checkout")
    public void continueCheckoutProcess() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.clickCheckoutButton();
    }
    @Step("Fill in the checkout forms with the following data '{firstNameInput}', '{lastNameInput}', '{zipcodeInput}'")
    public void fillInCheckoutForms(String firstNameInput, String lastNameInput, String zipcodeInput) {
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(driver, true);
        checkoutInfoPage.setFirstNameInput(firstNameInput);
        checkoutInfoPage.setLastNameInput(lastNameInput);
        checkoutInfoPage.setZipcodeInput(zipcodeInput);
        checkoutInfoPage.clickContinueButton();
    }
    @Step("Overview and finish the checkout process")
    public void overviewOrder() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver, false);
        checkoutOverviewPage.clickFinishButton();
    }
}