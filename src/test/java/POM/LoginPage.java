package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    By myAccountLink = By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']");
    By emailAddressInputLocator = By.xpath("//input[@id='email']");
    By passwordInputLocator = By.xpath("//input[@id='pass']");

    By loginButton = By.xpath("//span[contains(text(),'Login')]");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickMyAccountLink() {
        driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();
        driver.findElement(myAccountLink).click();
    }

    public void inputEmailAddress(String email) {
        WebElement emailElement = driver.findElement(emailAddressInputLocator);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void inputPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

}
