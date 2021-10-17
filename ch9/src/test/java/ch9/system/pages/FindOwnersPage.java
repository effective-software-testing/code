package ch9.system.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class FindOwnersPage extends PetClinicPageObject {

    public FindOwnersPage(WebDriver driver) {
        super(driver);
    }

    public ListOfOwnersPage findOwners(String ownerLastName) {

        driver.findElement(By.id("lastName")).sendKeys(ownerLastName);
        WebElement findOwnerButton = driver.findElement(By.id("search-owner-form")).findElement(By.tagName("button"));
        findOwnerButton.click();

        ListOfOwnersPage listOfOwnersPage = new ListOfOwnersPage(driver);
        listOfOwnersPage.isReady();
        return listOfOwnersPage;
    }

    public AddOwnerPage addOwner() {
        Optional<WebElement> link = driver.findElements(By.tagName("a"))
                .stream().filter(el -> el.getText().equals("Add Owner")).findFirst();
        link.get().click();

        AddOwnerPage addOwnerPage = new AddOwnerPage(driver);
        addOwnerPage.isReady();
        return addOwnerPage;
    }

    public void visit() {
        visit("/owners/find");
    }

    @Override
    public void isReady() {
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-owner-form")));
    }
}
