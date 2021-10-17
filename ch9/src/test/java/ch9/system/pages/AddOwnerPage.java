package ch9.system.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddOwnerPage extends PetClinicPageObject {
    public AddOwnerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void visit() {
        visit("/owners/new");
    }

    @Override
    public void isReady() {
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-owner-form")));
    }

    public OwnerInformationPage add(AddOwnerInfo ownerToBeAdded) {
        driver.findElement(By.id("firstName")).sendKeys(ownerToBeAdded.getFirstName());
        driver.findElement(By.id("lastName")).sendKeys(ownerToBeAdded.getLastName());
        driver.findElement(By.id("address")).sendKeys(ownerToBeAdded.getAddress());
        driver.findElement(By.id("city")).sendKeys(ownerToBeAdded.getCity());
        driver.findElement(By.id("telephone")).sendKeys(ownerToBeAdded.getTelephone());

        driver.findElement(By.id("add-owner-form"))
                .findElement(By.tagName("button"))
                .click();


        OwnerInformationPage ownerInformationPage = new OwnerInformationPage(driver);
        ownerInformationPage.isReady();
        return ownerInformationPage;
    }
}
