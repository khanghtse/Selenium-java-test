package baitap;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

4. Click on �COMPARE� button. A popup window opens

5. Verify the pop-up window and check that the products are reflected in it

Heading "COMPARE PRODUCTS" with selected products in it.

6. Close the Popup Windows

*/
@Test
public class TestCase4 {
    public static void testCase4() {
        StringBuffer verifyError = new StringBuffer();

        WebDriver driver = driverFactory.getChromeDriver();

        try {
        //1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        //2. Click on �MOBILE� menu
        driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

        //3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
        driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
        driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();

        //4. Click on �COMPARE� button. A popup window opens
        driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();

        //5. Verify the pop-up window and check that the products are reflected in it
        WebElement popupWindow = driver.findElement(By.cssSelector("div[class='page-title title-buttons'] h1"));
        String popupText = popupWindow.getText();
        try{
            AssertJUnit.assertEquals("COMPARE PRODUCTS", popupText);
        }catch (Error e){
            verifyError.append(e.toString());
        }

        // 6. Close the Popup Windows
        driver.findElement(By.xpath("//span[contains(text(),'Close Window')]")).click();

        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
