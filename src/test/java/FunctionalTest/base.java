package FunctionalTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public class base {
    protected WebDriver driver;
    public WebDriverWait wait;
    public Properties properties;
    public int timeout;

    @BeforeMethod
    public WebDriver initializeDriver() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties");
        properties.load(fileInputStream);
        //mvn test -Dbrowser=chrome
        String browserName = properties.getProperty("browser");
        timeout = Integer.parseInt(properties.getProperty("timeout"));
        String HEADLESS = properties.getProperty("HEADLESS");
        System.out.println("browser name: " + browserName);

        switch (browserName) {
            case "chrome":
                ChromeOptions optionsGC = new ChromeOptions();
                if (HEADLESS.equals("true")) {
                    optionsGC.addArguments("--headless");
                    System.out.println("headless options");
                }
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(optionsGC);
                break;

            case "firefox":
                FirefoxOptions optionsF = new FirefoxOptions();
                if (HEADLESS.equals("true")) {
                    optionsF.addArguments("--headless");
                    System.out.println("headless options");
                }
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(optionsF);
                break;

            case "edge":
                EdgeOptions optionsE = new EdgeOptions();
                if (HEADLESS.equals("true")) {
                    optionsE.addArguments("--headless");
                    System.out.println("headless options");
                }
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(optionsE);
                break;

            case "opera":
                OperaOptions optionsO = new OperaOptions();
                if (HEADLESS.equals("true")) {
                    optionsO.addArguments("--headless");
                    System.out.println("headless options");
                }
                WebDriverManager.operadriver().setup();
                driver =new OperaDriver(optionsO);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browserName);
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().deleteAllCookies();

        return driver;
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
