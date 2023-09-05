package FunctionalTest;
import com.aventstack.extentreports.ExtentReports;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import pageObjects.BanfieldHomePageDEV12;
import pageObjects.LoginPage;
import pageObjects.MyBanfieldPage;
import resources.ExtentReporterNG;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listeners(FunctionalTest.Listeners.class)
public class MainLoginTest extends base {
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
        log.info("...............................");
    }
    @Test(priority = 1)
    public void FLP_1(){ // Testing that all elements are presented
        LoginPage loginPage1 = new LoginPage(driver);
        Assert.assertEquals(loginPage1.getUsernameBox().getAttribute("placeholder"), "Enter email or username"); // Verify Email
        Assert.assertEquals(loginPage1.getPasswordBox().getAttribute("placeholder"), "Enter password"); // Verify Password
        Assert.assertTrue(loginPage1.getEyeIcon().getAttribute("class").contains("eye")); // Verify Eye icon
        Assert.assertEquals(loginPage1.getRememberMeCheckBx().getAttribute("name"), "RememberMe"); // Verify Remember me
        Assert.assertEquals(loginPage1.getLoginCTA().getAttribute("value"), "Log in"); // Verify Login
        Assert.assertEquals(loginPage1.getNeedHelp().getAttribute("title"), "Forgot passowrd"); // Verify Need help
        Assert.assertEquals(loginPage1.getCreateYourAccount().getAttribute("title"), "Sign up");
        log.info("FLP-1 Pass");
    }
    @Test(priority = 2)
    public void FLP_2(){ // Test that username and password are focused when used
        LoginPage loginPage2 = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#usernamepopup")));
        loginPage2.getUsernameBox().sendKeys("");
        new Actions(driver).moveToElement(loginPage2.getUsernameBox(),414, 45).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#passwordpopup")));
        loginPage2.getPasswordBox().sendKeys("");
        new Actions(driver).moveToElement(loginPage2.getPasswordBox(),414, 45).perform();
        log.info("FLP-2 Pass");
    }
    @Test(priority = 3) // Test that user can use TAB/ENTER on web elements
    public void FLP_4() throws InterruptedException {
        Actions actions = new Actions(driver);
        LoginPage loginPage4 = new LoginPage(driver);
        Thread.sleep(4000);
        actions.sendKeys(Keys.TAB).build().perform();
        WebElement currentElement1 = driver.switchTo().activeElement();
        actions.sendKeys(Keys.TAB).build().perform(); // Click TAB to get PASSWORD field
        WebElement currentElement2 = driver.switchTo().activeElement();
        Thread.sleep(2000);
        actions.sendKeys(Keys.TAB).build().perform();// Click TAB to get REMEMBER ME checkbox field
        WebElement currentElement3 = driver.switchTo().activeElement();
        Thread.sleep(2000);
        actions.sendKeys(Keys.TAB).build().perform(); // Click TAB to get LOGIN and click ENTER
        WebElement currentElement4 = driver.switchTo().activeElement();
        wait.until(ExpectedConditions.elementToBeClickable(loginPage4.getLoginCTA()));
        actions.sendKeys(Keys.ENTER).build().perform();//currentElement4.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        Assert.assertEquals(currentElement1.getAttribute("placeholder"), "Enter email or username");
        Assert.assertEquals(currentElement2.getAttribute("placeholder"), "Enter password");
        Assert.assertEquals(currentElement3.getAttribute("name"), "RememberMe");
        Assert.assertEquals(currentElement4.getAttribute("value"), "Log in"); // Verify Login
        Assert.assertEquals(driver.findElement(By.cssSelector("#usernamepopup-error")).getText(), "This field is required.");
        log.info("FLP-4 Pass");
    }
    @Test(priority = 4)
    public void FLP_5(){ // Test that user is able to log in with valid input
        LoginPage loginPage5 = new LoginPage(driver);
        MyBanfieldPage myBanfieldPage5 = new MyBanfieldPage(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usernamepopup")));
        loginPage5.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        loginPage5.getPasswordBox().sendKeys("Banfield1234!"); //Enter Password
        wait.until(ExpectedConditions.elementToBeClickable(loginPage5.getLoginCTA()));
        loginPage5.getLoginCTA().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.elementToBeClickable(myBanfieldPage5.getSeeFullProfile()));
        Assert.assertEquals(myBanfieldPage5.getWelcomeUser().getText(), "Welcome Rovik"); // See if User login is successful
        wait.until(ExpectedConditions.elementToBeClickable(myBanfieldPage5.getHamburgerIcon()));
        myBanfieldPage5.getHamburgerIcon().click();
        wait.until(ExpectedConditions.elementToBeClickable(myBanfieldPage5.getLogOutBtn()));
        myBanfieldPage5.getLogOutBtn().click();
        log.info("FLP-5 Pass");
    }
    @Test(priority = 5)
    public void FLP_6(){ // Invalid username and pass
        LoginPage loginPage6 = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#usernamepopup")));
        loginPage6.getUsernameBox().sendKeys("rovik"); //Enter invalid Username
        wait.until(ExpectedConditions.elementToBeClickable(loginPage6.getLoginCTA()));
        loginPage6.getPasswordBox().sendKeys("Banfi"); //Enter invalid Password
        wait.until(ExpectedConditions.textToBePresentInElementValue(loginPage6.getPasswordBox(),"Banfi"));
        loginPage6.getLoginCTA().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#errorUserNotFound")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#errorUserNotFound")).getText(), "Unable to log in. Check your info and try again?"); // See if User login is successful
        log.info("FLP-6 Pass");
    }
    @Test(priority = 6)
    public void FLP_7(){ // valid username and invalid pass
        LoginPage loginPage7 = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#usernamepopup")));
        loginPage7.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        wait.until(ExpectedConditions.textToBePresentInElementValue(loginPage7.getUsernameBox(),"rovik@test.com"));
        loginPage7.getPasswordBox().sendKeys("Banfield123"); //Enter invalid Password
        wait.until(ExpectedConditions.elementToBeClickable(loginPage7.getLoginCTA()));
        loginPage7.getLoginCTA().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#errorUserNotFound")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#errorUserNotFound")).getText(), "Unable to log in. Check your info and try again?"); // See if User login is successful
        log.info("FLP-7 Pass");
    }
    @Test(priority = 7)
    public void FLP_8(){ // valid username and invalid pass
        LoginPage loginPage8 = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#usernamepopup")));
        loginPage8.getUsernameBox().sendKeys("rovik@testcom"); //Enter Username
        wait.until(ExpectedConditions.textToBePresentInElementValue(loginPage8.getUsernameBox(),"rovik@testcom"));
        loginPage8.getPasswordBox().sendKeys("Banfield123!"); //Enter invalid Password
        wait.until(ExpectedConditions.elementToBeClickable(loginPage8.getLoginCTA()))   ;
        loginPage8.getLoginCTA().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#errorUserNotFound")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#errorUserNotFound")).getText(), "Unable to log in. Check your info and try again?");
        log.info("FLP-8 Pass");
    }
    @Test(priority = 8)
    public void FLP_9(){ // empty fields
        LoginPage loginPage9 = new LoginPage(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usernamepopup")));
        loginPage9.getLoginCTA().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#usernamepopup-error")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#usernamepopup-error")).getText(), "This field is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#passwordpopup-error")).getText(), "This field is required.");
        log.info("FLP-9 Pass");
    }
    @Test(priority = 9)
    public void FLP_10(){ // click browser back button after login
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        LoginPage loginPage10 = new LoginPage(driver);
        MyBanfieldPage myBanfieldPage10 = new MyBanfieldPage(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usernamepopup")));
        loginPage10.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        wait.until(ExpectedConditions.elementToBeClickable(loginPage10.getLoginCTA()));
        loginPage10.getPasswordBox().sendKeys("Banfield1234!"); //Enter Password
        loginPage10.getLoginCTA().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.elementToBeClickable(myBanfieldPage10.getSeeFullProfile()));
        Assert.assertEquals(myBanfieldPage10.getWelcomeUser().getText(), "Welcome Rovik"); // See if User login is successful
        driver.navigate().back();
        Assert.assertEquals(myBanfieldPage10.getWelcomeUser().getText(), "Welcome Rovik");
        myBanfieldPage10.getHamburgerIcon().click();
        myBanfieldPage10.getLogOutBtn().click();
        log.info("FLP-10 Pass");
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
