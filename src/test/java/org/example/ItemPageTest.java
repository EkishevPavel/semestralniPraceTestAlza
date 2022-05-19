package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.FileReader;
import java.io.Reader;
import java.time.Duration;


public class ItemPageTest {

    @Test
    public void testRankCountBetweenItemPageAndReviewsPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        Thread.sleep(1000);
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(1000);
        homePage.firstSwapEl.click();
        ItemPage itemPage = new ItemPage(driver);
        Thread.sleep(1000);
        String itemRankCount = itemPage.itemRankCount.getText();
        itemRankCount = itemRankCount.substring(0, itemRankCount.length() - 1);
        int rankCountOnItemPage = Integer.parseInt(itemRankCount);
        itemPage.reviews.click();
        Thread.sleep(1000);
        String countRankNumFive = driver.findElement(By.xpath("//*[@id='recenze']/div[1]/div/div[2]/div[1]/div/div[2]/div[1]/span[2]")).getText();
        String countRankNumFourth = driver.findElement(By.xpath("//*[@id='recenze']/div[1]/div/div[2]/div[1]/div/div[2]/div[2]/span[2]")).getText();
        String countRankNumThree = driver.findElement(By.xpath("//*[@id='recenze']/div[1]/div/div[2]/div[1]/div/div[2]/div[3]/span[2]")).getText();
        String countRankNumTwo = driver.findElement(By.xpath("//*[@id='recenze']/div[1]/div/div[2]/div[1]/div/div[2]/div[4]/span[2]")).getText();
        String countRankNumOne = driver.findElement(By.xpath("//*[@id='recenze']/div[1]/div/div[2]/div[1]/div/div[2]/div[5]/span[2]")).getText();
        countRankNumOne = countRankNumOne.substring(0, countRankNumOne.length() - 2);
        countRankNumTwo = countRankNumTwo.substring(0, countRankNumTwo.length() - 2);
        countRankNumThree = countRankNumThree.substring(0, countRankNumThree.length() - 2);
        countRankNumFourth = countRankNumFourth.substring(0, countRankNumFourth.length() - 2);
        countRankNumFive = countRankNumFive.substring(0, countRankNumFive.length() - 2);

        int rankCountOnReviewsPage = Integer.parseInt(countRankNumOne) + Integer.parseInt(countRankNumTwo) + Integer.parseInt(countRankNumThree) +
                Integer.parseInt(countRankNumFourth) + Integer.parseInt(countRankNumFive);

        Assert.assertEquals(rankCountOnReviewsPage, rankCountOnItemPage);
        driver.quit();
    }

    @Test(dataProvider = "login-data")
    public void napsatRecenziTest(String login, String pass) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        Thread.sleep(1000);
        String itemRankCount = itemPage.itemRankCount.getText();
        itemRankCount = itemRankCount.substring(0, itemRankCount.length() - 1);
        int rankCountOnItemPage = Integer.parseInt(itemRankCount);
        Thread.sleep(1000);
        itemPage.reviews.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        WebElement napsatRecenziBtn = driver.findElement(By.xpath("//*[@id='recenze']/div[1]/div/div[2]/div[1]/div/div[1]/button"));
        napsatRecenziBtn.click();
        Thread.sleep(3000);
        LoginFormPageFrame loginFormPageFrame = new LoginFormPageFrame(driver);
        driver.switchTo().frame("loginIframe");
        Thread.sleep(2000);
        loginFormPageFrame.inputLogin.sendKeys(login);
        loginFormPageFrame.inputPass.sendKeys(pass);
        Thread.sleep(1000);
        loginFormPageFrame.btnLogin.click();
        Thread.sleep(2000);
        Thread.sleep(3000);
        WebElement radioBtnFive = driver.findElement(By.xpath("//*[@class='MuiRating-label'][5]"));
        Thread.sleep(3000);
        radioBtnFive.click();
        Thread.sleep(2000);
        WebElement anonymBtn = driver.findElement(By.xpath("//*[contains(text(),'Anonymní')]"));
        Thread.sleep(2000);
        anonymBtn.click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        WebElement recenzeIsSubmit = driver.findElement(By.xpath("//*[contains(text(),'Děkujeme za vaši recenzi')]"));
        Assert.assertNotEquals(recenzeIsSubmit, null);
        driver.quit();
    }

    @DataProvider(name = "login-data")
    public Object[][] readCSVData() throws Exception {

        String[][] testData;

        Reader fileInputStream = new FileReader("/Users/Imcheldon/Documents/JAVAPROJECTY/JAVA_PROG/JAVACV/semestralniPraceTestAlza/src/main/resources/login-data.csv");

        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(fileInputStream);


        int numberOfRecords = 1;
        int numberOfColumns = 0;

        for (CSVRecord record : records) {
            numberOfColumns++;
            if(record.get(0).equals("")){
                ++numberOfRecords;
                numberOfColumns = 0;
            }
        }
        testData = new String[numberOfRecords][numberOfColumns];

        int currentRecord = 0;
        fileInputStream = new FileReader("/Users/Imcheldon/Documents/JAVAPROJECTY/JAVA_PROG/JAVACV/semestralniPraceTestAlza/src/main/resources/login-data.csv");
        records = CSVFormat.EXCEL.parse(fileInputStream);
        int i = 0;
        for (CSVRecord record : records) {
            if (record.get(0).equals("")) {
                currentRecord++;
                i = 0;
            } else {
                testData[currentRecord][i] = record.get(0);
                i++;
            }
        }
        return testData;
    }

}
