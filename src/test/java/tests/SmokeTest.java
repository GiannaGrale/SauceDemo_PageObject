package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.ShoppingCartPage;

public class SmokeTest extends BaseTest {

    @Test
    public void positiveLoginTest() {

        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "The page has not opened");
    }

    @Test
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("saded");
        loginPage.setPassword("adedxdewd");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void positiveHomeTaskLoginTest1() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("problem_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "The page has not opened");
    }

    @Test
    public void positiveHomeTaskLoginTest2() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("performance_glitch_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "The page has not opened");
    }

    @Test
    public void negativeHomeTaskLoginTest3() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("locked_out_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage().getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    //Displayed badge number
    @Test
    public void positiveHomeTaskAddToCartTest1() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.addItemToCart();
        Assert.assertEquals(productsPage.getNumberCartItems().getText(), "1", "The item has not been added");
    }

    //QTY element
    @Test
    public void positiveHomeTaskAddToCartTest2() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.addItemToCart();
        productsPage.clickCartBadge();
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver, false);
        Assert.assertEquals(shoppingCart.displayQuantityText(), "1", "The item has not been added");
    }

    //price check of added item in the cart
    @Test
    public void positiveHomeTaskAddToCartTest3() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.addItemToCart();
        productsPage.clickCartBadge();
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver, false);
        Assert.assertEquals(shoppingCart.displayTextPriceOfAddedItemInCart(), "$29.99", "The item has not been added");
    }

    /*Return to the item and check the "add to cart" button.
    In case the item is removed, the button is changed from "remove" to "add to cart".*/
    @Test
    public void positiveHomeTaskRemoveFromCartTest1() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.addItemToCart();
        productsPage.clickCartBadge();
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver, false);
        shoppingCart.clickRemoveButton();
        shoppingCart.clickContinueShoppingButton();
        Assert.assertEquals(productsPage.displayAddButtonMessage(), "add to cart");
    }

    //When item is removed, class "removed_cart_item" appears. Here we check its existence.
    @Test
    public void positiveHomeTaskRemoveFromCartTest2() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.addItemToCart();
        productsPage.clickCartBadge();
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver, false);
        shoppingCart.clickRemoveButton();
        Assert.assertTrue(shoppingCart.getRemovedCartItemIdentifier().isEnabled());
    }

    @Test
    public void positiveHomeTaskCheckoutTest1() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.addItemToCart();
        productsPage.clickCartBadge();
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver, false);
        shoppingCart.clickCheckoutButton();
        CheckoutPage checkoutPage = new CheckoutPage(driver, false);
        checkoutPage.setFirstNameInput();
        checkoutPage.setLastNameInput();
        checkoutPage.setZipcodeInput();
        checkoutPage.clickContinueButton();
        Assert.assertEquals(checkoutPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        checkoutPage.clickFinishButton();
        Assert.assertEquals(checkoutPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    @Test
    public void negativeHomeTaskCheckoutTest2() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.addItemToCart();
        productsPage.clickCartBadge();
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver, false);
        shoppingCart.clickCheckoutButton();
        CheckoutPage checkoutPage = new CheckoutPage(driver, false);
        checkoutPage.getFirstName().sendKeys("");
        checkoutPage.setLastNameInput();
        checkoutPage.setZipcodeInput();
        checkoutPage.clickContinueButton();
        Assert.assertEquals(checkoutPage.displayErrorButtonMessage(), "Error: First Name is required");
    }
}


