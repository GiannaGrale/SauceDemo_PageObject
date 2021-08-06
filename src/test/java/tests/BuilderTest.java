package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import models.Login;

import models.NewCustomer;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutCompletionPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;

public class BuilderTest extends BaseTest {

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with valid form data")
    public void positiveHomeTaskCheckoutTest() {
        Login login = Login.newBuilder()
                .withLogin("standard_user")
                .withPassword("secret_sauce")
                .build();

        Product product = Product.newBuilder()
                .withProduct("Sauce Labs Backpack")
                .build();

        NewCustomer newCustomer = NewCustomer.newBuilder()
                .withFirstName("Name")
                .withLastName("Surname")
                .withZipcode("34567")
                .build();

        CheckoutOverviewPage checkoutOverviewPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(login.getLogin(), login.getPassword())
                .addProduct(product.getProductName())
                .continueCheckoutProcess()
                .fillInFormsWithRightInfo(newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getZipcode());
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrder();
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }
}
