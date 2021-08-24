package tests.gui;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import models.CustomerBuilder;
import models.LoginBuilder;
import models.ProductBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CheckoutCompletionPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;

public class BuilderTest extends BaseTest {

    @BeforeTest
    public void prepareData() {
        login = LoginBuilder.builder()
                .login("standard_user")
                .glitchUser("performance_glitch_user")
                .lockedUser("locked_out_user")
                .randomCharsUser("wek3elk")
                .problemUser("problem_user")
                .password("secret_sauce")
                .build();


        product = ProductBuilder.builder()
                .productName("Sauce Labs Backpack")
                .build();


        newCustomer = CustomerBuilder.builder()
                .firstName("Name")
                .emptyFirstName("")
                .lastName("Surname")
                .zipcode("294706")
                .build();
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with valid form data")
    public void positiveHomeTaskCheckoutTest() {

        logger.info("positiveHomeTaskCheckoutTest1 is started...");
        CheckoutOverviewPage checkoutOverviewPage = new LoginPage(driver, true)
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick()
                .addProductToCart(product.getProductName())
                .goToTheCart()
                .continueCheckoutBtnClick()
                .setFirstName(newCustomer.getFirstName())
                .setLastName(newCustomer.getLastName())
                .setZipCode(newCustomer.getZipcode())
                .successContinueCheckoutBtnClick();
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrder();
        logger.info("positiveHomeTaskCheckoutTest1 is finished...");
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }
}
