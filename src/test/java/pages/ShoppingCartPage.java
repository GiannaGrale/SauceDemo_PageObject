package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {
    private final static String endpoint = "cart.html";

    @FindBy(className = "cart_quantity")
    public WebElement cartQuantityIdentifier;

    @FindBy(className = "inventory_item_price")
    public WebElement cartAddedItemPrice;

    @FindBy(id = "continue-shopping")
    public WebElement cartContinueShoppingButton;

    @FindBy(className = "removed_cart_item")
    public WebElement cartRemovedItemIdentifier;

    @FindBy(id = "checkout")
    public WebElement cartCheckoutButton;

    public String cartItemRemoveButton= "//div[.='replace']/following::div[@class='item_pricebar']//button";

    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return cartQuantityIdentifier.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ShoppingCartPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
        pageLogger.debug("The constructor worked successfully...");
    }


    public WebElement getRemoveItemButton(String productName) {
        return driver.findElement(By.xpath(cartItemRemoveButton.replace("replace", productName)));
    }

    //atomic methods
    public String displayTextPriceOfAddedItemInCart() {
        return cartAddedItemPrice.getText();
    }


    public ShoppingCartPage removeProduct (String productName){
        pageLogger.debug("Remove the product and stay on ShoppingCartPage");
        getRemoveItemButton(productName).click();
        return this;
    }

    public ShoppingCartPage continueShoppingBtnClick (String productName) {
        pageLogger.debug("Click continue shopping button and stay on the ShoppingCartPage");
        cartContinueShoppingButton.click();
        return this;
    }

    public ProductsPage goBackToShopping (){
        pageLogger.debug("Click continue shopping button and stay on the ProductsPage");
        cartContinueShoppingButton.click();
        return new ProductsPage(driver, false);
    }

    public CheckoutInfoPage continueCheckoutBtnClick (){
        pageLogger.debug("Click continue checkout button and go to the CheckoutInfoPage");
        cartCheckoutButton.click();
        return new CheckoutInfoPage(driver, false);
    }
}