package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    public WebDriver driver;
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    By forgotPasswordXPath = By.xpath("//a[@class='forgot-password orange-link orange-link-none']");
    By inputEMailXPath = By.xpath("//input[@id='txtForgotPasswordEmail']");
    By resetLinkXPath = By.xpath("//input[@type='submit' and @class='btn orange-outlined send-password-button-btn' and @value='Send my reset link' and @id='btnSendResetPassword']");
    By resultsResetLinkXPath = By.xpath("//span[text()='Sent! Look for an email with a password reset link in your inbox soon.']");
    public WebElement getForgotPassword() { return driver.findElement(forgotPasswordXPath); }
    public WebElement getInputEMail() { return driver.findElement(inputEMailXPath); }
    public WebElement getResetLink() { return driver.findElement(resetLinkXPath); }
    public WebElement getResultsResetLink() { return driver.findElement(resultsResetLinkXPath); }

    public void clickForgotPassword(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOf(getForgotPassword()));
            getForgotPassword().click();

            System.out.println("Click 'Need Help'");
        } catch (Exception e) {
            System.out.println("---Error. Not visibled locator: " + e);
        }
    }

    public void inputEMail(WebDriverWait wait, String login) {
        try {
            wait.until(ExpectedConditions.visibilityOf(getInputEMail()));
            getInputEMail().sendKeys(login);

            System.out.println("input E-Mail");
        } catch (Exception e) {
            System.out.println("---Error. Not visibled locator: " + e);
        }
    }

    public void resetLink(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOf(getResetLink()));
            getResetLink().click();
            System.out.println("sending reset link...");

            wait.until(ExpectedConditions.visibilityOf(getResultsResetLink()));
            if (getResultsResetLink().isDisplayed()) {
                System.out.println("Reset link was sent.");
            } else {
                System.out.println("---Error. Reset link was not sent.");
            }

        } catch (Exception e) {
            System.out.println("---Error. Not visibled locator: " + e);
        }
    }
}
