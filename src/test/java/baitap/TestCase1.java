package baitap;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/
@Test
public class TestCase1 {
    public static void testCase1(){
        int scc = 0;
        StringBuffer verifyError = new StringBuffer();

        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //Step 2. Verify Title of the page
            String demoSite = String.valueOf(driver.findElement(By.cssSelector("h2:nth-child(1)")));
            System.out.println(demoSite);
            try{
                AssertJUnit.assertEquals("This demo site for " ,demoSite);
            }catch (Error e){
                verifyError.append(e.toString());
            }

            Thread.sleep(2000);

            //Step 3. Click on -> MOBILE -> menu
            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[1]/div[1]/div[3]/nav[1]/ol[1]/li[1]/a[1]")).click();

            Thread.sleep(2000);

            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            new Select(driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) >" +
                    " div:nth-child(1) > div:nth-child(2) > div:nth-child(1) >" +
                    " div:nth-child(3) > div:nth-child(1) > div:nth-child(1) >" +
                    " div:nth-child(2) > select:nth-child(2)"))).selectByVisibleText("Name");
            Thread.sleep(2000);
            //Step 5. Verify all products are sorted by name]
            scc = (scc+1);
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String relativePath = "src/main/resources/screenshots/";
            String fileName = "testCase" + scc + ".png";
            String fullPath = relativePath + fileName;
            FileUtils.copyFile(srcFile, new File(fullPath));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
