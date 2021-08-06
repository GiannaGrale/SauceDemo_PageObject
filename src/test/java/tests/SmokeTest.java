package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Feature;
import models.SauceValue;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SmokeTest extends BaseTest {
    SauceValue sauceDemo = setUpSauceDemo();

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveLoginTest() {
        logger.info("positiveLoginTest is started...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword());
        logger.info("positiveLoginTest is finished...");
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeLoginTest() {
        logger.info("negativeLoginTest is started...");
        LoginPage loginPage = new LoginPage(driver, true)
                .loginWithIncorrectCredentials(sauceDemo.getRandomCharsLogin(), sauceDemo.getPassword());
        logger.info("negativeLoginTest is finished...");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest1() {
        logger.info("positiveHomeTaskLoginTest1 is started...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getProblemLogin(), sauceDemo.getPassword());
        logger.info("positiveHomeTaskLoginTest1 is finished...");
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with valid credentials")
    public void positiveHomeTaskLoginTest2() {
        logger.info("positiveHomeTaskLoginTest2 is started...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getGlitchLogin(), sauceDemo.getPassword());
        logger.info("positiveHomeTaskLoginTest2 is finished...");
        Assert.assertEquals(productsPage.titleLabel.getText(), "PRODUCTS", "The page has not opened");
    }

    @Feature("Login")
    @Test(description = "Log in with invalid credentials")
    public void negativeHomeTaskLoginTest3() {
        logger.info("negativeHomeTaskLoginTest3 is started...");
        LoginPage loginPage = new LoginPage(driver, true)
                .loginWithIncorrectCredentials(sauceDemo.getLockedLogin(), sauceDemo.getPassword());
        logger.info("negativeHomeTaskLoginTest3 is finished...");
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    //Displayed badge number
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest1() {
        logger.info("positiveHomeTaskAddToCartTest1 is started...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .orderProduct(sauceDemo.getProductName());
        logger.info("positiveHomeTaskAddToCartTest1 is finished...");
        Assert.assertEquals(productsPage.cartItemNumbers.getText(), "1", "The item has not been added");
    }

    //QTY element
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest2() {
        logger.info("positiveHomeTaskAddToCartTest2 is started...");
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .addProduct(sauceDemo.getProductName());
        logger.info("positiveHomeTaskAddToCartTest2 is finished...");
        Assert.assertEquals(shoppingCartPage.cartQuantityIdentifier.getText(), "1", "The item has not been added");
    }

    //price check of added item in the cart
    @Feature("Add to cart")
    @Test(description = "Check of product addition to the shopping cart")
    public void positiveHomeTaskAddToCartTest3() {
        logger.info("positiveHomeTaskAddToCartTest3 is started...");
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .addProduct(sauceDemo.getProductName());
        logger.info("positiveHomeTaskAddToCartTest3 is finished...");
        Assert.assertEquals(shoppingCartPage.displayTextPriceOfAddedItemInCart(), "$29.99", "The item has not been added");
    }

    /*Return to the item and check the "add to cart" button.
    In case the item is removed, the button is changed from "remove" to "add to cart".*/

    @Feature("Remove from cart")
    @Test(description = "Check of product removal from the shopping cart")
    public void positiveHomeTaskRemoveFromCartTest1() {
        logger.info("positiveHomeTaskRemoveFromCartTest1 is started...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .addProduct(sauceDemo.getProductName())
                .removeAndBackToShopping(sauceDemo.getProductName());
        logger.info("positiveHomeTaskRemoveFromCartTest1 is finished...");
        Assert.assertEquals(productsPage.displayAddButtonMessage(sauceDemo.getProductName()), "add to cart");
    }

    //When item is removed, class "removed_cart_item" appears. Here we check its existence.
    @Test
    public void positiveHomeTaskRemoveFromCartTest2() {
        logger.info("positiveHomeTaskRemoveFromCartTest2 is started...");
        ShoppingCartPage shoppingCartPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .addProduct(sauceDemo.getProductName())
                .removeProduct(sauceDemo.getProductName());
        logger.info("positiveHomeTaskRemoveFromCartTest2 is finished...");
        Assert.assertTrue(shoppingCartPage.cartRemovedItemIdentifier.isEnabled());
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with valid form data")
    public void positiveHomeTaskCheckoutTest1() {
        logger.info("positiveHomeTaskCheckoutTest1 is started...");
        CheckoutOverviewPage checkoutOverviewPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .addProduct(sauceDemo.getProductName())
                .continueCheckoutProcess()
                .fillInFormsWithRightInfo(sauceDemo.getFirstName(), sauceDemo.getLastName(), sauceDemo.getZipcode());
        Assert.assertEquals(checkoutOverviewPage.displayOverviewMessage(), "CHECKOUT: OVERVIEW");
        CheckoutCompletionPage completionPage = checkoutOverviewPage.overviewOrder();
        logger.info("positiveHomeTaskCheckoutTest1 is finished...");
        Assert.assertEquals(completionPage.displayCompletionMessage(), "THANK YOU FOR YOUR ORDER");
    }

    @Feature("Checkout")
    @Test(description = "An attempt to checkout with invalid form data")
    public void negativeHomeTaskCheckoutTest2() {
        logger.info("negativeHomeTaskCheckoutTest2 is started...");
        CheckoutInfoPage checkoutInfoPage = new LoginPage(driver, true)
                .loginWithCorrectCredentials(sauceDemo.getRightLogin(), sauceDemo.getPassword())
                .addProduct(sauceDemo.getProductName())
                .continueCheckoutProcess()
                .fillInFormsWithWrongInfo(sauceDemo.getNullName(), sauceDemo.getLastName(), sauceDemo.getZipcode());
        logger.info("negativeHomeTaskCheckoutTest2 is finished...");
        Assert.assertEquals(checkoutInfoPage.displayErrorButtonMessage(), "Error: First Name is required");
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
