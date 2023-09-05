package FunctionalTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ForgotPasswordPage;
import pageObjects.LoginPage;
import pageObjects.User;

import java.io.IOException;

import static pageObjects.LoginPage.waitForPageLoad;

public class ForgotPasswordTest extends base {
    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test(priority = 1)
    public void logIN() throws IOException {
        loginPage.navigateTo(properties.getProperty("url3")); // go to URL
        waitForPageLoad(driver, timeout);

        loginPage.clickSignIN(wait);

        forgotPasswordPage.clickForgotPassword(wait);

        int idList = 0;
        int idCredentials = 3;
        User user = loginPage.writeCredentials(properties.getProperty("FILE_PATH"), idList, idCredentials);

        forgotPasswordPage.inputEMail(wait, user.getLogin());

        forgotPasswordPage.resetLink(wait);
        waitForPageLoad(driver, timeout);

        System.out.println("Processing complete.");
    }
}
