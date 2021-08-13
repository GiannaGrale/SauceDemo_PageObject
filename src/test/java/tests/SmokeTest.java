package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.CheckoutStep;
import steps.LoginStep;
import steps.OrderStep;

public class SmokeTest extends BaseTest {
    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveLoginTest() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        Assert.assertEquals(new ProductsPage(driver, false).getTitleText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeLoginTest() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getRandomCharsUsername(), properties.getPassword());
        Assert.assertEquals(new LoginPage(driver, false).getErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest1() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getProblemUsername(), properties.getPassword());
        Assert.assertEquals(new ProductsPage(driver, false).getTitleText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest2() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getGlitchUsername(), properties.getPassword());
        Assert.assertTrue(false);
        //Assert.assertEquals(new ProductsPage(driver, false).getTitleText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeHomeTaskLoginTest3() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getLockedUsername(), properties.getPassword());
        Assert.assertEquals(new LoginPage(driver, false).getErrorMessage().getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    //Displayed badge number
    @Feature("Add to cart")
    @Test (description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest1() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        OrderStep orderStep = new OrderStep(driver);
        orderStep.addProduct("Sauce Labs Backpack");
        Assert.assertEquals(new ProductsPage(driver, false).getNumberCartItems().getText(), "1", "The item has not been added");
    }

    //QTY element
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest2() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        OrderStep orderStep = new OrderStep(driver);
        orderStep.addProduct("Sauce Labs Backpack");
        Assert.assertTrue(false);
        //Assert.assertEquals(new ShoppingCartPage(driver, false).displayQuantityText(), "1", "The item has not been added");
    }

    //price check of added item in the cart
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest3() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        OrderStep orderStep = new OrderStep(driver);
        orderStep.addProduct("Sauce Labs Backpack");
        Assert.assertEquals(new ShoppingCartPage(driver, false).displayTextPriceOfAddedItemInCart(), "$29.99", "The item has not been added");
    }

    /*Return to the item and check the "add to cart" button.
    In case the item is removed, the button is changed from "remove" to "add to cart".*/
    @Feature("Remove from cart")
    @Test(description = "Check of product removal from the shopping cart")
    public void positiveHomeTaskRemoveFromCartTest1() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        OrderStep orderStep = new OrderStep(driver);
        orderStep.addProduct("Sauce Labs Backpack");
        orderStep.removeProductAndContinueShopping("Sauce Labs Backpack");
        Assert.assertEquals(new ProductsPage(driver, false).displayAddButtonMessage("Sauce Labs Backpack"), "add to cart");
    }

    //When item is removed, class "removed_cart_item" appears. Here we check its existence.
    @Feature("Remove from cart")
    @Test(description = "Check of product removal from the shopping cart")
    public void positiveHomeTaskRemoveFromCartTest2() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        OrderStep orderStep = new OrderStep(driver);
        orderStep.addProduct("Sauce Labs Backpack");
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        orderStep.removeProduct("Sauce Labs Backpack");
        Assert.assertTrue(shoppingCartPage.getRemovedCartItemIdentifier().isEnabled());
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with valid form data")
    public void positiveHomeTaskCheckoutTest1() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        OrderStep orderStep = new OrderStep(driver);
        orderStep.addProduct("Sauce Labs Backpack");
        CheckoutStep checkOutStep = new CheckoutStep(driver);
        checkOutStep.continueCheckoutProcess();
        checkOutStep.fillInCheckoutForms("Name", "Surname", "00001");
        Assert.assertEquals(new CheckoutOverviewPage(driver, false).displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        checkOutStep.overviewOrder();
        Assert.assertEquals(new CheckoutCompletionPage(driver, false).displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with invalid form data")
    public void negativeHomeTaskCheckoutTest2() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());
        OrderStep orderStep = new OrderStep(driver);
        orderStep.addProduct("Sauce Labs Backpack");
        CheckoutStep checkOutStep = new CheckoutStep(driver);
        checkOutStep.continueCheckoutProcess();
        checkOutStep.fillInCheckoutForms("", "Surname", "00001");
        Assert.assertEquals(new CheckoutInfoPage(driver, false).displayErrorButtonMessage(), "Error: First Name is required");
    }
}
