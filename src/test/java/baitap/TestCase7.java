package baitap;

import POM.CartPage;
import POM.LoginPage;
import POM.RegisterPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class TestCase7 {

    public static void testCase7(){
        int scc =0;
        String email = "khanghtse173615@fpt.edu.vn";
        String password = "123mical@0907";
        StringBuffer verifyError = new StringBuffer();

        WebDriver driver = driverFactory.getChromeDriver();
        try {
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

            //4. Click on 'My Orders'
            driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //5. Click on 'View Order'
            driver.findElement(By.xpath("//tr[@class='first odd']//a[contains(text(),'View Order')]")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            //6. Click on 'Print Order' link
            driver.findElement(By.xpath("//a[@class='link-print']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            scc = (scc+7);
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
