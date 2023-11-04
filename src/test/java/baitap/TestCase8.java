package baitap;

import POM.CartPage;
import POM.LoginPage;
import POM.RegisterPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
@Test
public class TestCase8 {
    public static void testCase8() {
        int scc =0;
        String email = "khanghtse173615@fpt.edu.vn";
        String password = "123mical@0907";
        StringBuffer verifyError = new StringBuffer();

        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            RegisterPage registerPage = new RegisterPage(driver);
            LoginPage loginPage = new LoginPage(driver);
            CartPage cartPage = new CartPage(driver);

            //2. Click on my account link
            loginPage.clickMyAccountLink();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //3. Login in application using previously created credential
            loginPage.inputEmailAddress(email);
            loginPage.inputPassword(password);
            loginPage.clickLoginButton();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //4. Click on 'REORDER' link , change QTY & click Update
            driver.findElement(By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']")).click();
            WebElement quantityEle = driver.findElement(By.xpath("//input[@title='Qty']"));
            quantityEle.click();
            quantityEle.clear();
            quantityEle.sendKeys("3");
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
            Thread.sleep(2000);

            //5. Verify Grand Total is changed
            String previousTotal = driver.findElement(By.cssSelector("td[class='product-cart-price'] span[class='price']")).getText();
            String totalAfterUpdate = driver.findElement(By.cssSelector("strong span[class='price']")).getText();

            System.out.println("Previous total: " +previousTotal);
            System.out.println("Total after update: " +totalAfterUpdate);

            cartPage.selectShippingCountry("country", "US");

            //* select province
            cartPage.selectStateProvince("region_id", "62");
            //input zip
            cartPage.inputZip("3000");

            //7. Click Estimate
            //driver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();
            cartPage.clickEstimateButton();
            //8. Verify Shipping cost generated

//            String total = driver.findElement(By.cssSelector("strong span[class='price']")).getText();
//            String productPrice = driver.findElement(By.cssSelector("td[class='product-cart-price'] span[class='price']")).getText();
//            AssertJUnit.assertEquals(total, productPrice);
//            if (total.equals(productPrice)){
//                System.out.println("productPrice = Total = "+total);
//            }

            //9. Select Shipping Cost, Update Total
            driver.findElement(By.xpath("//label[@for='s_method_flatrate_flatrate']")).click();
            Thread.sleep(2000);

            //driver.findElement(By.xpath("//button[@title='Update Total']")).click();
            cartPage.clickUpdateTotalButton();
            Thread.sleep(2000);


            //10. Verify shipping cost is added to total

            //11. Click "Proceed to Checkout"
            //driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]")).click();
            cartPage.clickProcessToCheckOut();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //12a. Enter Billing Information, and click Continue

            driver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).click();
            driver.findElement(By.xpath("//button[@onclick='billing.save()']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            //12b. Enter Shipping Information, and click Continue
//            driver.findElement(By.xpath("//button[@onclick='shipping.save()']//span//span[contains(text(),'Continue')]")).click();
//            for (String handle : driver.getWindowHandles()) {
//                driver.switchTo().window(handle);
//            }
            //13. In Shipping Method, Click Continue
            driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']" +
                    "//span//span[contains(text(),'Continue')]")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            //14. In Payment Information select 'Check/Money Order' radio button. Click Continue

            driver.findElement(By.xpath("//label[@for='p_method_checkmo']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@onclick='payment.save()']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            //15. Click 'PLACE ORDER' button
            driver.findElement(By.xpath("//button[@title='Place Order']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            scc = (scc+8);
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String relativePath = "src/main/resources/screenshots/";
            String fileName = "testCase" + scc + ".png";
            String fullPath = relativePath + fileName;
            FileUtils.copyFile(srcFile, new File(fullPath));
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
