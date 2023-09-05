package FunctionalTest;
import com.aventstack.extentreports.ExtentReports;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import pageObjects.BanfieldHomePageDEV12;
import pageObjects.LoginPage;
import pageObjects.ShopPage;
import resources.ExtentReporterNG;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listeners(FunctionalTest.Listeners.class)
public class RegisterAccountTest extends base{
    public WebDriver driver;
    static ExtentReports extent;
    private static final Logger log = LogManager.getLogger(base.class);

    @BeforeTest
    public void beforeTest() throws IOException {
        driver = initializeDriver();
        extent = ExtentReporterNG.getReportObject();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        log.info("Driver is initialized");
    }
    @BeforeMethod
    public void setUp(){
        driver.get(properties.getProperty("url1")); // Navigate to Banfield.com
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        BanfieldHomePageDEV12 landingPageInt = new BanfieldHomePageDEV12(driver);
        wait.until(ExpectedConditions.elementToBeClickable(landingPageInt.getLoginPage()));
        landingPageInt.getLoginPage().click();// Click on Login from Landing page
        ShopPage shopPage1 = new ShopPage(driver);
        shopPage1.getCloseCookie().click();
        log.info("...............................");
    }
    @Test(priority = 1)
    public void FRP_1() { // Testing that all elements are presented
        LoginPage loginPage1 = new LoginPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(loginPage1.getLoginCTA()));
        js.executeScript("window.scrollBy(0,250)", "");
        wait.until(ExpectedConditions.elementToBeClickable(loginPage1.getCreateYourAccount()));
        loginPage1.getCreateYourAccount().click();
        js.executeScript("window.scrollBy(0,100)", "");

    }

    @AfterMethod
    public void afterTestCase() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
