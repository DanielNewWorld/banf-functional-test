package FunctionalTest;
import com.aventstack.extentreports.ExtentReports;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import resources.ExtentReporterNG;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;

@Listeners(FunctionalTest.Listeners.class)
public class CreateAccountTest extends base {
    public static WindowsDriver remoteDesktopDriver = null;
    public static ExtentReports extent;
    private static final Logger log = LogManager.getLogger(base.class);
    static Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
    public static Random random = new Random();
    int randomNumber = random.nextInt(90)+10;
    int users = 10;
    public static String getDate() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    @BeforeSuite
    public void beforeSuit(){
        try {
            Robot robot0 = new Robot();
            robot0.mouseMove(767, 701);
            robot0.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot0.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot0.delay(1000);
            robot0.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot0.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot0.delay(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getDate());
        extent = ExtentReporterNG.getReportObject();
        log.info("Driver is initialized");
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Windows\\System32\\mstsc.exe");
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            remoteDesktopDriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            //remoteDesktopDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //remoteDesktopDriver.findElementByName("Connect").click();
            log.info("RemoteDesktop is lunched");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BeforeClass
    public void setUp(){
        try {
            Robot robot1 = new Robot();
            robot1.delay(15000);
            robot1.mouseMove(115, 335); // Navigate to PetWare
            robot1.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot1.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot1.delay(1000);
            robot1.keyPress(KeyEvent.VK_ENTER);
            robot1.keyRelease(KeyEvent.VK_ENTER);
            robot1.delay(15000);
            StringSelection stringSelection1 = new StringSelection("PWSQA");
            robot1.delay(1000);
            clipboard1.setContents(stringSelection1, null); // Enter username PWSQA
            robot1.delay(1000);
            robot1.keyPress(KeyEvent.VK_CONTROL);
            robot1.delay(2000);
            robot1.keyPress(KeyEvent.VK_V);
            robot1.keyRelease(KeyEvent.VK_V);
            robot1.keyRelease(KeyEvent.VK_CONTROL);
            robot1.delay(500);
            robot1.keyPress(KeyEvent.VK_TAB);
            robot1.delay(500);
            StringSelection stringSelection2 = new StringSelection("2STARS9"); // Enter pass *******
            clipboard1.setContents(stringSelection2, null);
            robot1.delay(500);
            robot1.keyPress(KeyEvent.VK_CONTROL);
            robot1.delay(2000);
            robot1.keyPress(KeyEvent.VK_V);
            robot1.keyRelease(KeyEvent.VK_V);
            robot1.keyRelease(KeyEvent.VK_CONTROL);
            robot1.keyPress(KeyEvent.VK_ENTER);
            robot1.keyRelease(KeyEvent.VK_ENTER);
            robot1.delay(5000);
            robot1.mouseMove(917, 362);
            robot1.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot1.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot1.delay(8000);
            robot1.keyPress(KeyEvent.VK_TAB);
            robot1.delay(1000);
            robot1.keyPress(KeyEvent.VK_ENTER);
            robot1.keyRelease(KeyEvent.VK_ENTER);
        }catch (AWTException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 1) // Create account in petWare with 1 pet
    public void lunchRemoteDesktopLogin(){
        for (int i = 1; i <= users; i++) {
            try {
                Robot robot2 = new Robot();
                robot2.delay(3000);
                StringSelection stringSelection = new StringSelection("Rovshen".substring(0, 7)+ randomNumber+i);
                log.info("Rovshen".substring(0, 5) + randomNumber+i);
                robot2.delay(2000);
                robot2.mouseMove(23, 49); // move to Search box
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);// Click Search box
                robot2.delay(1000);
                clipboard1.setContents(stringSelection, null);
                robot2.mouseMove(187, 333); // move to First name
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Click to First name field
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_CONTROL);
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V); // Past Client First name
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                StringSelection stringSelection2 = new StringSelection("Test" + randomNumber+i);
                log.info("Test" + randomNumber+i);
                clipboard1.setContents(stringSelection2, null);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste Client Last name
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(1000);// ---------Click Enter to Search if clients exists
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.delay(2000);// ----- Search all Hospital
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.delay(4000);
                robot2.mouseMove(1117, 116);//----------Create Account click
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(3000);
                robot2.mouseMove(339, 411);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                StringSelection stringSelection3 = new StringSelection("18101 SE 6th Way");// Add address
                clipboard1.setContents(stringSelection3, null);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste Client Address
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(12000);
                robot2.mouseMove(923, 335);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(1000);
                robot2.mouseMove(382, 477); // Navigate to city
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                StringSelection stringSelection4 = new StringSelection("Vancouver");// Add city
                clipboard1.setContents(stringSelection4, null);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste Client city
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.keyRelease(KeyEvent.VK_TAB);
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.delay(1000);
                robot2.mouseMove(693, 437); //--------Selecting State
                robot2.delay(500);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK); // double click
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(500);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseMove(619, 397); // Click on WA
                robot2.delay(500);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_TAB); // move to zipcode
                robot2.keyRelease(KeyEvent.VK_TAB);
                robot2.delay(1000);
                StringSelection stringSelection5 = new StringSelection("98683");// Add zipcode
                clipboard1.setContents(stringSelection5, null);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste Zipcode
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_TAB); // move to phone number
                robot2.keyRelease(KeyEvent.VK_TAB);
                robot2.delay(1000);
                StringSelection stringSelection6 = new StringSelection("(503) 933-4900");// Add phone
                clipboard1.setContents(stringSelection6, null);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste phone
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_TAB); // move to Prefer phone number
                robot2.keyRelease(KeyEvent.VK_TAB);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_SPACE);
                robot2.keyRelease(KeyEvent.VK_SPACE); // click radio button
                robot2.delay(500);
                robot2.mouseMove(624, 542); // move to Email
                robot2.delay(500);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                String email = "rovshenSQA" + randomNumber +i+ "@test.com";
                StringSelection stringSelection7 = new StringSelection(email);// Add Email
                clipboard1.setContents(stringSelection7, null);
                log.info(email);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste email
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.mouseMove(400, 428);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.mouseMove(580, 427);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(500);
                robot2.mouseMove(490, 635);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(3000);//---------------------------------------Create A Pet
                robot2.mouseMove(371, 335);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(3000);
                String petName = "Jora-Dog" + randomNumber+i;
                StringSelection stringSelection8 = new StringSelection(petName);// Add phone
                clipboard1.setContents(stringSelection8, null);
                log.info(petName);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste Pet name
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB); // move to Species
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.mouseMove(631, 413);// Navigate to Canine
                robot2.delay(500);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);// Click on Dog
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB); // move to Breed
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_DOWN); // Choose A Mississippi breed
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER); //click Enter
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB); // move to Sex
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_DOWN); // Choose Male
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);//enter
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);// move to Color of pet
                StringSelection stringSelection9 = new StringSelection("Black");// add Black color
                clipboard1.setContents(stringSelection9, null);
                log.info("Black");
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste Pet color
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(500);
                robot2.mouseMove(642, 472); // Navigate to Birth Date BY AGE
                robot2.delay(500);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);// click birth by Age
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);// to Years
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_1);
                robot2.keyRelease(KeyEvent.VK_1);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);//enter
                robot2.delay(5000);// ----------------------------------------------Reporting
                robot2.mouseMove(354, 109); // Navigate to Clients info
                robot2.delay(500);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(2000);
                robot2.keyPress(KeyEvent.VK_TAB);// navigate to Client info dash
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);//enter in Client info dash
                robot2.delay(2000);
                robot2.mouseMove(383, 651); // scrolling down to lower field
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(1500);
                robot2.keyPress(KeyEvent.VK_TAB); // moved to First Name
                robot2.delay(1000);
                robot2.mouseMove(779, 638); // scrolling down
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // moved email
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);
                robot2.delay(1000);
                robot2.mouseMove(546, 657); // move to
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Click to Client ID
                robot2.keyPress(KeyEvent.VK_CONTROL);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_A); // Select Client ID
                robot2.keyRelease(KeyEvent.VK_A);
                robot2.delay(500);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.keyPress(KeyEvent.VK_CONTROL);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_C); // Copy Client ID
                robot2.keyRelease(KeyEvent.VK_C);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(1000);
                robot2.mouseMove(905, 17);// minimize the Remote Desktop
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(3000);
                robot2.mouseMove(719, 701); // navigate to Excel
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(4000);
                robot2.keyPress(KeyEvent.VK_WINDOWS);
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_UP);
                robot2.keyRelease(KeyEvent.VK_UP);
                robot2.keyRelease(KeyEvent.VK_WINDOWS);
                robot2.delay(3000);
                robot2.mouseMove(406, 491); // navigate to Test file
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste ID
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL); // paste ID
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB); // move to paste email
                robot2.delay(500);
                StringSelection stringSelection10 = new StringSelection(email);// paste email
                clipboard1.setContents(stringSelection10, null);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste pet name
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_TAB);// move to paste Pet Name
                robot2.delay(500);
                StringSelection stringSelection11 = new StringSelection(petName);// paste pet name
                clipboard1.setContents(stringSelection11, null);
                robot2.keyPress(KeyEvent.VK_CONTROL); // ------Paste pet name
                robot2.delay(1000);
                robot2.keyPress(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_V);
                robot2.keyRelease(KeyEvent.VK_CONTROL);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.delay(1000);
                robot2.mouseMove(1257, 13);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(500);
                robot2.keyPress(KeyEvent.VK_ENTER);
                robot2.keyRelease(KeyEvent.VK_ENTER);
                robot2.delay(2000);
                robot2.mouseMove(819, 699);
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot2.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot2.delay(1000);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        log.info("..........................................");
    }
    @Test(priority = 2)
    public void createPetWareUser(){
    }
    @Test
    public void practice() throws InterruptedException {
        Thread.sleep(14000);
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        System.out.println("X = " + x + "\n");
        System.out.println("Y = " + y);  //28 --------------------------
    }
}
