package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage extends BasePage {

    private final static By cart_Quantity_Identifier_By = By.className("cart_quantity");
    private final static By cart_Added_Item_Price_By = By.className("inventory_item_price");
    private final static By cart_Remove_Item_By = By.id("remove-sauce-labs-backpack");
    private final static By cart_Continue_Shopping_Button_By = By.id("continue-shopping");
    private final static By cart_Removed_Item_Identifier_By = By.className("removed_cart_item");
    private final static By cart_Checkout_Button_By = By.id("checkout");


    @Override
    protected void openPage() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getCartQuantityIdentifier().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ShoppingCartPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    public WebElement getCartQuantityIdentifier() {
        return driver.findElement(cart_Quantity_Identifier_By);
    }

    public WebElement getPriceOfAddedItem() {
        return driver.findElement(cart_Added_Item_Price_By);
    }

    public WebElement getRemoveItemButton() {
        return driver.findElement(cart_Remove_Item_By);
    }

    public WebElement getContinueShoppingButton() {
        return driver.findElement(cart_Continue_Shopping_Button_By);
    }

    public WebElement getRemovedCartItemIdentifier() {
        return driver.findElement(cart_Removed_Item_Identifier_By);
    }

    public  WebElement getCheckoutButton (){
        return  driver.findElement(cart_Checkout_Button_By);
    }


    //atomic methods
    public String displayQuantityText() {
        return getCartQuantityIdentifier().getText();
    }

    public String displayTextPriceOfAddedItemInCart() {
        return getPriceOfAddedItem().getText();
    }

    public void clickRemoveButton() {
        getRemoveItemButton().click();
    }

    public void clickContinueShoppingButton() {
        getContinueShoppingButton().click();
    }

    public void clickCheckoutButton (){
        getCheckoutButton().click();
    }

}

