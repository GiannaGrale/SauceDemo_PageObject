package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import models.Customer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ValueObjectTest extends BaseTest {
    Customer customer = setUpCustomer();

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with invalid form data")
    public void positiveCheckoutTest() {
        CheckoutOverviewPage checkoutOverviewPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(customer.getLogin(), customer.getPassword())
                .addProduct(customer.getProductName())
                .continueCheckoutProcess()
                .fillInFormsWithRightInfo(customer.getFirstName(), customer.getLastName(), customer.getZipcode());
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrder();
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    private Customer setUpCustomer() {
        Customer customer = new Customer();
        customer.setLogin("standard_user");
        customer.setPassword("secret_sauce");
        customer.setProductName("Sauce Labs Backpack");
        customer.setFirstName("Anna");
        customer.setLastName("Surname");
        customer.setZipcode("19732");
        return customer;
    }
}

