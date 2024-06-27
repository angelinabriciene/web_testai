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
import java.util.List;

public class SkelbiuLTTests {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();
    }

    public void acceptCookies() {
        driver.get("https://www.skelbiu.lt/");
        driver.findElement(By.id("onetrust-reject-all-handler")).click();
    }

    @Test
    public void itemSearch() throws InterruptedException {
        WebElement searchForm = driver.findElement(By.id("searchKeyword"));
        searchForm.sendKeys("stetoskopas");
        driver.findElement(By.id("searchButton")).click();

        Thread.sleep(500);
        List<WebElement> items = driver.findElements(By.className("js-remember-button"));
        int itemCount = items.size();
        System.out.println("Pasirinktų prekių skaičius lange: " + itemCount);

        double totalPrice = 0;
        int priceCount = 0;

        for (WebElement item : items) {
            List<WebElement> priceElements = item.findElements(By.className("price"));
            if (!priceElements.isEmpty()) {
                String priceText = priceElements.get(0).getText();
                priceText = priceText.replace("€", "").trim();
                double price = Double.parseDouble(priceText);
                totalPrice += price;
                priceCount++;
            }
        }

        if (priceCount > 0) {
            double averagePrice = totalPrice / priceCount;
            System.out.println("Vidutinė kaina: " + averagePrice + " €");
        } else {
            System.out.println("Kainų nerasta");
        }
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }


}
