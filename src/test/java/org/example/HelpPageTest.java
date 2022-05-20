package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.Reader;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelpPageTest {

    @Test
    public void checkIfHelpPageHasAFewOptions() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.manage().window().maximize();
        Thread.sleep(2000);
        homePage.acceptCOOOOOOCKIE.click();
        driver.findElement(By.xpath("//*[contains(text(),'Napište nám')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        HelpPage helpPage = new HelpPage(driver);// if all elemnts is found - > correct
        Assert.assertNotEquals(helpPage.zbozi, null);
        Assert.assertNotEquals(helpPage.objednavkyAPlatby, null);
        Assert.assertNotEquals(helpPage.reklamace, null);
        Assert.assertNotEquals(helpPage.ostatni, null);
        Assert.assertNotEquals(helpPage.sluzba, null);
        driver.close();
    }

    @Test(dataProvider = "answers-data")
    public void polozitNewDotazDoDiskuze(String[] data) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.manage().window().maximize();
        Thread.sleep(2000);
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        String nameOfAddedItem = itemPage.itemName.getText();
        driver.navigate().back();
        Thread.sleep(2000);
        homePage.acceptCOOOOOOCKIE.click();
        driver.findElement(By.xpath("//*[contains(text(),'Napište nám')]")).click();
        Thread.sleep(2000);
        HelpPage helpPage = new HelpPage(driver);
        helpPage.zbozi.click();
        Thread.sleep(2000);
        driver.findElement(By.id("commodityToFind")).sendKeys(nameOfAddedItem);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(text()," +"\'" + nameOfAddedItem +"\'" + ")]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(text(),'Položit nový dotaz do diskuze')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='discussionPost']")).sendKeys(data[0]);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='userEmail']")).click();
        driver.findElement(By.xpath("//*[@id='userEmail']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='userEmail']")).sendKeys(data[1]);
        driver.findElement(By.xpath("//*[@id='addDiscussionPost']/span[2]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        Thread.sleep(2000);
        WebElement uspech = null;
        try {
            uspech = driver.findElement(By.xpath("//*[contains(text(),'Příspěvek byl úspěšně přidán')]"));
        } catch (Exception e) {
            System.out.println(("neni uspech"));
        }
        if(uspech != null){
            driver.navigate().refresh();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            WebElement el = driver.findElement(By.xpath("//*[contains(text()," + "\'" + data[0] + "\'" + ")]"));
            Thread.sleep(2000);
            assertThat("WebElement is not null", el, is(notNullValue()));
        }
        driver.quit();
    }

    @DataProvider(name="answers-data")
    public Object[][] readCSVData() throws Exception {

        String[][] testData;


        Reader fileInputStream = new FileReader("src/main/resources/answers-data.csv");
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
        fileInputStream = new FileReader("src/main/resources/answers-data.csv");
        records = CSVFormat.EXCEL.parse(fileInputStream);
        int i =0;
        for (CSVRecord record : records) {
            if(record.get(0).equals("")) {
                currentRecord++;
                i = 0;
            }else {
                testData[currentRecord][i] = record.get(0);
                i++;
            }
        }
        return testData;
    }

}
