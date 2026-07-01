package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage{

    private By pageTitle = By.xpath("//span[@data-test='title']");
    private By productName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By productPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By paymentInfo = By.xpath("//div[@data-test='payment-info-value']");
    private By shippingInfo = By.xpath("//div[@data-test='shipping-info-value']");
    private By totalPriceWithoutTax = By.xpath("//*[@data-test = 'subtotal-label' and text()='29.99']");
    private By tax = By.xpath("//*[@data-test = 'tax-label']");
    private By totalPriceWithTax = By.xpath("//*[@data-test = 'total-label']");
    private By cancelButton = By.id("cancel");
    private By finishButton = By.id("finish");
    private By cartButton = By.xpath("//*[@id=\"shopping_cart_container\"]/a");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return findElement(pageTitle);
    }
    public WebElement getProductName() {
        return findElement(productName);
    }
    public WebElement getProductDescription() {
        return findElement(productDescription);
    }
    public WebElement getProductPrice() {
        return findElement(productPrice);
    }
    public WebElement getPaymentInfo() {
        return findElement(paymentInfo);
    }
    public WebElement getShippingInfo() {
        return findElement(shippingInfo);
    }
    public WebElement getTotalPriceWithoutTax() {
        return findElement(totalPriceWithoutTax);
    }
    public WebElement getTax() {
        return findElement(tax);
    }
    public WebElement getTotalPriceWithTax() {
        return findElement(totalPriceWithTax);
    }
    public WebElement getCancelButton() {
        return findElement(cancelButton);
    }
    public WebElement getFinishButton() {
        return findElement(finishButton);
    }
    public WebElement getCartButton() {
        return findElement(cartButton);
    }
    public boolean CheckTotalPriceWithoutAddTax(CheckoutOverviewPage checkoutOverview) {
        boolean checkTotalPrice = false;
        double price = Double.parseDouble(checkoutOverview.getProductPrice().getText().replace("$", "").trim());
        double totalPrice = Double.parseDouble(checkoutOverview.getTotalPriceWithoutTax().getText().replace("Item total: $", "").trim());
        if(price == totalPrice)
            checkTotalPrice = true;
        return checkTotalPrice;
    }
    public boolean CheckTotalPriceWithAddTax(CheckoutOverviewPage checkoutOverview) {
        boolean checkTotalPrice = false;
        double totalPrice = Double.parseDouble(checkoutOverview.getTotalPriceWithTax().getText().replace("Total: $", "").trim());
        double price = Double.parseDouble(checkoutOverview.getProductPrice().getText().replace("$", "").trim());
        double tax = Double.parseDouble(checkoutOverview.getTax().getText().replace("Tax: $", "").trim());
        double priceWithTax = price + tax;
        if(priceWithTax == totalPrice)
            checkTotalPrice = true;
        return checkTotalPrice;
    }
    public CheckoutCompletePage ClickOnFinishButton() {
        findElement(finishButton).click();
        return new CheckoutCompletePage(driver);
    }
}
