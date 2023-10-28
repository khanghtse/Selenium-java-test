package POM;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private WebDriver driver;
    By myAccountLink = By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']");
    By createAnAccount = By.xpath("//span[contains(text(),'Create an Account')]");
    By firstNameInputLocator = By.id("firstname");
    By middleNameInputLocator = By.id("middlename");
    By lastNameInputLocator = By.id("lastname");
    By emailAddressInputLocator = By.id("email_address");
    By passwordInputLocator = By.id("password");
    By confirmPasswordInputLocator = By.id("confirmation");
    By messageInputLocator = By.id("message");
    By registerButton = By.xpath("//button[@title='Register']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMyAccountLink() {
        driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();
        driver.findElement(myAccountLink).click();
    }

    public void clickCreateAccount() {
        driver.findElement(createAnAccount).click();
    }

    public void inputMessage(String message) {
        WebElement messageElement = driver.findElement(messageInputLocator);
        messageElement.clear();
        messageElement.sendKeys(message);
    }

    public void inputFirstName(String firstName) {
        WebElement firstNameElement = driver.findElement(firstNameInputLocator);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    public void inputMiddleName(String middleName) {
        WebElement middleNameElement = driver.findElement(middleNameInputLocator);
        middleNameElement.clear();
        middleNameElement.sendKeys(middleName);
    }

    public void inputLastName(String lastName) {
        WebElement lastNameElement = driver.findElement(lastNameInputLocator);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
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

    public void inputConfirmPassword(String confirmPassword) {
        WebElement passwordElement = driver.findElement(confirmPasswordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(confirmPassword);
    }

    public void clickRegister(){
        driver.findElement(registerButton).click();
    }
}
