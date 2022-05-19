package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BucketPageTest {
    public BucketPageTest() {
    }


    //check jestli item, ktere jste pridal je stejny jako v kosiku
    @Test
    void addItemToBucket() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        String nameOfAddedItem = itemPage.itemName.getText();
        String priceOfAddedItem = itemPage.price.getText();
        Thread.sleep(1000);

        itemPage.buyItem.click();
        homePage.bucketBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        BucketPage bucketPage = new BucketPage(driver);
        WebElement linkToItemInBucket;
        try {
            linkToItemInBucket = driver.findElement(By.xpath("//*[contains(text()," +"\'" + nameOfAddedItem +"\'" + ")]"));
        }catch (Exception e){ throw
                new AssertionError(e);
        }

        Thread.sleep(2000);
        linkToItemInBucket.click();

        String nameOfItemInBucketFromItemPage = itemPage.itemName.getText();
        String priceOfItemInBucketFromItemPage = itemPage.price.getText();

        Assert.assertEquals(nameOfItemInBucketFromItemPage, nameOfAddedItem); // test if selected item is added
        Assert.assertEquals(priceOfItemInBucketFromItemPage, priceOfAddedItem);
        driver.quit();
    }

    @Test
    void deleteItemFromBucket() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(1000);
        homePage.firstSwapEl.click();
        ItemPage itemPage = new ItemPage(driver);
        Thread.sleep(2000);
        itemPage.buyItem.click();
        homePage.bucketBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.className("countMinus")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='alzaDialog']/div[3]/div/div[2]/span[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(), 'Jsem tak prázdný...' )]")).getText(),"Jsem tak prázdný...");
        driver.quit();
    }



    @Test
    void checkIfPriceRisesCorrectly() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        String priceOfAddedItem = itemPage.price.getText();
        Thread.sleep(2000);
        itemPage.buyItem.click();
        homePage.bucketBtn.click();
        Thread.sleep(3000);
        BucketPage bucketPage = new BucketPage(driver);
        Thread.sleep(1000);
        driver.findElement(By.className("countPlus")).click();
        Thread.sleep(3000);
        String currentPrice = bucketPage.price.getText();
        priceOfAddedItem = priceOfAddedItem.substring(0, priceOfAddedItem.length() - 2);
        currentPrice = currentPrice.substring(0, currentPrice.length() - 3);
        priceOfAddedItem = priceOfAddedItem.replaceAll(" ", "");
        int expectedPrice = Integer.parseInt(priceOfAddedItem);
        currentPrice = currentPrice.replaceAll(" ", "");
        int actualPrice = Integer.parseInt(currentPrice);
        expectedPrice *=2;
        Assert.assertEquals(actualPrice, expectedPrice);
        driver.quit();
    }
    @Test
    void checkIfPriceDecreasesCorrectly() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        String priceOfAddedItem = itemPage.price.getText();
        Thread.sleep(1000);
        itemPage.buyItem.click();
        homePage.bucketBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        BucketPage bucketPage = new BucketPage(driver);
        Thread.sleep(1000);
        driver.findElement(By.className("countPlus")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("countPlus")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("countMinus")).click();
        Thread.sleep(2000);
        String currentPrice = bucketPage.price.getText();
        priceOfAddedItem = priceOfAddedItem.substring(0, priceOfAddedItem.length() - 2);
        currentPrice = currentPrice.substring(0, currentPrice.length() - 3);
        priceOfAddedItem = priceOfAddedItem.replaceAll(" ", "");
        int expectedPrice = Integer.parseInt(priceOfAddedItem);
        currentPrice = currentPrice.replaceAll(" ", "");
        int actualPrice = Integer.parseInt(currentPrice);
        expectedPrice *=2;
        Assert.assertEquals(actualPrice, expectedPrice);
        driver.quit();
    }

    @Test
    void testTryToBuyWithBigNum() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        String priceOfAddedItem = itemPage.price.getText();
        Thread.sleep(2000);
        itemPage.buyItem.click();
        homePage.bucketBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        BucketPage bucketPage = new BucketPage(driver);
        Thread.sleep(1000);
        String bigNum = "1000000000";
        bucketPage.inputItemsCount.sendKeys(bigNum);
        bucketPage.inputItemsCount.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
        WebElement informacniHlaska = driver.findElement(By.xpath("//*[contains(text(),'Informace')]")); // vyskoci hlaska INFORMACE (ze input je moc velky)
        assertThat("WebElement is not null", informacniHlaska, is(notNullValue()));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(text(),'Rozumím')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        Assert.assertNotEquals(bucketPage.inputItemsCount.getText(), bigNum);
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    void checkIfExistMultipleDeliveryOptions() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.manage().window().maximize();
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        itemPage.buyItem.click();
        homePage.bucketBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        BucketPage bucketPage = new BucketPage(driver);
        Thread.sleep(2000);
        bucketPage.linkToNextStep.click();
        Thread.sleep(2000);
        WebElement deliveryToAdress = driver.findElement(By.xpath("//*[contains(text(),'\n" + "Doručení na adresu' )]"));
        WebElement deliveryAlzaBox = driver.findElement(By.xpath("//*[contains(text(),'AlzaBox - nonstop vyzvednutí' )]"));
        WebElement deliveryProdejny = driver.findElement(By.xpath("//*[contains(text(),'Prodejny a odběrná místa' )]"));

        assertThat("deliveryToAdress is not null", deliveryToAdress, is(notNullValue()));
        assertThat("deliveryAlzaBox is not null", deliveryAlzaBox, is(notNullValue()));
        assertThat("deliveryProdejny is not null", deliveryProdejny, is(notNullValue()));
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    void checkIfExistMultiplePaymentOptions() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.manage().window().maximize();
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(2000);
        homePage.firstSwapEl.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        ItemPage itemPage = new ItemPage(driver);
        itemPage.buyItem.click();
        homePage.bucketBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        BucketPage bucketPage = new BucketPage(driver);
        Thread.sleep(2000);
        bucketPage.linkToNextStep.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(text(),'\n" + "Doručení na adresu' )]")).click();
        Thread.sleep(3000);
        driver.switchTo().activeElement().sendKeys("17000");
        Thread.sleep(1000);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Thread.sleep(1000);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Thread.sleep(2000);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        WebElement zvoltePlatbuEl = driver.findElement(By.xpath("//*[contains(text(),'Zvolte platbu' )]"));
        WebElement kartouOnline = driver.findElement(By.xpath("//*[contains(text(),'Kartou online' )]"));
        WebElement googlePay = driver.findElement(By.xpath("//*[contains(text(),'Google Pay' )]"));
        WebElement kartouKuryrovi = driver.findElement(By.xpath("//*[contains(text(),'Kartou kurýrovi' )]"));

        assertThat("zvoltePlatbuEl is not null", zvoltePlatbuEl, is(notNullValue()));
        assertThat("kartouOnline is not null", kartouOnline, is(notNullValue()));
        assertThat("googlePay is not null", googlePay, is(notNullValue()));
        assertThat("kartouKuryrovi is not null", kartouKuryrovi, is(notNullValue()));
        Thread.sleep(6000);
        driver.quit();


    }
    
    
    
    

}
