package ch9.system.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ListOfOwnersPage extends PetClinicPageObject{
    public ListOfOwnersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isReady() {
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("owners")));
    }

    public List<OwnerInfo> all() {
        List<OwnerInfo> owners = new ArrayList<>();

        WebElement table = driver.findElement(By.id("owners"));
        List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));

            String name = columns.get(0).getText().trim();
            String address = columns.get(1).getText().trim();
            String city = columns.get(2).getText().trim();
            String telephone = columns.get(3).getText().trim();
            String pets = columns.get(4).getText().trim();

            OwnerInfo ownerInfo = new OwnerInfo(name, address, city, telephone, pets);
            owners.add(ownerInfo);
        }

        return owners;
    }
}
