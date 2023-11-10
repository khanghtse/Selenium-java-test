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
        //  Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Open target page
            driver.get("http://live.techpanda.org/");
            // Delay Web for Performance

            // 2. Click on Mobile
            WebElement mobileMenu = driver.findElement(By.className("level0"));
            mobileMenu.click();

            // 3. Click on Add To Compare product 1 Sony Xperia
            WebElement productSelect = driver.findElement(By.xpath("(//a[@class='link-compare'])[2]"));
            productSelect.click();

            //  Click on Add To Compare product 2 Iphone
            WebElement productSelect1 = driver.findElement(By.xpath("(//a[@class='link-compare'])[3]"));
            productSelect1.click();

            // 4. Click on Compare  and switching to new window
            WebElement productCompare = driver.findElement(By.xpath("//button[@class='button']"));
            productCompare.click();
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            // 5. Verify the pop-up window and check that the products are reflected in it Heading "COMPARE PRODUCTS" with selected products in it.
            WebElement errorMessage = driver.findElement(By.xpath("//h1[text()='Compare Products']"));
            String expectedErrorMessage = "COMPARE PRODUCTS";
            AssertJUnit.assertEquals(expectedErrorMessage, errorMessage.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Quit browser session
        driver.quit();
    }
}
