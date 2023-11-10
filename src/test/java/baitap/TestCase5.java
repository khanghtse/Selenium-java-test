package baitap;

import POM.RegisterPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

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
    public static void testCase5() {
        int scc = 0;
        String firstname = "Khang";
        String middlename = "Tuan";
        String lastname = "Hoang";
        String email_address = "khanghoang090703@gmail.com";
        String password = "123456";
        String confirmation = "123456";

        //  Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Open target page
            driver.get("http://live.techpanda.org/");
            RegisterPage registerPage = new RegisterPage(driver);
            // Delay Web for Performance

            // 2. Click on my account link
            registerPage.myAccountLink();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            // 3. Click Create an Account link and fill New User information excluding the registered Email ID.
            registerPage.createAccountLink();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            // 3a. fill New User information excluding the registered Email ID
            registerPage.enterFirstName(firstname);
            registerPage.enterMiddleName(middlename);
            registerPage.enterLastName(lastname);
            registerPage.enterEmailAddress(email_address);
            registerPage.enterPassword(password);
            registerPage.enterPasswordConfirmation(confirmation);

            //4. Click Register
            registerPage.registerButton();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //5. Verify Registration is done. Expected account registration done.
            String vWelcome = ("WELCOME, " + firstname.toUpperCase() + " " + middlename.toUpperCase() + " " + lastname.toUpperCase() + "!");
            String tWelcome = driver.findElement(By.xpath("//div[1]/p[1]")).getText();
            System.out.println(tWelcome);

            // Catch Error
            try {
                AssertJUnit.assertEquals(vWelcome, tWelcome);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 6. Go To TV Menu
            driver.findElement(By.xpath("(//a[@class='level0 '])[2]")).click();

            // 7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("//a[@class='link-wishlist']")).click();

            // 8. Click SHARE WISHLIST
            driver.findElement(By.xpath("//button[@class='button btn-share']")).click();

            //9. In next page enter Email and a message and click SHARE WISHLIST
            WebElement typeEmail = driver.findElement(By.id("email_address"));
            typeEmail.clear();
            typeEmail.sendKeys("khanghtse173615@fpt.edu.vn");

            WebElement typeMess = driver.findElement(By.id("message"));
            typeMess.clear();
            typeMess.sendKeys("hihihi");

            // click SHARE WISHLIST
            driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            // 10.Check wishlist is shared. Expected wishlist shared successfully.
            WebElement messageWishlist = driver.findElement(By.xpath("//li[@class='success-msg']//li[1]"));
            String expectedMessageWishlist = "Your Wishlist has been shared.";

            try {
                AssertJUnit.assertEquals(expectedMessageWishlist, messageWishlist.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Take Screen shot
            scc = (scc + 5);
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String relativePath = "src/main/resources/screenshots/";
            String fileName = "testCase" + scc + ".png";
            String fullPath = relativePath + fileName;
            FileUtils.copyFile(srcFile, new File(fullPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
        //  Quit browser session
        driver.quit();
    }
}
