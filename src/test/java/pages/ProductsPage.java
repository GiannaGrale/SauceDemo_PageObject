package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {
    private final static String endpoint = "inventory.html";

    @FindBy(className = "title")
    public WebElement titleLabel;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartItemNumbers;

    @FindBy (className = "shopping_cart_container")
    public WebElement cartBadge;

    public String itemAddToCartButton ="//div[.='replace']/ancestor::div[@class= 'inventory_item_description']//button";

    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return titleLabel.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public ProductsPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    public WebElement getItemAddToCartButton(String productName) {
        return driver.findElement(By.xpath(itemAddToCartButton.replace("replace", productName)));
    }

    public String displayAddButtonMessage(String productName) {
        return getItemAddToCartButton(productName).getText().toLowerCase();
    }

    public ProductsPage addProductToCart (String productName) {
        getItemAddToCartButton(productName).click();
        return this;
    }

    public ProductsPage observeCartOnProductPage () {
        cartBadge.click();
        return this;
    }

    public ShoppingCartPage goToTheCart () {
        cartBadge.click();
        return new ShoppingCartPage(driver, false);
    }
}