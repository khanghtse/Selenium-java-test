package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
    private WebDriver driver;

    By wishtListButton = By.xpath("//a[normalize-space()='My Wishlist']");
    By addToCartButton = By.xpath("//button[@type='button']//span//span[contains(text(),'Add to Cart')]");

    By shippingCountryLocator = By.xpath("//select[@id='country']");

    By estimateButton = By.xpath("//span[contains(text(),'Estimate')]");

    By stateProvinceLocator = By.xpath("//select[@id='region_id']");
    By zipLocator = By.xpath("//input[@id='postcode']");

    By updateTotalButton = By.xpath("//button[@title='Update Total']");

    By processToCheckOut = By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']" +
            "//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickUpdateTotalButton(){
        driver.findElement(updateTotalButton).click();
    }
    public void clickProcessToCheckOut(){
        driver.findElement(processToCheckOut).click();
    }

    public void clickEstimateButton(){
        driver.findElement(estimateButton).click();
    }

    public void clickWishListButton(){
        driver.findElement(wishtListButton).click();
    }

    public void clickAddToCartButton(){
        driver.findElement(addToCartButton).click();
    }

    public void selectShippingCountry(String id, String input){
        driver.findElement(shippingCountryLocator).click();
        Select dropdown = new Select(driver.findElement(By.id(id)));
        dropdown.selectByValue(input);
    }

    public void selectStateProvince(String id, String input){
        driver.findElement(stateProvinceLocator).click();
        Select dropdown = new Select(driver.findElement(By.id(id)));
        dropdown.selectByValue(input);
    }

    public void inputZip(String zip){
        WebElement zipElement = driver.findElement(zipLocator);
        zipElement.clear();
        zipElement.sendKeys(zip);
    }
}
