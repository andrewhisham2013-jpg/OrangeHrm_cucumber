package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage{
    private By cartPageTitle = By.xpath("//*[@data-test = 'title']");
    private By productName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productDesc = By.xpath("//div[@data-test='inventory-item-desc']");
    private By productPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By productCartButton = By.xpath("//a[@data-test='shopping-cart-link']");
    private By productCheckoutButton = By.id("checkout");
    private By productContinueButton = By.xpath("continue-shopping");
    private By productRemoveButton = By.id("remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCartPageTitle() {
        return findElement(cartPageTitle);
    }
    public WebElement getProductCartName() {
        return findElement(productName);
    }
    public WebElement getProductCartDesc() {
        return findElement(productDesc);
    }
    public WebElement getProductCartPrice() {
        return findElement(productPrice);
    }
    public WebElement getProductCartButton() {
        return findElement(productCartButton);
    }
    public WebElement getProductCheckoutButton() {
        return findElement(productCheckoutButton);
    }
    public WebElement getProductCartContinueButton() {
        return findElement(productContinueButton);
    }
    public WebElement getProductCartRemoveButton() {
        return findElement(productRemoveButton);
    }
    public boolean checkCartProduct(ProductDetails productDetails){
        boolean checker = false;
        String chosenProduct = productDetails.getNameDet().getText();
        String checkedProduct = getProductCartName().getText();
        if(chosenProduct.equals(checkedProduct))
            checker = true;
        return checker;
    }
    public CheckoutInformationPage ClickOnCheckoutButton() {
        findElement(productCheckoutButton).click();
        return new CheckoutInformationPage(driver);
    }


}
