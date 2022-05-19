package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeliveryFormPage {
    private WebDriver driver;


    @FindBy(xpath = "//*[@id='rootHtml']/body/div[19]/div[3]/div/div/form[1]/div/div/div")
    WebElement inputTownOrPostalCode;


    public DeliveryFormPage(WebDriver driver) {
        this.driver = driver;
    }
}
