package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import java.util.List;

public class ProductsPage extends BasePage {
    private By pageTitle = By.xpath("//span[@data-test=\"title\"]");
    private By productName = By.xpath("//*[@id=\"item_4_title_link\"]/div");
    private By productDescription = By.xpath("//div[contains(text(),'streamlined Sly Pack')]");
    private By productPrice = By.xpath("//div[text()='29.99']");
    private By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    private By cartButton = By.xpath("//a[@data-test='shopping-cart-link']");

    //List<WebElement> Products = driver.findElements(By.className("inventory_item_name"));
    public ProductsPage(WebDriver driver){
        super(driver);
    }

    public WebElement getTitle() {
        return findElement(pageTitle);
    }
    public WebElement getName() {
        return findElement(productName);
    }
    public WebElement getDescription() {
        return findElement(productDescription);
    }
    public WebElement getPrice() {
        return findElement(productPrice);
    }
    public WebElement getAddToCart() {
        return findElement(addToCart);
    }
    public WebElement getCartButton() {
        return findElement(cartButton);
    }

    public ProductDetails clickOnProductName(){
        findElement(productName).click();
        return new ProductDetails(driver);
    }

}
