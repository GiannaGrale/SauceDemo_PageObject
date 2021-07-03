package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.CheckoutInfoPage;
import pages.CheckoutOverviewPage;
import pages.ShoppingCartPage;


public class CheckoutStep extends BaseStep {
    public CheckoutStep(WebDriver driver) {
        super(driver);
    }

    public void continueCheckoutProcess() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.clickCheckoutButton();
    }

    public void fillInCheckoutForms(String firstNameInput, String lastNameInput, String zipcodeInput) {
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(driver, true);
        checkoutInfoPage.setFirstNameInput(firstNameInput);
        checkoutInfoPage.setLastNameInput(lastNameInput);
        checkoutInfoPage.setZipcodeInput(zipcodeInput);
        checkoutInfoPage.clickContinueButton();
    }

    public void overviewOrder() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver, false);
        checkoutOverviewPage.clickFinishButton();
    }
}