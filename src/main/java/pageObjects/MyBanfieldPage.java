package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyBanfieldPage {
    public WebDriver driver;
    By hamburgerIcon = By.cssSelector(".navbar-toggler-icon>img:nth-child(1)");
    By shopSideBarCTA = By.xpath("//a[normalize-space()='Shop']");
    By shopPetProducts = By.xpath("//a[normalize-space()='Shop Pet Products']");
    By shopDogsCTA = By.xpath("//a[contains(text(),'Shop Dogs')]");
    By shopCatsCTA = By.xpath("//a[contains(text(),'Shop Cats')]");
    By patient1Name = By.xpath("//*[@id='DashBoardPatientData']");
    By seeFullProfile = By.cssSelector("a.btn:nth-child(6)");
    By welcomeUser = By.xpath("//*[contains(@class,'desktoplogin desktop-login-padding')]");
    // $x("//*[@class='orange-link-userActions']"); List of Update Information table (3 elements)
    By updateYourPassword = By.xpath("//*[@id='updPassword']");
    By dashboard = By.xpath("//a[normalize-space()='Dashboard']");
    By seeWhatsInStore = By.cssSelector(".shop-cart-links>div:nth-child(1)>a:nth-child(1)");
    By myOrdersCTA = By.xpath("//a[contains(text(),'My orders')]");
    By myCartCTA = By.xpath("//a[@title='My cart']");
    By manageAccount = By.xpath("//a[normalize-space()='Manage your account']");
    By logOutBtn = By.cssSelector("#lnkSignout");

    public MyBanfieldPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHamburgerIcon() {
        return driver.findElement(hamburgerIcon);
    }

    public WebElement getShopCTA(){ return driver.findElement(shopSideBarCTA);}

    public WebElement getShopPetProducts(){ return driver.findElement(shopPetProducts);}

    public WebElement getShopDogs(){ return driver.findElement(shopDogsCTA); }

    public WebElement getShopCats(){ return driver.findElement(shopCatsCTA); }

    public WebElement getPatient1Name(){ return driver.findElement(patient1Name); }

    public WebElement getSeeFullProfile(){return driver.findElement(seeFullProfile);}

    public WebElement getWelcomeUser() {
        return driver.findElement(welcomeUser);
    }

    public WebElement getUpdateYourPass(){ return driver.findElement(updateYourPassword);}

    public WebElement getDashboard(){ return driver.findElement(dashboard); }

    public WebElement getSeeWhatInStore(){ return driver.findElement(seeWhatsInStore); }

    public WebElement getMyOrdersCTA(){ return driver.findElement(myOrdersCTA);}

    public WebElement getMyCartCTA(){ return driver.findElement(myCartCTA);}

    public WebElement getManageAccount(){ return driver.findElement(manageAccount);}

    public WebElement getLogOutBtn() {
        return driver.findElement(logOutBtn);
    }

}
