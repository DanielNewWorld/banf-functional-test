package FunctionalTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;

import static pageObjects.LoginPage.waitForPageLoad;

public class LoginTest extends base {
    private LoginPage loginPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void logIN() throws IOException {
        loginPage.navigateTo(properties.getProperty("url3")); // go to URL
        waitForPageLoad(driver, timeout);

        int idList = 0;
        int idCredentials = 3;
        User user = loginPage.writeCredentials(properties.getProperty("FILE_PATH"), idList, idCredentials);

        loginPage.clickSignIN(wait);

        loginPage.userLogin(wait, user);
        waitForPageLoad(driver, timeout);

        if (loginPage.allowUserToLogin(wait)) {
            loginPage.userSignOUT(wait);
            waitForPageLoad(driver, timeout);

            if (loginPage.allowUserToLogin(wait)) {
                System.out.println("Processing complete.");
            }
        }
    }
}
