package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryAndPaymentPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[contains(text(),'\n" + "Doručení na adresu')]")
    WebElement deliveryToAddressBtn;

    @FindBy(xpath = "//*[@id='mui-87090']")
    WebElement inputTown;


    public DeliveryAndPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
