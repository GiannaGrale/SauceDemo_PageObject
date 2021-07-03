package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {
    private final static String endpoint = "inventory.html";

    private final static By title_Label_By = By.className("title");
    private final static String item_Add_To_Cart_Button = "//div[.='replace']/ancestor::div[@class= 'inventory_item_description']//button";
    private final static By cart_Items_Number_By = By.className("shopping_cart_badge");
    private final static By cart_Badge_By = By.className("shopping_cart_container");

    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitleLabel().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public ProductsPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    public WebElement getTitleLabel() {
        return driver.findElement(title_Label_By);
    }

    public String getTitleText() {
        return getTitleLabel().getText();
    }

    public WebElement getNumberCartItems() {
        return driver.findElement(cart_Items_Number_By);
    }

    public WebElement getCartBadge() {
        return driver.findElement(cart_Badge_By);
    }

    public WebElement getItemAddToCartButton(String productName) {
        return driver.findElement(By.xpath(item_Add_To_Cart_Button.replace("replace", productName)));
    }

    //atomic methods

    public void addItemToCart(String productName) {
        getItemAddToCartButton(productName).click();
    }

    public void clickCartBadge() {
        getCartBadge().click();
    }

    public String displayAddButtonMessage(String productName) {
        return getItemAddToCartButton(productName).getText().toLowerCase();
    }
}
