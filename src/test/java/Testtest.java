
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class Testtest {

    @Test
    public void init() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        Assertions.assertEquals(driver.findElement(By.xpath(
                        "//*[@id='pay-section']/div/div/div[2]/section/div/h2")).getText(),
                "Онлайн пополнение\nбез комиссии");
        driver.quit();
    }

    @Test // Test cases for AssertTrue
    public void verifyAssertTrue() {


        String[] paymentLogos = {
                "img[alt='Visa']",
                "img[alt='Verified By Visa']",
                "img[alt='MasterCard']",
                "img[alt='MasterCard Secure Code']",
                "img[alt='Белкарт']"
                // "img[alt='Qiwi']"

        };

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        try {

            for (String logo : paymentLogos) {
                WebElement logoElement = driver.findElement(By.cssSelector(logo));
                Assert.assertTrue(logoElement.isDisplayed(), "Логотип не найден: " + logo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }



    @Test
    public void checkServiceLink() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://mts.by/");
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        Actions actions = new Actions(driver);
        actions.moveToElement(link).click().build().perform();
        driver.quit();
    }


}