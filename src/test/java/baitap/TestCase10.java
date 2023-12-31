package baitap;

import POM.BackEnd;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class TestCase10 {
    public static void testCase10(){
        //LoginPage
        String username = "user01";
        String password = "guru99com";
        String orderId = "100021153";
        String fromDay = "11/1/2023";
        String toDay = "11/8/2023";

        //  Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        // import function from POM
        BackEnd backEnd = new BackEnd(driver);

        try {
            // 1. Open target page
            driver.get("http://live.techpanda.org/index.php/backendlogin");
            // Delay Web for Performance

            // 2. Login the credentials provided
            backEnd.setEnterUserName(username);
            backEnd.setEnterPassword(password);
            backEnd.setLoginClick();

            Thread.sleep(2000);

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(1000);
            // Click to remove messages
            driver.findElement(By.xpath("//span[text()='close']")).click();
            Thread.sleep(2000);

            // 3.Go to Sales-> Orders menu
            driver.findElement(By.xpath("//span[text()='Sales']")).click();
            Thread.sleep(1000);

            backEnd.setOrderLink();
            Thread.sleep(1000);

            // 4. Input OrderId and FromDate -> ToDate
            backEnd.setEnterOrder(orderId);
            backEnd.setEnterFromDay(fromDay);
            backEnd.setEnterToDay(toDay);

            Thread.sleep(500);
            //5.Click Search button
            backEnd.setSearchClick();
            Thread.sleep(2000);

            // 6.Screenshot capture.
            int scc = 10;
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
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
