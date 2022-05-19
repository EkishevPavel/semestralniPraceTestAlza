package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginFormPageFrame {
    private WebDriver driver;
    @FindBy(xpath = "//*[@id='userName']")
    WebElement inputLogin;

    @FindBy(xpath = "//*[@id='password']")
    WebElement inputPass;

    @FindBy(xpath = "//*[@id='btnLogin']")
    WebElement btnLogin;

    @FindBy(xpath = "//*[@id='registerLink']")
    WebElement newRegistration;

    public LoginFormPageFrame(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
