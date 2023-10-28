package baitap;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

//Test Steps:
//
//        1. Go to http://live.techpanda.org/
//
//        2. Click on �MOBILE� menu
//
//        3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
//
//        4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed
//
//        "The requested quantity for "Sony Xperia" is not available.
//
//        5. Verify the error message
//
//        6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
//
//        7. Verify cart is empty
@Test
public class TestCase3 {
    public static void testCase3(){
        int scc = 0;
        StringBuffer verifyError = new StringBuffer();

        WebDriver driver = driverFactory.getChromeDriver();
        try{
            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // 2. Click on �MOBILE� menu
            driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
            Thread.sleep(2000);

            //  3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
            driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();

            // 4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed
            ////        "The requested quantity for "Sony Xperia" is not available.
            WebElement quantityInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            quantityInput.clear();
            quantityInput.sendKeys("1000");
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();

            // 5. Verify the error message

            WebElement errorMes = driver.findElement(By.cssSelector("li[class='error-msg'] ul li span"));
            String errorMessage = errorMes.getText();
            System.out.println(errorMessage);
            try{
                AssertJUnit.assertEquals("The maximum quantity allowed for purchase is 500.", errorMessage);
            }catch (Error e){
                verifyError.append(e.toString());
            }

            // 6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
            driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
            Thread.sleep(2000);
            // 7. Verify cart is empty
            WebElement emptyCartMess = driver.findElement(By.cssSelector("div[class='page-title'] h1"));
            String emptyCartMessage = emptyCartMess.getText();
            System.out.println(emptyCartMessage);
            try{
                AssertJUnit.assertEquals("SHOPPING CART IS EMPTY", errorMessage);
            }catch (Error e){
                verifyError.append(e.toString());
            }
//            scc = (scc+3);
//            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//            String relativePath = "src/main/resources/screenshots/";
//            String fileName = "testCase" + scc + ".png";
//            String fullPath = relativePath + fileName;
//            FileUtils.copyFile(srcFile, new File(fullPath));
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
