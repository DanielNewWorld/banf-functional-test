package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopPage {
    public WebDriver driver;
    By cartIcon = By.xpath("//*[@class='minicart-total hide-link-med position-relative']");
    By myOrders = By.xpath("//*[@class='order-text d-none d-sm-block align-self-start align-self-sm-center order-1 ml-auto']");
    By hamburgerIcon = By.id("hamburger");
    By banfieldLogoCTA = By.cssSelector("#logoLink1");
    By shopLogoCTA = By.cssSelector("#logoLink2");
    By topNavLocationCTA = By.cssSelector("#topNav-1");
    By hamburgerLocations = By.id("topNavHam-1");
    By topNavWellnessCTA = By.cssSelector("#topNav-3");
    By hamburgerWellnessPackage = By.id("topNavHam-3");
    By topNavNewToBanfieldCTA = By.cssSelector("#topNav-4");
    By hamburgerNewToBanfield = By.cssSelector("#topNavHam-4");
    By hamburgerAppointment = By.id("topNavHam-6"); // same as Schedule now its just Appointment schedule-appointment
    By topNavSignInPage = By.id("main-nav-login");
    By hamburgerSignIn = By.xpath("//*[@id='user-login-link']");
    By hamOWP_collapsed = By.cssSelector("#hamburger-l1-1");
    By hamOptimumWellnessPlan = By.cssSelector("#l2-nav-item-1-OWP.nav-link.p-0");//Optimum Wellness Plan+-----------
    By hamPuppyPackages = By.cssSelector("#l2-nav-item-2-OWP.nav-link.p-0");
    By hamKittenPackages = By.cssSelector("#l2-nav-item-3-OWP.nav-link.p-0");
    By hamDogPackages = By.cssSelector("#l2-nav-item-4-OWP.nav-link.p-0");
    By hamCatPackages = By.cssSelector("#l2-nav-item-5-OWP.nav-link.p-0");
    By hamFAQs = By.cssSelector("#l2-nav-item-6-OWP.nav-link.p-0");
    By hamInquire = By.cssSelector("#l2-nav-item-7-OWP.nav-link.p-0");
    By hamWellnessAtBanfieldFinal = By.cssSelector("#hamburger-l1-2"); //Wellness at Banfield Final+----------
    By hamWellness = By.cssSelector("#nav-group-section2>li:nth-child(1)>a:nth-child(1)");
    By hamPuppy_hub = By.cssSelector("#nav-group-section2>li:nth-child(2)>a:nth-child(1)");
    By hamDental_care = By.cssSelector("#nav-group-section2>li:nth-child(3)>a:nth-child(1)");
    By hamDiet_and_nutrition = By.cssSelector("#nav-group-section2>li:nth-child(4)>a:nth-child(1)");
    By hamEar_care = By.cssSelector("#nav-group-section2>li:nth-child(5)>a:nth-child(1)");
    By hamFirst_aid = By.cssSelector("#nav-group-section2>li:nth-child(6)>a:nth-child(1)");
    By hamParasites = By.cssSelector("#nav-group-section2>li:nth-child(7)>a:nth-child(1)");
    By hamVaccination = By.cssSelector("#nav-group-section2>li:nth-child(8)>a:nth-child(1)");
    By hamOurLocations = By.cssSelector("#hamburger-l1-3");            // Our Locations+---------
    By hamLocation_search = By.cssSelector("#nav-group-section3>li:nth-child(1)>a:nth-child(1)");
    By hamMakeAnAppointment = By.cssSelector("#nav-group-section3>li:nth-child(2)>a:nth-child(1)");
    By hamMyBanfield = By.cssSelector("#hamburger-l1-4");//   MyBanfield+--------------
    By hamLogIn_Register = By.cssSelector("#nav-group-section4>li:nth-child(1)>a:nth-child(1)");
    By hamDashboard = By.id("l2-nav-item-2-MyBanfield"); //l2-nav-item-2-MyBanfield
    By hamMyAccount = By.cssSelector("#nav-group-section4>li:nth-child(2)>a:nth-child(1)");
    By hamPARP_dashboard = By.cssSelector("#nav-group-section4>li:nth-child(3)>a:nth-child(1)");
    By hamShop = By.xpath("//*[@id='hamburger-l1-5']"); // Shop+----------------------
    By hamShopPetProducts = By.xpath("//*[@id='l2-nav-item-1-Shop']"); //Shop Pet Products
    By hamShopDogs = By.xpath("//*[@id='l2-nav-item-2-Shop']");// Shop Dogs
    By hamShopCats = By.xpath("//*[@id='l2-nav-item-3-Shop']");// Shop Cats
    By hamAboutShop = By.xpath("//*[@id='l2-nav-item-4-Shop']");// About Shop
    By hamShopFAQs = By.id("l2-nav-item-5-Shop");// Shop FAQ's   By.xpath("//*[@id='l2-nav-item-5-Shop']")
    By loginAdd2Cart = By.xpath("//div[@class='hidden-sm-down']//button[@id='customerLogin']");
    By userNameBox = By.xpath("//*[@id='UserName']");
    By needHelpEmailBox = By.xpath("//input[@id='txtForgotPasswordEmail']");
    By sendMyResetLinkCTA = By.xpath("//input[@id='btnSendResetPassword']");
    By resetSentMassage = By.xpath("//*[@id='pageLevelNotificationContainer']/div[2]/div/div/h5/span");
    By createUrAccountCTA = By.cssSelector("form[id='loginPage'] a[title='Sign up']");
    By createAccountTitle = By.cssSelector("#content>div.signup-page>div.container");
    By createAccountFormTxt = By.xpath("//*[@id='clientvalidate']/h2");
    By passwordBox = By.xpath("//*[@id='Password']");
    By rememberMeBox = By.cssSelector("#loginPage > div:nth-child(6) > div > label");
    By loginBtn = By.cssSelector("#loginPage>div:nth-child(7)>div:nth-child(1)>input");
    By welcomeUser = By.cssSelector("p.m-0");
    By searchBox = By.id("search-input");
    By searchBtn = By.cssSelector("#search-submit");
    By searchResultCount = By.cssSelector("#product-search-results>div>div.p-3.col-12>div");
    By topNavDogsDropdown = By.cssSelector("#alldog_ecomcategory"); // >>DOG<<< dropdown--------------------
    By topNavCatsDropdown = By.cssSelector("#allcat_ecomcategory"); // >>CAT<<< dropdown--------------------
    By topNavMedicationDropDown = By.cssSelector("#medication_ecomcategory"); // >>MEDICATION<< -------------
    By topNavFoodDropdown = By.cssSelector("#food_ecomcategory"); //             >>FOOD<< -------------------
    By alphaTRAK2BloodGMSFromSearch = By.cssSelector("#pdp-link-alphatrak-2-blood-glucose-monitoring-system");
    By alphaTRAK2BloodGMS = By.cssSelector("#tile-image-link-alphatrak-2-blood-glucose-monitoring-system");
    By keepShoppingFromCart = By.xpath("//*[@class='continue-shopping-link']");
    By addToCartCTA = By.xpath("//div[@class='row']//button[2]");
    By productTitleInCart = By.xpath("//*[@class='line-item-name']");
    By goToCheckOutCTA = By.cssSelector("#modal-view-cart");
    By customerNameOrders = By.cssSelector(".customer-name");
    By hamLogOutBtn = By.cssSelector("#logout");
    By clientID = By.cssSelector("#ClientId");
    By cookieClose = By.cssSelector("#onetrust-close-btn-container");
    By needHelpLink = By.xpath("//*[@class='forgot-password orange-link orange-link-none']");
    By passwordEyeIcon = By.cssSelector(".fa.fa-fw.field-icon.toggle-password.fa-eye");

    public ShopPage(WebDriver driver){ this.driver = driver; }
    public WebElement getEyeIcon(){ return driver.findElement(passwordEyeIcon); }
    public WebElement getNeedHelpLink(){ return driver.findElement(needHelpLink); } // Need Help CTA
    public WebElement getCloseCookie(){return driver.findElement(cookieClose); }
    public WebElement getClientID(){return driver.findElement(clientID); }
    public WebElement getCartIcon(){ return driver.findElement(cartIcon); } // Cart from Home page
    public WebElement getMyOrders(){ return driver.findElement(myOrders); }// My Orders
    public WebElement getTopNavSignInPage(){ return driver.findElement(topNavSignInPage); } // "Sign in" page from top Nav
    public WebElement getBanfieldLogo(){ return driver.findElement(banfieldLogoCTA); } // Main Banfield Logo
    public WebElement getShopLogo(){ return driver.findElement(shopLogoCTA); } // Shop Logo next main Logo
    public WebElement getTopNavLocations(){ return driver.findElement(topNavLocationCTA);} // "Locations" from top Navigation
    public WebElement getHamburgerIcon(){ return driver.findElement(hamburgerIcon); } // Hamburger icon
    public WebElement getHamburgerSignIn(){ return driver.findElement(hamburgerSignIn); } // "Sign In" from Hamburger icon
    public WebElement getHamLocations(){ return driver.findElement(hamburgerLocations);} // "Locations" from Hamburger icon
    public WebElement getTopNavWellness(){ return driver.findElement(topNavWellnessCTA); } // "Wellness package" from top navigation
    public WebElement getHamWellnessPackage(){ return driver.findElement(hamburgerWellnessPackage);} // "Wellness package" from hamburger icon
    public WebElement getTopNavNewToBanfield(){ return driver.findElement(topNavNewToBanfieldCTA); } // "New to Banfield" from top navigation
    public WebElement getHamNewToBanfield(){ return driver.findElement(hamburgerNewToBanfield);} // "New to Banfield" from hamburger icon
    public WebElement getHamAppointment(){ return driver.findElement(hamburgerAppointment);} //schedule-appointment from hamburger menu after user Logged in
    public WebElement getHamOWP_collapsed(){ return driver.findElement(hamOWP_collapsed); } // OWP+
    public WebElement getHamOptimumWellnessPlan(){ return driver.findElement(hamOptimumWellnessPlan); }//Optimum Wellness Plan+--------------
    public WebElement getHamPuppyPackages(){ return driver.findElement(hamPuppyPackages); }
    public WebElement getHamKittenPackages(){ return driver.findElement(hamKittenPackages); }
    public WebElement getHamDogPackages(){ return driver.findElement(hamDogPackages); }
    public WebElement getHamCatPackages(){ return driver.findElement(hamCatPackages); }
    public WebElement getHamFAQs(){ return driver.findElement(hamFAQs); }
    public WebElement getHamInquire(){ return driver.findElement(hamInquire);}
    public WebElement getHamWellnessAtBanfieldFinal(){ return driver.findElement(hamWellnessAtBanfieldFinal); }//Wellness at Banfield Final+----   -Hamburger
    public WebElement getHamWellness(){ return driver.findElement(hamWellness); } // Wellness
    public WebElement getHamPuppy_hub(){ return driver.findElement(hamPuppy_hub); } // Puppy Hub
    public WebElement getHamDental_care(){ return driver.findElement(hamDental_care); } // Dental care
    public WebElement getHamDiet_and_nutrition(){ return driver.findElement(hamDiet_and_nutrition); } // Diet and nutrition
    public WebElement getHamEar_care(){ return driver.findElement(hamEar_care); } // Ear Care
    public WebElement getHamFirst_aid(){ return driver.findElement(hamFirst_aid); } // First Aid
    public WebElement getHamParasites(){ return driver.findElement(hamParasites); } // Parasites
    public WebElement getHamVaccination(){ return driver.findElement(hamVaccination); } // Vaccination
    public WebElement getHamOurLocations(){ return driver.findElement(hamOurLocations); }// Our Locations+---------
    public WebElement getHamLocation_search(){ return driver.findElement(hamLocation_search); } // Location Search
    public WebElement getHamMakeAnAppointment(){ return driver.findElement(hamMakeAnAppointment); }// Make an Appointment
    public WebElement getHamMyBanfield(){ return driver.findElement(hamMyBanfield); }// My Banfield+--------------
    public WebElement getHamLogIn_Register(){ return driver.findElement(hamLogIn_Register); } // Login/Register
    public WebElement getHamDashboard(){ return driver.findElement(hamDashboard); } // Dashboard - hamburger
    public WebElement getHamMyAccount(){ return driver.findElement(hamMyAccount); } // My Account- hamburger
    public WebElement getHamPARP_dashboard(){ return driver.findElement(hamPARP_dashboard); } // PARP dashboard- hamburger
    public WebElement getHamShop(){ return driver.findElement(hamShop); }// Shop+--------------
    public WebElement getHamShopPetProducts(){ return driver.findElement(hamShopPetProducts); }//Shop Pet Products
    public WebElement getHamShopDogs(){ return driver.findElement(hamShopDogs); }// Shop Dogs
    public WebElement getHamShopCats(){ return driver.findElement(hamShopCats); }// Shop Cats
    public WebElement getHamAboutShop(){ return driver.findElement(hamAboutShop); }// About Shop
    public WebElement getHamShopFAQs(){ return driver.findElement(hamShopFAQs); }// Shop FAQs
    public WebElement getUsernameBox(){ return driver.findElement(userNameBox); } // Username box from Login
    public WebElement getNeedHelpEmailBox(){ return driver.findElement(needHelpEmailBox); } // "Email" box from Need Help
    public WebElement getSendMyResetLink(){ return driver.findElement(sendMyResetLinkCTA); } // "Send my Reset Link" from Reset pass page
    public WebElement getResetSentMassage(){ return driver.findElement(resetSentMassage); }
    public WebElement getCreateUrAccountPage(){ return driver.findElement(createUrAccountCTA); } // "Crate your account" from Login page
    public WebElement getCreateAccountTitle(){ return driver.findElement(createAccountTitle); }
    public WebElement getCreateAccountForm(){ return driver.findElement(createAccountFormTxt); }
    public WebElement getPasswordBox(){ return driver.findElement(passwordBox); }
    public WebElement getRememberMe(){ return driver.findElement(rememberMeBox); }
    public WebElement getLoginBtn(){ return driver.findElement(loginBtn); }
    public WebElement getWelcomeUser(){ return driver.findElement(welcomeUser); }
    public WebElement getLoginAddCart(){ return driver.findElement(loginAdd2Cart); }
    public WebElement getCustomerNameOrders(){ return driver.findElement(customerNameOrders); }
    public WebElement getSearchBox(){ return driver.findElement(searchBox); } // Search Box
    public WebElement getAlphaTRAK2BloodGMSFromSearch(){ return driver.findElement(alphaTRAK2BloodGMSFromSearch); }
    public WebElement getAlphaTRAK2BloodGMS(){return driver.findElement(alphaTRAK2BloodGMS); }
    public WebElement getSearchCTA(){ return driver.findElement(searchBtn); } // Search click - CTA
    public WebElement getSearchResultCount(){ return driver.findElement(searchResultCount); } // Search result count
    public WebElement getTopNavDogsDropdown(){ return driver.findElement(topNavDogsDropdown);} // >>DOGS<< dropdown------
    public WebElement getTopNavCatsDropdown(){ return driver.findElement(topNavCatsDropdown); }// >>CATS<< dropdown------
    public WebElement getTopNavMedicationDropDown(){ return driver.findElement(topNavMedicationDropDown);}// >>MEDICATION<< dropdown------
    public WebElement getTopNavFoodDropdown(){ return driver.findElement(topNavFoodDropdown);}// >>FOOD<< dropdown------
    public WebElement getAddToCartCTA(){ return driver.findElement(addToCartCTA); }
    public WebElement getProductTitleInCart(){ return driver.findElement(productTitleInCart); } // 1st product title name
    public WebElement getGoToCheckOutCTA(){ return driver.findElement(goToCheckOutCTA); }
    public WebElement getKeepShoppingFromCart(){ return driver.findElement(keepShoppingFromCart); }
    public WebElement getHamLogOutBtn(){ return driver.findElement(hamLogOutBtn); }
}
