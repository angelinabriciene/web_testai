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
        System.out.println("Pasirinktų prekių skaičius: " + itemCount);

        double totalPrice = 0;
        int priceCount = 0;

        for (WebElement item : items) {
            WebElement priceElement = item.findElement(By.xpath(".//following-sibling::*[contains(@class, 'price')]"));
            String priceText = priceElement.getText();
            String[] priceParts = priceText.split("\\s+");
            if (priceParts.length > 0 && !priceParts[0].trim().isEmpty()) {
                double price = Double.parseDouble(priceParts[0].replace("€", "").replace(",", "."));
                totalPrice += price;
                priceCount++;

                double discount = 0;
                if (priceParts.length > 1) {
                    String discountText = priceParts[1];
                    if (discountText.contains("%")) {
                        discount = Double.parseDouble(discountText.replace("%", ""));
                    }
                }
            }
        }
        if (priceCount > 0) {
            double averagePrice = totalPrice / priceCount;
            System.out.println("Vidutinė prekės kaina: " + String.format("%.2f", averagePrice) + " €");
        }
    }
 }

