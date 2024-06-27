import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class VynotekaTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookiesAndConsentAge();
        closeAdvert();
    }

    public void acceptCookiesAndConsentAge() {
        driver.get("https://vynoteka.lt/");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
        driver.findElement(By.xpath("//*[@id=\"app__inner\"]/div[2]/div/div/div/div/div[2]/div[3]/div/div[1]/button")).click();
    }

    public void closeAdvert() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div[2]/div[2]/button"))).click();
    }

    @Test
    public void vineSearch() {
        WebElement searchForm = driver.findElement(By.xpath("//*[@id=\"app__header\"]/div[2]/div/div/div[3]/div/div/div/form/div[1]/div/input"));
        searchForm.sendKeys("bla");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/header/div[2]/div/div/div[3]/div/div/div/form/div[2]/div/div[2]/div/div/div/div[1]/div[3]/div/div/button")));
        driver.findElement(By.xpath("//*[@id=\"app__header\"]/div[2]/div/div/div[3]/div/div/div/form/div[1]/button")).click();
        WebElement searchComplete = driver.findElement(By.xpath("//*[@id=\"app__main\"]/section/div/div[2]/div/div[1]/div[1]/div/div[2]/div/div[1]/h1"));
        Assert.assertTrue(searchComplete.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

}
