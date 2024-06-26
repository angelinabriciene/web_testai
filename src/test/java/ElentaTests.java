import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

//vardas 3-20 simbolių, raidės skaičiai ir tarpai galimi
//passwordas 8 simboliai maža, didelė raidė, skaicius, 8 simboliai. viskas kartu privaloma


public class ElentaTests {
    public static WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();
    }

    public void acceptCookies() {
        driver.get("https://elenta.lt");
        driver.findElement(By.className("fc-cta-consent")).click();
    }
    private String getRandomLetters(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
    public String getRandomSymbols(int length) {
        String symbols = "!@#$%^&*()_+-={}:<>?[]|";
        StringBuilder randomSymbols = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(symbols.length());
            randomSymbols.append(symbols.charAt(randomIndex));
        }

        return randomSymbols.toString();
    }
    public String getRandomSymbolsLT(int length) {
        String symbols = "ĄČĘĖĮŠŲŪ";
        StringBuilder randomSymbolsLT = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(symbols.length());
            randomSymbolsLT.append(symbols.charAt(randomIndex));
        }

        return randomSymbolsLT.toString();
    }

    @BeforeMethod
    public void navigateToRegistrationPage() {
        driver.get("https://elenta.lt/registracija");
    }

    @Test
    public void normalRegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement registrationComfirmed = driver.findElement(By.className("info"));
        Assert.assertTrue(registrationComfirmed.isDisplayed());
    }
    @Test
    public void emptyUsernameRegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("");

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void twoLettersUsernameRegistrationTest() {
        String randomLetters = getRandomLetters(2);
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys(randomLetters);

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void thirtyLettersUsernameRegistrationTest() {
        String randomLetters = getRandomLetters(30);
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys(randomLetters);

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void specialSymbolsUsernameRegistrationTest() {
        String randomSymbols = getRandomSymbols(8);
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys(randomSymbols);

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void specialLTSymbolsUsernameRegistrationTest() {
        String randomSymbolsLT = getRandomSymbolsLT(8);
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys(randomSymbolsLT);

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void emptyPassword1RegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void sixSymbolsPassword1RegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testa1");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testa1");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void allLowerCasePassword1RegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("testas1as");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void allUpperCasePassword1RegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("TESTAS1AS");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("TESTAS1AS");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void noNumberPassword1RegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testasas");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testasas");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void noLetterPassword1RegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("12345678");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("12345678");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }
    @Test
    public void notMatchingPassword1ToPassword2RegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random()*300) + "as" + Math.round(Math.random()*300) + Math.round(Math.random()*300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random()*300) + Math.round(Math.random()*300) + "@testas" + Math.round(Math.random()*300) + ".lt");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("Testas1as");

        WebElement password2 = driver.findElement(By.id("Password2"));
        password2.click();
        password2.sendKeys("Testas1as1");

        WebElement submit = driver.findElement(By.className("bigNavBtn2"));
        submit.click();

        WebElement noUserName = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[8]/td[2]/span"));
        Assert.assertTrue(noUserName.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }
}