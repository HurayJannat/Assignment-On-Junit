import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {

    WebDriver driver;

    @BeforeAll
    public void setUp()
    {
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

    }

    @DisplayName("Form FillUP")
    @Test
    public void testRun() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.id("edit-name")).sendKeys("Dipty");
        driver.findElement(By.id("edit-number")).sendKeys("01923064413");
        driver.findElement(By.id("edit-date")).click();
        driver.findElement(By.id("edit-date")).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE,"18"+Keys.ARROW_RIGHT+"8"+Keys.ARROW_RIGHT+"2024"+Keys.ENTER);
        Utils.scroll(driver, 500);
        driver.findElement(By.id("edit-email")).sendKeys("Dipty@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am a SQA");
        Utils.scroll(driver, 500);
        Thread.sleep(6000);
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"\\src\\test\\resources\\picture.png");

        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();
        driver.switchTo().alert().accept();


        WebElement confirmationMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Thank you for your submission!']")));
        String messageActual = confirmationMessage.getText();
        String messageExpected = "Thank you for your submission!";

        assertEquals(messageExpected,messageActual);
        System.out.println(messageActual);




    }
}
