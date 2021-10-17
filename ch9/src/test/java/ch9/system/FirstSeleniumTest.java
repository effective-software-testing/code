package ch9.system;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstSeleniumTest {

    @Test
    void firstSeleniumTest() {
        // select which driver to use
        WebDriver browser = new SafariDriver();

        // visit a page
        browser.get("http://localhost:8080");

        // find an HTML element in the page
        WebElement welcomeHeader = browser.findElement(By.tagName("h2"));

        // assert it contains what we want
        assertThat(welcomeHeader.getText()).isEqualTo("Welcome");

        // close the browser and the selenium session
        browser.close();
    }
}
