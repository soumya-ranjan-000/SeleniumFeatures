package org.example.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ExplicitWait {
    WebDriver driver = null;
    FluentWait<WebDriver> fluentWait;
    CustomWait customWait;

    @BeforeTest
    void setup() {
        System.setProperty("webdriver.chrome.driver", "E:\\Web Development\\WebDrivers\\Chrome\\chromedriver_108.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(60));
        fluentWait.pollingEvery(Duration.ofSeconds(3));
        fluentWait.ignoring(NoSuchElementException.class);
        customWait = new CustomWait(driver);
    }

    @Test
    void practiseFluentWait() {
        driver.get("https://shop.demoqa.com/my-account");
        driver.navigate().refresh();
        fluentWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dismiss")));
        driver.findElement(By.linkText("Dismiss")).click();
//      fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[text()='My Account']")));
        Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(By.xpath("//li/a[text()='My Account']"));
                System.out.println("href: " + element.getAttribute("href"));
                if (element.getAttribute("href") != "") return true;
                return false;
            }
        };
        fluentWait.until(func);
        WebElement myAccount = driver.findElement(By.xpath("//li/a[text()='My Account']"));
        myAccount.click();
    }

    @AfterTest
    void close() throws InterruptedException {
        Thread.sleep(3000L);
        driver.quit();
    }
}
