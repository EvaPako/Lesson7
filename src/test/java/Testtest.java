
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class Testtest {

    private static WebDriver driver;

    @BeforeSuite
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        //driver.findElement(By.xpath("//*[text()='Принять']")).click();


        driver.manage().addCookie(new Cookie("_fbp", "fb.1.1743527539961.19365594915407187"));
        driver.manage().addCookie(new Cookie("_ga_7C99PNNT06", "GS1.1.1743527531.9.0.1743527531.60.0.0"));
        driver.manage().addCookie(new Cookie("_ga_DNC2PBDGDP", "GS1.1.1743527531.9.0.1743527531.0.0.0"));
        driver.manage().addCookie(new Cookie("BX_USER_ID", "66ad0f93a7a70c4401744df2df73ae77"));
        driver.manage().addCookie(new Cookie("PHPSESSID", "SSz91H39UDpODKb0UqKjHNl1AqNPxlPw"));
        driver.manage().addCookie(new Cookie("WEBIM_LOCALE", "ru"));

        //driver.navigate().refresh();
    }

    @AfterSuite
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 0)
    public void verifyText() {
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id='pay-section']/div/div/div[2]/section/div/h2")).getText(),
                "Онлайн пополнение\nбез комиссии");
    }

    @Test(priority = 1)
    public void verifyAssertTrueLogotype() {

        String[] paymentLogos = {
                "img[alt='Visa']",
                "img[alt='Verified By Visa']",
                "img[alt='MasterCard']",
                "img[alt='MasterCard Secure Code']",
                "img[alt='Белкарт']"
                // "img[alt='Qiwi']"

        };

        try {

            for (String logo : paymentLogos) {
                WebElement logoElement = driver.findElement(By.cssSelector(logo));
                Assert.assertTrue(logoElement.isDisplayed(), "Логотип не найден: " + logo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //driver.quit();
        }
    }


    @Test(priority = 2)
    public void checkServiceLink() {
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        Actions actions = new Actions(driver);
        actions.moveToElement(link).doubleClick().build().perform();
        //new WebDriverWait(driver, Duration.ofSeconds(20))
        // .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Подробнее о сервисе']")));

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        Assert.assertEquals(actualUrl, expectedUrl, "URL не соответствует ожидаемому");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().back();

    }

    @Test(priority = 3)
    public void Input () {
        driver.findElement(By.xpath("//*[@class='phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//*[@id='connection-sum']")).sendKeys("100");
        // driver.findElement(By.xpath("//*[@id='pay-connection']/button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pay-connection']/button")));
    }
}