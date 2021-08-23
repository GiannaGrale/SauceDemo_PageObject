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
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick();
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(driver, true)
                .setLogin(login.getRandomCharsUser())
                .setPassword(login.getPassword())
                .loginBtnClick();
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest1() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .setLogin(login.getProblemUser())
                .setPassword(login.getPassword())
                .successLoginBtnClick();
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest2() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .setLogin(login.getGlitchUser())
                .setPassword(login.getPassword())
                .successLoginBtnClick();
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeHomeTaskLoginTest3() {
        LoginPage loginPage = new LoginPage(driver, true)
                .setLogin(login.getLockedUser())
                .setPassword(login.getPassword())
                .loginBtnClick();
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    //Displayed badge number
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest1() {
        ProductsPage productsPage= new LoginPage(driver, true)
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick()
                .addProductToCart(product.getProductName())
                .observeCartOnProductPage();
        Assert.assertEquals(productsPage.cartItemNumbers.getText(), "1", "The item has not been added");
    }

    //QTY element
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest2() {
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick()
                .addProductToCart(product.getProductName())
                .goToTheCart();
        Assert.assertEquals(shoppingCartPage.cartQuantityIdentifier.getText(), "1", "The item has not been added");
    }

    //price check of added item in the cart
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest3() {
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick()
                .addProductToCart(product.getProductName())
                .goToTheCart();
        Assert.assertEquals(shoppingCartPage.displayTextPriceOfAddedItemInCart(), "$29.99", "The item has not been added");
    }

    /*Return to the item and check the "add to cart" button.
    In case the item is removed, the button is changed from "remove" to "add to cart".*/
    @Feature("Remove from cart")
    @Test(description = "Check of product removal from the shopping cart")
    public void positiveHomeTaskRemoveFromCartTest1() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick()
                .addProductToCart(product.getProductName())
                .goToTheCart()
                .removeProduct(product.getProductName())
                .goBackToShopping();
        Assert.assertEquals(productsPage.displayAddButtonMessage("Sauce Labs Backpack"), "add to cart");
    }

    //When item is removed, class "removed_cart_item" appears. Here we check its existence.
    @Test
    public void positiveHomeTaskRemoveFromCartTest2() {
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick()
                .addProductToCart(product.getProductName())
                .goToTheCart()
                .removeProduct(product.getProductName());
        Assert.assertTrue(shoppingCartPage.cartRemovedItemIdentifier.isEnabled());
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with valid form data")
    public void positiveHomeTaskCheckoutTest1() {
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
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrderBtnClick();
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with invalid form data")
    public void negativeHomeTaskCheckoutTest2() {
        CheckoutInfoPage checkoutInfoPage = new LoginPage(driver, true)
                .setLogin(login.getLogin())
                .setPassword(login.getPassword())
                .successLoginBtnClick()
                .addProductToCart(product.getProductName())
                .goToTheCart()
                .continueCheckoutBtnClick()
                .setFirstName(newCustomer.getEmptyFirstName())
                .setLastName(newCustomer.getLastName())
                .setZipCode(newCustomer.getZipcode())
                .checkoutContinueBtnClick();
        Assert.assertEquals(checkoutInfoPage.displayErrorButtonMessage(), "Error: First Name is required");
    }
}