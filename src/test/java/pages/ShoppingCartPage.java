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


    public ProductsPage removeAndBackToShopping (String productName){
        pageLogger.debug("Initialization of removeAndBackToShopping");
        getRemoveItemButton(productName).click();
        pageLogger.debug("Removal of" + productName);
        cartContinueShoppingButton.click();
        pageLogger.debug("Finish of removeAndBackToShopping");
        return new ProductsPage(driver, true);
    }

    public CheckoutInfoPage continueCheckoutProcess (){
        pageLogger.debug("Initialization of continueCheckoutProcess");
        cartCheckoutButton.click();
        pageLogger.debug("Finish of continueCheckoutProcess");
        return new CheckoutInfoPage(driver, true);
    }

    public ShoppingCartPage removeProduct (String productName){
        pageLogger.debug("Initialization of removeProduct");
        getRemoveItemButton(productName).click();
        pageLogger.debug("Removal of" + productName);
        pageLogger.debug("Finish of removeProduct");
        return this;
    }
}