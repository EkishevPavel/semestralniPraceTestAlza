package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='lblLogin']")
    WebElement logInBtn;
//
    @FindBy(how = How.CLASS_NAME, using = "js-cookies-info-accept")
    WebElement acceptCOOOOOOCKIE;



    @FindBy(xpath = "//*[@id='basketc']")
    WebElement bucketBtn;

    @FindBy(xpath = "//*[@id='btnSearch']")
    WebElement searchBtn;

    @FindBy(xpath = "//*[@id='edtSearch']")
    WebElement inputSearchText;

    @FindBy(xpath = "//*[@id='ltp']/div[3]/div/div/div[2]/div/div[2]/div/div[1]")
    WebElement firstSwapEl;
    @FindBy(xpath = "//*[@id='ltp']/div[3]/div/div/div[2]/div/div[2]/div/div[2]")
    WebElement secondSwapEl;
    @FindBy(xpath = "//*[@id='ltp']/div[3]/div/div/div[2]/div/div[2]/div/div[3]")
    WebElement thirdSwapEl;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.alza.cz/");
        PageFactory.initElements(driver, this);
    }
}