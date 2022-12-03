package org.example.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

public class CustomWait {
    FluentWait<WebDriver> fluentWait;

    CustomWait(WebDriver driver) {
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(60));
        fluentWait.pollingEvery(Duration.ofSeconds(3));
    }

    void waitUntilPresent(By by) {
        Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(by);
                System.out.printf(by + " :-> is present? " + element.isDisplayed());
                return element.isDisplayed();
            }
        };
        fluentWait.until(func);
    }

}
