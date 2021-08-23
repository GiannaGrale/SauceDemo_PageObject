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
                .setLogin(customer.getLogin())
                .setPassword(customer.getPassword())
                .successLoginBtnClick()
                .addProductToCart(customer.getProductName())
                .goToTheCart()
                .continueCheckoutBtnClick()
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setZipCode(customer.getZipcode())
                .successContinueCheckoutBtnClick();
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrderBtnClick();
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

