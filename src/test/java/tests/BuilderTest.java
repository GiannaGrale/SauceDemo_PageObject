package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import models.LoginBuilder;

import models.CustomerBuilder;
import models.ProductBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutCompletionPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;

public class BuilderTest extends BaseTest {

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with valid form data")
    public void positiveHomeTaskCheckoutTest() {
       LoginBuilder newLogin = setUpLogin();
       ProductBuilder newProduct = setUpProduct();
       CustomerBuilder newCustomer = setUpCustomer();

        CheckoutOverviewPage checkoutOverviewPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(newLogin.getLogin(), newLogin.getPassword())
                .addProduct(newProduct.getProductName())
                .continueCheckoutProcess()
                .fillInFormsWithRightInfo(newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getZipcode());
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrder();
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    private LoginBuilder setUpLogin (){
        LoginBuilder newLogin = LoginBuilder.builder()
                .login("standard_user")
                .password("secret_sauce")
                .build();
        return newLogin;
    }

    private ProductBuilder setUpProduct () {
        ProductBuilder product = ProductBuilder.builder()
                .productName("Sauce Labs Backpack")
                .build();
        return product;
    }

    private CustomerBuilder setUpCustomer () {
        CustomerBuilder newCustomer = CustomerBuilder.builder()
                .firstName("Anna")
                .lastName("Surname")
                .zipcode("23456")
                .build();
        return  newCustomer;
    }
}
