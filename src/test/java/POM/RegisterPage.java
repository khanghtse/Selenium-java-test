package POM;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    WebDriver driver;

    //Define your locators at the top of the class
    By myAccountLink = By.linkText("MY ACCOUNT");
    By createAccountLink = By.linkText("CREATE AN ACCOUNT");
    By enterFirstName = By.id("firstname");
    By enterMiddleName = By.id("middlename");
    By enterLastName = By.id("lastname");
    By enterEmailAddress = By.id("email_address");
    By enterPassword = By.id("password");
    By enterPasswordConfirmation = By.id("confirmation");
    By registerButton= By.xpath("//button[@class='button']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Create methods for each action
    public void myAccountLink(){
        driver.findElement(myAccountLink).click();
    }
    public void createAccountLink(){
        driver.findElement(createAccountLink).click();
    }
    public void enterFirstName(String firstName){
        WebElement firstNameElement = driver.findElement(enterFirstName);
        firstNameElement.clear(); // clear the QTY before
        firstNameElement.sendKeys(firstName);
    }
    public void enterMiddleName(String middleName){
        WebElement firstNameElement = driver.findElement(enterMiddleName);
        firstNameElement.clear(); // clear the QTY before
        firstNameElement.sendKeys(middleName);
    }
    public void enterLastName(String lastName){
        WebElement firstNameElement = driver.findElement(enterLastName);
        firstNameElement.clear(); // clear the QTY before
        firstNameElement.sendKeys(lastName);
    }
    public void enterEmailAddress(String emailAddress){
        WebElement firstNameElement = driver.findElement(enterEmailAddress);
        firstNameElement.clear(); // clear the QTY before
        firstNameElement.sendKeys(emailAddress);
    }
    public void enterPassword(String password){
        WebElement firstNameElement = driver.findElement(enterPassword);
        firstNameElement.clear(); // clear the QTY before
        firstNameElement.sendKeys(password);
    }
    public void enterPasswordConfirmation(String confirmation){
        WebElement firstNameElement = driver.findElement(enterPasswordConfirmation);
        firstNameElement.clear(); // clear the QTY before
        firstNameElement.sendKeys(confirmation);
    }
    public void registerButton(){
        driver.findElement(registerButton).click();
    }
}
