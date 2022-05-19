package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='h1c']/h1")
    WebElement itemName;

    @FindBy(xpath = "//*[@id='cpcm_cpc_tdAmount']/span/div[1]/div")
    WebElement buyItem;

    @FindBy(how = How.CLASS_NAME, using = "price_withVat")
    WebElement price;

    @FindBy(xpath = "//*[@id='reviews-tab']")
    WebElement reviews;


    @FindBy(xpath = "//*[@id='itemRankContainer']/span")
    WebElement itemRankCount;




    public ItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
