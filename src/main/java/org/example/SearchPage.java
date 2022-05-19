package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='boxes']/div[1]/div[1]/div[2]/a")
    WebElement firstItemLink;
    @FindBy(xpath = "//*[@id='boxes']/div[2]/div[1]/div[2]/a")
    WebElement secondItemLink;
    @FindBy(xpath = "//*[@id='boxes']/div[3]/div[1]/div[2]/a")
    WebElement thirdItemLink;
    @FindBy(xpath = "//*[@id='ui-id-3']")
    WebElement btnSortingByPrice;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
