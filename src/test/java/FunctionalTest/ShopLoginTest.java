package FunctionalTest;
import com.aventstack.extentreports.ExtentReports;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import pageObjects.ShopPage;
import resources.ExtentReporterNG;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(FunctionalTest.Listeners.class)
public class ShopLoginTest extends base {

    public WebDriver driver;
    static ExtentReports extent;
    private static final Logger log = LogManager.getLogger(base.class);
    public ShopPage shopPage;

    @BeforeTest
    public void beforeTest() throws IOException {
        driver = initializeDriver();
        extent = ExtentReporterNG.getReportObject();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        log.info("Driver is initialized");
    }
    @BeforeMethod
    public void setUp() {
        driver.get(properties.getProperty("url2"));
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        shopPage = new ShopPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getTopNavSignInPage()));
        shopPage.getTopNavSignInPage().click();
        log.info("....................................");
    }
    @Test(priority = 1)
    public void FLP_1() { // Testing that all elements are presented
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertEquals(shopPage.getUsernameBox().getAttribute("placeholder"), "Enter email or username"); // Verify Email
        Assert.assertEquals(shopPage.getPasswordBox().getAttribute("placeholder"), "Enter password"); // Verify Password
        Assert.assertTrue(shopPage.getEyeIcon().getAttribute("class").contains("eye")); // Verify Eye icon
        Assert.assertEquals(shopPage.getRememberMe().getAttribute("name"), "RememberMe"); // Verify Remember me
        Assert.assertEquals(shopPage.getLoginBtn().getAttribute("value"), "Log in"); // Verify Login
        List<WebElement> needHelpLink = Collections.singletonList(shopPage.getNeedHelpLink());
        Assert.assertEquals(needHelpLink.get(0).getAttribute("title"), "Forgot passowrd"); // Verify Need help
        Assert.assertEquals(shopPage.getCreateUrAccountPage().getAttribute("title"), "Sign up");
        log.info("FLP-1 Pass");
    }
    @Test(priority = 2)
    public void FLP_2() { // Test that username and password focused when used
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getCloseCookie().click();
        shopPage.getUsernameBox().sendKeys("");
        new Actions(driver).moveToElement(shopPage.getUsernameBox(), 414, 45).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='Password']")));
        shopPage.getPasswordBox().sendKeys("");
        new Actions(driver).moveToElement(shopPage.getPasswordBox(), 414, 45).perform();
        log.info("FLP-2 Pass");
    }
    @Test(priority = 3) // Test that user can use TAB/ENTER on web elements
    public void FLP_4() throws InterruptedException {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().click();
        WebElement currentElement1 = driver.switchTo().activeElement();
        Thread.sleep(2000);
        actions.sendKeys(Keys.TAB).build().perform(); // Click TAB to get PASSWORD field
        WebElement currentElement2 = driver.switchTo().activeElement();
        Thread.sleep(2000);
        actions.sendKeys(Keys.TAB).build().perform();// Click TAB to get REMEMBER ME checkbox field
        WebElement currentElement3 = driver.switchTo().activeElement();
        Thread.sleep(2000);
        actions.sendKeys(Keys.TAB).build().perform(); // Click TAB to get LOGIN and click ENTER
        WebElement currentElement4 = driver.switchTo().activeElement();
        Thread.sleep(1000);
        currentElement4.sendKeys(Keys.RETURN);
        Assert.assertEquals(currentElement1.getAttribute("placeholder"), "Enter email or username");
        Assert.assertEquals(currentElement2.getAttribute("placeholder"), "Enter password");
        Assert.assertEquals(currentElement3.getAttribute("name"), "RememberMe");
        Assert.assertEquals(currentElement4.getAttribute("value"), "Log in"); // Verify Login
        Assert.assertEquals(driver.findElement(By.id("UserName-error")).getText(), "This field is required.");
        log.info("FLP-4 Pass");
    }
    @Test(priority = 4)
    public void FLP_5(){ // Test that user is able to log in with valid input
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        shopPage.getPasswordBox().sendKeys("Banfield1234!"); //Enter Password
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        Assert.assertEquals(shopPage.getWelcomeUser().getText(), "Welcome Rovik"); // See if User login is successful
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getHamburgerIcon()));
        shopPage.getHamburgerIcon().click();
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getHamLogOutBtn()));
        shopPage.getHamLogOutBtn().click();
        log.info("FLP-5 Pass");
    }
    @Test(priority = 5)
    public void FLP_6(){ // Invalid username and pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik"); //Enter invalid Username
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        shopPage.getPasswordBox().sendKeys("Banfi"); //Enter invalid Password
        wait.until(ExpectedConditions.textToBePresentInElementValue(shopPage.getPasswordBox(),"Banfi"));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation-summary-errors ul li")));
        Assert.assertEquals(driver.findElement(By.cssSelector(".validation-summary-errors ul li")).getText(), "Unable to log in. Check your info and try again?"); // See if User login is successful
        log.info("FLP-6 Pass");
    }
    @Test(priority = 6)
    public void FLP_7(){ // valid username and invalid pass
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        wait.until(ExpectedConditions.textToBePresentInElementValue(shopPage.getUsernameBox(),"rovik@test.com"));
        shopPage.getPasswordBox().sendKeys("Banfield123"); //Enter invalid Password
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation-summary-errors ul li")));
        Assert.assertEquals(driver.findElement(By.cssSelector(".validation-summary-errors ul li")).getText(), "Unable to log in. Check your info and try again?"); // See if User login is successful
        log.info("FLP-7 Pass");
    }
    @Test(priority = 7)
    public void FLP_8(){ // valid username and invalid pass
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@testcom"); //Enter Username
        wait.until(ExpectedConditions.textToBePresentInElementValue(shopPage.getUsernameBox(),"rovik@testcom"));
        shopPage.getPasswordBox().sendKeys("Banfield123!"); //Enter invalid Password
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()))   ;
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation-summary-errors ul li")));
        Assert.assertEquals(driver.findElement(By.cssSelector(".validation-summary-errors ul li")).getText(), "Unable to log in. Check your info and try again?");
        log.info("FLP-8 Pass");
    }
    @Test(priority = 8)
    public void FLP_9() { // empty
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#UserName-error")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#UserName-error")).getText(), "This field is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "This field is required.");
        log.info("FLP-9 Pass");
    }
    @Test(priority = 9)
    public void FLP_10(){ // click browser back button after login
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        shopPage.getPasswordBox().sendKeys("Banfield1234!"); //Enter Password
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        Assert.assertEquals(shopPage.getWelcomeUser().getText(), "Welcome Rovik");
        driver.navigate().back();
        Assert.assertEquals(driver.findElement(By.cssSelector("[class='desktoplogin desktop-login-padding']")).getText(), "Welcome Rovik");
        driver.navigate().forward();
        shopPage.getHamburgerIcon().click();
        shopPage.getHamLogOutBtn().click();
        log.info("FLP-10 Pass");
    }
    @Test(priority = 10)
    public void FLP_11(){ // input is shown with bullets
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        shopPage.getPasswordBox().sendKeys("Banfield1234!"); //Enter Password
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        Assert.assertEquals(shopPage.getPasswordBox().getAttribute("type"), "password");
        log.info("FLP-11 Pass");
    }
    @Test(priority = 11)
    public void FLP_12() throws InterruptedException { // checking eye icon
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        shopPage.getPasswordBox().sendKeys("Banfield1234!"); //Enter Password
        Thread.sleep(2000);
        new Actions(driver).clickAndHold(shopPage.getEyeIcon()).perform();
        wait.until(ExpectedConditions.textToBePresentInElementValue(shopPage.getPasswordBox(), "Banfield1234!"));
        Assert.assertEquals(shopPage.getPasswordBox().getAttribute("type"), "text");
        log.info("FLP-12 Pass");
    }
    @Test(priority = 12)
    public void FLP_14() { // checking Remember me box
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        Assert.assertFalse(shopPage.getRememberMe().isSelected());
        log.info("FLP-14 Pass");
    }
    @Test(priority = 13)
    public void FLP_15(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        wait.until(ExpectedConditions.textToBePresentInElementValue(shopPage.getUsernameBox(),"rovik@test.com"));
        shopPage.getPasswordBox().sendKeys("Banfield1234!"); //Enter invalid Password
        shopPage.getRememberMe().sendKeys(Keys.SPACE);
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        shopPage.getHamburgerIcon().click();
        shopPage.getHamLogOutBtn().click();
        shopPage.getTopNavSignInPage().click();
        Assert.assertEquals(shopPage.getUsernameBox().getAttribute("value"), "rovik@test.com");
        log.info("FLP-15 Pass");
    }
    @Test(priority = 14)
    public void FLP_17(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        wait.until(ExpectedConditions.textToBePresentInElementValue(shopPage.getUsernameBox(),"rovik@test.com"));
        shopPage.getPasswordBox().sendKeys("Banfield1234!"); //Enter invalid Password
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        shopPage.getHamburgerIcon().click();
        shopPage.getHamLogOutBtn().click();
        Assert.assertEquals(shopPage.getMyOrders().getText(), "My orders");
        log.info("FLP-17 Pass");
    }
    @Test(priority = 15)
    public void FLP_18(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserName']")));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 250)");
        shopPage.getNeedHelpLink().click();
        Assert.assertEquals(shopPage.getSendMyResetLink().getAttribute("value"), "Send my reset link");
        log.info("FLP-18 Pass");
    }
    @Test(priority = 16)
    public void FLP_19(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserName']")));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 250)");
        shopPage.getCreateUrAccountPage().click();
        Assert.assertEquals(shopPage.getClientID().getAttribute("placeholder"), "Ex: BNF123B");
        log.info("FLP-19 Pass");
    }
    @Test(priority = 17)
    public void FLP_21(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserName']")));
        shopPage.getUsernameBox().sendKeys("rovik@test.com"); //Enter Username
        shopPage.getPasswordBox().sendKeys("Banfield1234!"); //Enter Password
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getLoginBtn()));
        shopPage.getLoginBtn().sendKeys(Keys.RETURN); //Click Login
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getHamburgerIcon()));
        shopPage.getHamburgerIcon().click();
        wait.until(ExpectedConditions.elementToBeClickable(shopPage.getHamLogOutBtn()));
        shopPage.getHamLogOutBtn().click();
        driver.navigate().back();
        //Assert.assertEquals(driver.findElement(By.xpath("//*[@id='main-nav-login']/text()")).getText(), "Sign in");
        log.info("FLP-21 Pass");
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
