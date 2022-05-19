package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HelpPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='contactFormNavigation']/div[2]/a[1]")
    WebElement zbozi;

    @FindBy(xpath = "//*[@id='contactFormNavigation']/div[2]/a[2]")
    WebElement objednavkyAPlatby;

    @FindBy(xpath = "//*[@id='contactFormNavigation']/div[2]/a[3]")
    WebElement reklamace;

    @FindBy(xpath = "//*[@id='contactFormNavigation']/div[2]/a[4]")
    WebElement sluzba;

    @FindBy(xpath = "//*[@id='contactFormNavigation']/div[2]/a[5]")
    WebElement ostatni;


    public HelpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
