package baitap;

import POM.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/* Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Click Create an Account link and fill New User information excluding the registered Email ID.

4. Click Register

5. Verify Registration is done. Expected account registration done.

6. Go to TV menu

7. Add product in your wish list - use product - LG LCD

8. Click SHARE WISHLIST

9. In next page enter Email and a message and click SHARE WISHLIST

10.Check wishlist is shared. Expected wishlist shared successfully.

 */
@Test
public class TestCase5 {
    public static void testCase5(){
        String firstName = "Hoang";
        String middleName = "Tuan";
        String lastName = "Khang";
        String email = "khanghtse173615@fpt.edu.vn";
        String password = "123mical@0907";
        String confirmPassword = "123mical@0907";
        StringBuffer verifyError = new StringBuffer();
        String message = "This product is good";

        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            RegisterPage registerPage = new RegisterPage(driver);

            //2. Click on my account link
            registerPage.clickMyAccountLink();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //3. Click Create an Account link and fill New User information excluding the registered Email ID.
            registerPage.clickCreateAccount();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            registerPage.inputFirstName(firstName);
            registerPage.inputMiddleName(middleName);
            registerPage.inputLastName(lastName);
            registerPage.inputEmailAddress(email);
            registerPage.inputPassword(password);
            registerPage.inputConfirmPassword(confirmPassword);
            //4. Click Register
            registerPage.clickRegister();

            //5. Verify Registration is done. Expected account registration done.
            By registrationConfirmationElement = By.cssSelector("p[class='hello'] strong");
            driver.findElement(registrationConfirmationElement).getText();
            try {
                AssertJUnit.assertEquals("Hello, Hoang Tuan Khang!", registrationConfirmationElement);
            }catch (Error e){
                verifyError.append(e.toString());
            }
            //6. Go to TV menu
            driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //7. Add product in your wish list - use product - LG LCD

            driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //8. Click SHARE WISHLIST
            driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //9. In next page enter Email and a message and click SHARE WISHLIST
            registerPage.inputEmailAddress(email);
            registerPage.inputMessage(message);
            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
            //10. Check wishlist is shared. Expected wishlist shared successfully.
            By wishlist = By.cssSelector("li[class='success-msg'] ul li span");
            driver.findElement(wishlist).getText();
            try {
                AssertJUnit.assertEquals("Your Wishlist has been shared.", wishlist);
            }catch (Error e){
                verifyError.append(e.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
