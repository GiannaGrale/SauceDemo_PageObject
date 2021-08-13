package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;
import pages.ShoppingCartPage;

public class OrderStep extends BaseStep {

    public OrderStep(WebDriver driver) {
        super(driver);
    }
    @Step("Add a '{productName}' to the cart")
    public void addProduct(String productName) {
        ProductsPage productsPage = new ProductsPage(driver, true);
        productsPage.addItemToCart(productName);
        productsPage.clickCartBadge();
    }
    @Step("Remove a '{productName}' to the cart")
    public void removeProduct(String productName){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.clickRemoveButton(productName);
    }
    @Step("Remove a '{productName}' and continue shopping")
    public void removeProductAndContinueShopping(String productName) {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.clickRemoveButton(productName);
        shoppingCartPage.clickContinueShoppingButton();
    }
}
