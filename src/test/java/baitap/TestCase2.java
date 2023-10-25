package baitap;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
//        1. Goto http://live.techpanda.org/
//
//        2. Click on �MOBILE� menu
//
//        3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
//
//        4. Click on Sony Xperia mobile
//
//        5. Read the Sony Xperia mobile from detail page.
//
//        6. Compare Product value in list and details page should be equal ($100).

@Test
public class TestCase2 {
    public static void  testCase2(){
        int scc = 0;
        StringBuffer verifyError = new StringBuffer();

        WebDriver driver = driverFactory.getChromeDriver();
        try{
            // step 1: Goto http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // step 2: Click on �MOBILE� menu
            driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

            Thread.sleep(2000);
            // step 3: In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
            String xPeriaPrice = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']")).getText();

            // step4: Click on Sony Xperia mobile
            driver.findElement(By.cssSelector("#product-collection-image-1")).click();
            Thread.sleep(2000);

            // step 5: Read the Sony Xperia mobile from detail page.
            String detailprice = driver.findElement(By.cssSelector(".price")).getText();
            AssertJUnit.assertEquals(xPeriaPrice, detailprice);
            if (xPeriaPrice.equals(detailprice)){
                System.out.println("xperiaPrice = detailprice = " +detailprice);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
