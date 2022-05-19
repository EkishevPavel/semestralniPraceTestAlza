package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;



import java.io.FileReader;
import java.io.Reader;
import java.time.Duration;


//test if search is works, check if the first search result is correct
public class SearchItemTest {


    @BeforeTest
    public void createData() throws InterruptedException {
        ProcessNakup processNakup = new ProcessNakup();
        processNakup.processThreeFirstItems();
    }

    @Test(dataProvider = "item-names-data")
    public void testSearch(String[] itemNames) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.manage().window().maximize();
        Thread.sleep(2000);
        homePage.acceptCOOOOOOCKIE.click();
        homePage.inputSearchText.sendKeys(itemNames[0]);
        Thread.sleep(1000);
        homePage.searchBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        SearchPage searchPage = new SearchPage(driver);
        searchPage.firstItemLink.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        Assert.assertEquals(itemPage.itemName.getText(), itemNames[0]);
        Thread.sleep(2000);
        homePage.inputSearchText.sendKeys(itemNames[1]);
        homePage.searchBtn.click();
        Thread.sleep(2000);
        searchPage.firstItemLink.click();
        Assert.assertEquals(itemPage.itemName.getText(), itemNames[1]);
        Thread.sleep(2000);
        homePage.inputSearchText.sendKeys(itemNames[2]);
        homePage.searchBtn.click();
        Thread.sleep(2000);
        searchPage.firstItemLink.click();
        Assert.assertEquals(itemPage.itemName.getText(), itemNames[2]);
        driver.quit();
    }



    @DataProvider(name="item-names-data")
    public Object[][] readCSVData() throws Exception {

        String[][] testData;


        Reader fileInputStream = new FileReader("src/main/resources/item-names-for-search.csv");
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
        fileInputStream = new FileReader("src/main/resources/item-names-for-search.csv");
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
