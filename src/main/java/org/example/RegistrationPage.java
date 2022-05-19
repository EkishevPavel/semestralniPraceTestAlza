package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='edth1EmailLogin']")
    WebElement email;

    @FindBy(xpath = "//*[@id='edth1Password']")
    WebElement pass;

    @FindBy(xpath = "//*[@id='edth1PasswordConfirm']")
    WebElement confirmPass;

    @FindBy(xpath = "//*[@id='ctl00_ctl00_cpcm_cpc_ud2_phoneCountryBasicPhoneValidator_inpTelNumber']")
    WebElement phoneNum;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
