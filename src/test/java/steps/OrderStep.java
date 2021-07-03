package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.CheckoutInfoPage;
import pages.CheckoutOverviewPage;
import pages.ProductsPage;
import pages.ShoppingCartPage;

public class OrderStep extends BaseStep {

    public OrderStep(WebDriver driver) {
        super(driver);
    }

    public void addProduct(String productName) {
        ProductsPage productsPage = new ProductsPage(driver, true);
        productsPage.addItemToCart(productName);
        productsPage.clickCartBadge();
    }
    public void removeProduct(String productName){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.clickRemoveButton(productName);
    }

    public void removeProductAndContinueShopping(String productName) {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver, true);
        shoppingCartPage.clickRemoveButton(productName);
        shoppingCartPage.clickContinueShoppingButton();
    }
}
