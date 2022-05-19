package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(1000);
        homePage.firstSwapEl.click();
        ItemPage itemPage = new ItemPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        itemNames[0][0] = itemPage.itemName.getText();
        driver.navigate().back();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        homePage.secondSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        itemNames[1][0] = itemPage.itemName.getText();
        driver.navigate().back();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(1000);
        homePage.thirdSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        itemNames[2][0] = itemPage.itemName.getText();
        writer.writeDataLineByLine(itemNames, "src/main/resources/item-names-for-search.csv");
        driver.quit();
    }
}
