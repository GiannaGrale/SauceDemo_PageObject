package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import models.SauceValue;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ValueObjectTest extends BaseTest {
    SauceValue sauceDemo = setUpSauceDemo();

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with invalid form data")
    public void positiveCheckoutTest() {
        CheckoutOverviewPage checkoutOverviewPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .addProduct(sauceDemo.getProductName())
                .continueCheckoutProcess()
                .fillInFormsWithRightInfo(sauceDemo.getFirstName(), sauceDemo.getLastName(), sauceDemo.getZipcode());
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrder();
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    private SauceValue setUpSauceDemo (){
        SauceValue sauceValue = new SauceValue();
        sauceValue.setRightLogin("standard_user");
        sauceValue.setProblemLogin("problem_user");
        sauceValue.setLockedLogin("locked_out_user");
        sauceValue.setRandomCharsLogin("incorrect_user");
        sauceValue.setGlitchLogin("performance_glitch_user");
        sauceValue.setPassword("secret_sauce");
        sauceValue.setProductName("Sauce Labs Backpack");
        sauceValue.setFirstName("Anna");
        sauceValue.setNullName("");
        sauceValue.setLastName("Surname");
        sauceValue.setZipcode("19732");
        return sauceValue;
    }
}

