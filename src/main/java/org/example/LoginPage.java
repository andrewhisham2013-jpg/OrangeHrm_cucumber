package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private By userName = By.id("user-name");
    private By pass = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver){
        super(driver);
    }
    public WebElement getUserName() {
        return findElement(userName);
    }
    public WebElement getPassword() {
        return findElement(pass);
    }
    public WebElement getLogin() {
        return findElement(loginButton);
    }
    public void enterUserName(String username){
        findElement(userName).clear();
        findElement(userName).sendKeys(username);
    }
    public void enterPassword(String password){
        findElement(pass).clear();
        findElement(pass).sendKeys(password);
    }
    public ProductsPage clickOnLoginButton(){
        findElement(loginButton).click();
        return new ProductsPage(driver);
    }

}
