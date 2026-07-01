package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetails extends BasePage{
    private By addToCart = By.id("add-to-cart");
    private By cartButton = By.xpath("//a[@data-test='shopping-cart-link']");
    private By productDetName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productDetDesc = By.xpath("//div[@data-test='inventory-item-desc']");
    private By productDetPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By productDetImg = By.xpath("//*[@data-test = 'item-sauce-labs-backpack-img']");

    public ProductDetails(WebDriver driver) {
        super(driver);
    }

    public WebElement getImgDet() {
        return findElement(productDetImg);
    }
    public WebElement getNameDet() {
        return findElement(productDetName);
    }
    public WebElement getDescDet() {
        return findElement(productDetDesc);
    }
    public WebElement getPriceDet() {
        return findElement(productDetPrice);
    }
    public WebElement getAddToCart() {
        return findElement(addToCart);
    }
    public WebElement getCartButton() {
        return findElement(cartButton);
    }
    public void clickOnAddToCartButton(){
        findElement(addToCart).click();
    }
    public CartPage clickOnCartButton(){
        findElement(cartButton).click();
        return new CartPage(driver);
    }



}
