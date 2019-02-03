package com.gauge.bdd.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public WebDriver getDriver(Capabilities capabilities) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

}
