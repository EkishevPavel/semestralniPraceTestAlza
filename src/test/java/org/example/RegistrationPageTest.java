package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.Reader;
import java.time.Duration;

public class RegistrationPageTest {

    @Test(dataProvider = "registration-data")
    void checkRegistrationInputMessages(String email, String pass, String confirmPass, String number, String emailMessage, String passMessage, String passConfirmMessage, String numberMessage) throws InterruptedException {
        if(number.equals("zero")){
            number = "";
        }
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.manage().window().maximize();
        Thread.sleep(1000);
        homePage.acceptCOOOOOOCKIE.click();
        Thread.sleep(1000);
        homePage.logInBtn.click();
        Thread.sleep(4000);
        LoginFormPageFrame loginFormPageFrame = new LoginFormPageFrame(driver);
        driver.switchTo().frame("loginIframe");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        loginFormPageFrame.newRegistration.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        RegistrationPage registrationPage = new RegistrationPage(driver);
        try {
            Thread.sleep(2000);
            registrationPage.email.click();
            registrationPage.email.sendKeys(email);
            Thread.sleep(2000);
            registrationPage.email.sendKeys(Keys.ARROW_RIGHT);
            Thread.sleep(1000);
            registrationPage.email.sendKeys(Keys.BACK_SPACE);
            Thread.sleep(2000);
            registrationPage.email.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            if (!emailMessage.equals("-")) {
                driver.switchTo().activeElement().sendKeys(Keys.ENTER);
                WebElement messageEl = driver.findElement(By.xpath("//*[@id='panel1']/table/tbody/tr[1]/td[2]/div"));
                if (!hasClass(messageEl, "hiddenAlert")) {
                    Assert.assertEquals(messageEl.getText(), emailMessage);
                }
            }
            registrationPage.pass.sendKeys(pass);
            Thread.sleep(3000);
            if (!passMessage.equals("-")) {
                Assert.assertEquals(driver.findElement(By.className("password-verdict")).getText(), passMessage);
            }
            Thread.sleep(3000);
            registrationPage.confirmPass.sendKeys(confirmPass);
            Thread.sleep(4000);
            if (!passConfirmMessage.equals("-")) {
                WebElement passConfirmMessageEl = driver.findElement(By.xpath("//*[@id='blockpsw2']/td[2]/div"));
                Thread.sleep(2000);
                if (!hasClass(passConfirmMessageEl, "hiddenAlert")) {
                    Assert.assertEquals(passConfirmMessageEl.getText(), passConfirmMessage);
                }
            }
            Thread.sleep(2000);
            registrationPage.phoneNum.sendKeys(number);
            registrationPage.confirmPass.click();
            Thread.sleep(2000);
            registrationPage.phoneNum.click();
            Thread.sleep(3000);
            if (!numberMessage.equals("-")) {
                WebElement numberMessageEl = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_cpcm_cpc_ud2_phoneCountryBasicPhoneValidator_blockPhoneCountryValidator']/div[2]"));
                Thread.sleep(2000);
                if (!hasClass(numberMessageEl, "hiddenAlert")) {
                    Assert.assertEquals(numberMessageEl.getText(), numberMessage);
                }
            }
        } finally {
            Thread.sleep(1000);
            driver.quit();
        }
    }

    @DataProvider(name="registration-data")
    public Object[][] readCSVData() throws Exception {

        String[][] testData;


        Reader fileInputStream = new FileReader("/Users/Imcheldon/Documents/JAVAPROJECTY/JAVA_PROG/JAVACV/semestralniPraceTestAlza/src/main/resources/register-data.csv");
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
        fileInputStream = new FileReader("/Users/Imcheldon/Documents/JAVAPROJECTY/JAVA_PROG/JAVACV/semestralniPraceTestAlza/src/main/resources/register-data.csv");
        records = CSVFormat.EXCEL.parse(fileInputStream);
        int i =0;
        for (CSVRecord record : records) {
            if(record.get(0).equals("")) {
                currentRecord++;
                i = 0;
            }else {
                if (record.size() > 1){
                    String fullStr = record.get(0) +
                            "," +
                            record.get(1);
                    testData[currentRecord][i] = fullStr;
                }else{
                    testData[currentRecord][i] = record.get(0);}
                i++;
            }
        }
        return testData;
    }

    public boolean hasClass(WebElement element, String classSearching) {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(classSearching)) {
                return true;
            }
        }
        return false;
    }
}
