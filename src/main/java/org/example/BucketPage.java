package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.Test;

import java.io.*;

public class BucketPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='orderpage']/div[3]/div[10]/a")
    WebElement linkToHomePage;

    @FindBy(xpath = "//*[@id='blockBtnRight']/a")
    WebElement linkToNextStep;

    @FindBy(xpath = "//*[@id='o1basket']/table/tbody/tr[1]/td[6]")
    WebElement price;

    @FindBy(xpath = "//*[@class='countEdit']/input")
    WebElement inputItemsCount;



    public BucketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/Imcheldon/Documents/JAVAPROJECTY/TESTOVANISOFT/chromedriver");
        WebDriver driver = new ChromeDriver();
        BucketPage bucketPage = new BucketPage(driver);
    }
}
