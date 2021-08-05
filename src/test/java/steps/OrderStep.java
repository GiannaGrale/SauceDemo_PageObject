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
    public ProductsPage addProduct(String productName) {
        ProductsPage productsPage = new ProductsPage(driver, true);
        productsPage.getItemAddToCartButton(productName);
        productsPage.cartBadge.click();
        return new ProductsPage (driver, true);
    }

    @Step("Remove a '{productName}' to the cart")
    public ShoppingCartPage removeProduct(String productName){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.getRemoveItemButton(productName).click();

        return  new ShoppingCartPage(driver, true);
    }

    @Step("Remove a '{productName}' and continue shopping")
    public ShoppingCartPage removeProductAndContinueShopping(String productName) {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.getRemoveItemButton(productName).click();
        shoppingCartPage.cartContinueShoppingButton.click();

        return  new ShoppingCartPage(driver, false);
    }
}
