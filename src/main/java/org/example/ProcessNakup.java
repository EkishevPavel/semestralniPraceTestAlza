package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ProcessNakup {
    public ProcessNakup() {
    }
    public void processThreeFirstItems() throws InterruptedException {
        String[][] itemNames = new String[3][1];
        CsvWriter writer = new CsvWriter();
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        Thread.sleep(2000);
        homePage.acceptCOOOOOOCKIE.click();
        homePage.firstSwapEl.click();
        ItemPage itemPage = new ItemPage(driver);
        Thread.sleep(1000);
        itemNames[0][0] = itemPage.itemName.getText();
        driver.navigate().back();
        Thread.sleep(3000);
        homePage.acceptCOOOOOOCKIE.click();
        homePage.secondSwapEl.click();
        Thread.sleep(1000);
        itemNames[1][0] = itemPage.itemName.getText();
        driver.navigate().back();
        Thread.sleep(3000);
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(1000);
        homePage.thirdSwapEl.click();
        Thread.sleep(1000);
        itemNames[2][0] = itemPage.itemName.getText();
        writer.writeDataLineByLine(itemNames, "src/main/resources/item-names-for-search.csv");
        driver.close();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        itemPage.addItemToBucketBtn.click();
//        driver.navigate().back();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        homePage.bucketBtn.click();
//        BucketPage bucketPage = new BucketPage(driver);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        bucketPage.linkToNextStep.click();
//        DeliveryAndPaymentPage deliveryAndPaymentPage = new DeliveryAndPaymentPage(driver);
//        deliveryAndPaymentPage.deliveryToAddressBtn.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


    }
}
