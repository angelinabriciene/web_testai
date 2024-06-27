import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
//        driver.get("https://elenta.lt/registracija");
        driver.get("https://elenta.lt");
    }

    @Test
    public void normalRegistrationTest() {
        WebElement username = driver.findElement(By.id("UserName"));
        username.click();
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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
        username.sendKeys("Testas" + Math.round(Math.random() * 300) + "as" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300));

        WebElement email = driver.findElement(By.id("Email"));
        email.click();
        email.sendKeys("teastas" + Math.round(Math.random() * 300) + Math.round(Math.random() * 300) + "@testas" + Math.round(Math.random() * 300) + ".lt");

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

    @Test
    public void addNewAutoAndMotoSellAdvertNormalTest() throws AWTException, InterruptedException {
        driver.findElement(By.id("submit-new-ad-nav-button")).click();
        driver.findElement(By.xpath("//*[@id=\"select-top-category-list\"]/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"select-sub-category-list\"]/li[1]/a")).click();

        WebElement advertName = driver.findElement(By.id("title"));
        advertName.click();
        advertName.sendKeys(getRandomLetters(10));

        WebElement advertDescription = driver.findElement(By.id("text"));
        advertDescription.click();
        advertDescription.sendKeys(getRandomLetters(10), getRandomSymbols(2), getRandomSymbolsLT(10));

        WebElement advertPrice = driver.findElement(By.id("price"));
        advertPrice.click();
        advertPrice.sendKeys("100");

        WebElement advertCity = driver.findElement(By.id("location-search-box"));
        advertCity.click();
        advertCity.sendKeys("Kaunas");

        WebElement advertPhoNoo = driver.findElement(By.id("phone"));
        advertPhoNoo.click();
        advertPhoNoo.sendKeys("+37060011223");

        WebElement advertEmail = driver.findElement(By.id("email"));
        advertEmail.click();
        advertEmail.sendKeys("testas@gmail.com");

        WebElement advertSecndLevel = driver.findElement(By.id("submit-button"));
        advertSecndLevel.click();

        WebElement uploadPhoto = driver.findElement(By.id("fileinput-label"));
        uploadPhoto.click();
        String photoPath = "C:\\Users\\Angelina7\\Downloads\\New folder\\vln2.jpg";
        Robot robot = new Robot();
        robot.delay(1000);
        StringSelection selection = new StringSelection(photoPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(10000);
        WebElement advertFinalLevel = driver.findElement(By.id("forward-button"));
        advertFinalLevel.click();

//        WebElement advertThirdLevel = driver.findElement(By.id("forward-button"));
//        advertThirdLevel.click();

        WebElement advertLink = driver.findElement(By.xpath("//*[@id=\"main-container\"]/h1"));
        Assert.assertTrue(advertLink.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }
}