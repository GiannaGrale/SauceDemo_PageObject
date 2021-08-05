package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SmokeTest extends BaseTest {
    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveLoginTest() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword());
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(driver, true)
                .loginWithIncorrectCredentials(properties.getRandomCharsUsername(), properties.getPassword());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest1() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getProblemUsername(), properties.getPassword());
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest2() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getGlitchUsername(), properties.getPassword());
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeHomeTaskLoginTest3() {
        LoginPage loginPage = new LoginPage(driver, true)
                .loginWithIncorrectCredentials(properties.getLockedUsername(), properties.getPassword());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    //Displayed badge number
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest1() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword())
                .orderProduct("Sauce Labs Backpack");
        Assert.assertEquals(productsPage.cartItemNumbers.getText(), "1", "The item has not been added");
    }

    //QTY element
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest2() {
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword())
                .addProduct("Sauce Labs Backpack");
        Assert.assertEquals(shoppingCartPage.cartQuantityIdentifier.getText(), "1", "The item has not been added");
    }

    //price check of added item in the cart
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest3() {
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword())
                .addProduct("Sauce Labs Backpack");
        Assert.assertEquals(shoppingCartPage.displayTextPriceOfAddedItemInCart(), "$29.99", "The item has not been added");
    }

    /*Return to the item and check the "add to cart" button.
    In case the item is removed, the button is changed from "remove" to "add to cart".*/
    @Feature("Remove from cart")
    @Test(description = "Check of product removal from the shopping cart")
    public void positiveHomeTaskRemoveFromCartTest1() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword())
                .addProduct("Sauce Labs Backpack")
                .removeAndBackToShopping("Sauce Labs Backpack");
        Assert.assertEquals(productsPage.displayAddButtonMessage("Sauce Labs Backpack"), "add to cart");
    }

    //When item is removed, class "removed_cart_item" appears. Here we check its existence.
    @Test
    public void positiveHomeTaskRemoveFromCartTest2() {
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword())
                .addProduct("Sauce Labs Backpack")
                .removeProduct("Sauce Labs Backpack");
        Assert.assertTrue(shoppingCartPage.cartRemovedItemIdentifier.isEnabled());
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with valid form data")
    public void positiveHomeTaskCheckoutTest1() {
        CheckoutOverviewPage checkoutOverviewPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword())
                .addProduct("Sauce Labs Backpack")
                .continueCheckoutProcess()
                .fillInFormsWithRightInfo("Name", "Surname", "00001");
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrder();
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with invalid form data")
    public void negativeHomeTaskCheckoutTest2() {
        CheckoutInfoPage checkoutInfoPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(properties.getUsername(), properties.getPassword())
                .addProduct("Sauce Labs Backpack")
                .continueCheckoutProcess()
                .fillInFormsWithWrongInfo("", "Surname", "00001");
        Assert.assertEquals(checkoutInfoPage.displayErrorButtonMessage(), "Error: First Name is required");
    }
}