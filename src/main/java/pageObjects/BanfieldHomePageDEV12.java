package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BanfieldHomePageDEV12 {
    private final WebDriver driver;
    By login_Com_CTA = By.cssSelector(".desktoplogin>div:nth-child(1)"); //"//*[@class='login']"
    By hamburgerIcon = By.xpath("//*[@class='navbar-toggler-icon']");
    By shopSideBarCTA = By.xpath("//a[normalize-space()='Shop']");
    By shopPetProducts = By.xpath("//a[normalize-space()='Shop Pet Products']");
    By shopDogsCTA = By.xpath("//a[contains(text(),'Shop Dogs')]");
    By shopCatsCTA = By.xpath("//a[contains(text(),'Shop Cats')]");

    public BanfieldHomePageDEV12(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLoginPage(){ return driver.findElement(login_Com_CTA); }

    public WebElement getHamburgerIcon(){ return driver.findElement(hamburgerIcon); }

    public WebElement getShopCTA(){ return driver.findElement(shopSideBarCTA);}

    public WebElement getShopPetProducts(){ return driver.findElement(shopPetProducts);}

    public WebElement getShopDogs(){ return driver.findElement(shopDogsCTA); }

    public WebElement getShopCats(){ return driver.findElement(shopCatsCTA); }

}
