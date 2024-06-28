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
import java.util.NoSuchElementException;

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
        searchForm.sendKeys("mazda mx 5");
        driver.findElement(By.id("searchButton")).click();

        int totalCount = 0;
        double totalPrice = 0;
        int priceCount = 0;
        int currentPage = 1;

        while (true) {
            List<WebElement> items = driver.findElements(By.xpath("//a[starts-with(@href, '/skelbimai/')]//div[@class='content-block']"));         int itemCount = items.size();
            totalCount += itemCount;
            System.out.println("Puslapis: " + currentPage + "; Skelbimų puslapyje: " + itemCount);

            for (WebElement item : items) {
                WebElement priceElement = item.findElement(By.xpath(".//following-sibling::*[contains(@class, 'price')]"));
                String priceText = priceElement.getText();
                String[] priceParts = priceText.split("\\s+");
                if (priceParts.length > 0 &&!priceParts[0].trim().isEmpty()) {
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
            Thread.sleep(3000);
            List<WebElement> nextPageLinks = driver.findElements(By.xpath("//a[@rel='next']"));
            if (nextPageLinks.size() > 0) {
                nextPageLinks.get(0).click();
                currentPage++;
            } else {
                break;
            }
        }

        System.out.println("Iš viso skelbimų: " + totalCount);
        if (priceCount > 0) {
            double averagePrice = totalPrice / priceCount;
            System.out.println("Vidutinė prekės kaina: " + String.format("%.2f", averagePrice) + " €");
        }
    }
}

