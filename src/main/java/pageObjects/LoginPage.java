package pageObjects;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    By hamburgerIcon = By.cssSelector(".navbar-toggler-icon>img:nth-child(1)");
    By loginCTA = By.xpath("//*[@id='btnLoginPopup']");
    By usernameBox = By.cssSelector("#usernamepopup");
    By passwordBox = By.cssSelector("#passwordpopup");
    By passwordEyeIcon = By.cssSelector(".fa.fa-fw.field-icon.toggle-password.fa-eye");
    By rememberMeCheckBx = By.cssSelector("#chkRememberPopup");
    By needHelpLink = By.xpath("//*[@class='forgot-password orange-link orange-link-none']");
    By createYourAccountLink = By.xpath("//*[@class='signup orange-link orange-link-none']");
    By signIN = By.xpath("//a[@class='nav-link' and @data-toggle='modal' and @data-target='#signInModal']");
    By userSignIN = By.xpath("//a[@class='nav-link' and @data-toggle='dropdown' and @aria-haspopup='true' and @aria-expanded='false' and not(@id='langdropdown')]");
    By signOUT = By.xpath("//a[@id='lnkSignout' and contains(@class, 'dropdown-item logoutsubmit')]");

    public WebElement getHamburgerIcon() { return driver.findElement(hamburgerIcon); }

    public WebElement getLoginCTA() { return driver.findElement(loginCTA); }

    public WebElement getUsernameBox() { return driver.findElement(usernameBox); }

    public WebElement getPasswordBox() { return driver.findElement(passwordBox); }

    public WebElement getEyeIcon(){ return driver.findElement(passwordEyeIcon); }

    public WebElement getRememberMeCheckBx(){ return driver.findElement(rememberMeCheckBx); }

    public WebElement getNeedHelp(){ return driver.findElement(needHelpLink); }

    public WebElement getCreateYourAccount(){ return driver.findElement(createYourAccountLink); }
    public WebElement getSignIN(){ return driver.findElement(signIN); }
    public WebElement getUserSignIN(){ return driver.findElement(userSignIN); }
    public WebElement getUserSignOUT(){ return driver.findElement(signOUT); }

    public void navigateTo(String url) {
        driver.get(url);
        System.out.println("web site running...");
    }

    public static void waitForPageLoad(WebDriver driver, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until((ExpectedCondition<Boolean>) wd ->
                wd != null && "complete".equals(((JavascriptExecutor) wd).executeScript("return document.readyState"))
        );
    }

    public User writeCredentials(String filePath, int idList, int idCredentials) {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            System.out.println("Reading from Excel file");
            Sheet sheet = workbook.getSheetAt(idList); // ----- GETTING EXCEL SHEET READY
            int rowNumber = sheet.getPhysicalNumberOfRows(); // Get number of rows
            if (idCredentials <= rowNumber-1 ) {
                System.out.println("Sheet is empty.");
                Row row = sheet.getRow(idCredentials); // Get the Row
                String cellValueDataLogin = new DataFormatter().formatCellValue(row.getCell(0));
                String cellValueDataPass = new DataFormatter().formatCellValue(row.getCell(1));
                return new User(cellValueDataLogin, cellValueDataPass);
            } else {
                System.out.println("---Error: Sheet is not empty.");
            }
        } catch (Exception e) {
            System.out.println("---Error open credentials file: " + e);
        }
        return new User("error", "error");
    }

    public void clickSignIN(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOf(getSignIN())); //get locator SignIN
            getSignIN().click();

            System.out.println("click signIN");
        } catch (Exception e) {
            System.out.println("---Error. Locator not find: " + e);
        }
    }

    public void userLogin(WebDriverWait wait, User user) {
        try {
            wait.until(ExpectedConditions.visibilityOf(getUsernameBox())); //get locator login
            getUsernameBox().sendKeys(user.getLogin());

            wait.until(ExpectedConditions.visibilityOf(getPasswordBox())); //get locator pass
            getPasswordBox().sendKeys(user.getPass());

            wait.until(ExpectedConditions.visibilityOf(getLoginCTA())); //get locator loginCTA
            getLoginCTA().click();

            System.out.println("user login and pass send.");
        } catch (Exception e) {
            System.out.println("---Error. Locator not find: " + e);
        }
    }
    public boolean allowUserToLogin(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOf(getUserSignIN()));
            System.out.println("Status: User is logged in");
            return true;

        } catch (Exception e) {
            System.out.println("Status: User did not log in");
        }

        return false;
    }

    public void userSignOUT(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOf(getUserSignIN()));
            getUserSignIN().click();

            wait.until(ExpectedConditions.visibilityOf(getUserSignOUT()));
            getUserSignOUT().click();

            System.out.println("User is sign out");
        } catch (Exception e) {
            System.out.println("---Error. Not visibled locator: " + e);
        }
    }
}