package ch9.system;

import ch9.system.pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FindOwnersFlowTest {
    private FindOwnersPage page = new FindOwnersPage(driver);

    protected static WebDriver driver = new SafariDriver();

    @AfterAll
    static void close() {
        driver.close();
    }

    @Test
    void findOwnersBasedOnTheirLastNames() {
        AddOwnerInfo owner1 = new AddOwnerInfo("John", "Doe", "some address", "some city", "11111");
        AddOwnerInfo owner2 = new AddOwnerInfo("Jane", "Doe", "some address", "some city", "11111");
        addOwners(owner1, owner2);

        page.visit();

        ListOfOwnersPage listPage = page.findOwners("Doe");
        List<OwnerInfo> all = listPage.all();

        assertThat(all).hasSize(2).
                containsExactlyInAnyOrder(owner1.toOwnerInfo(), owner2.toOwnerInfo());
    }

    private void addOwners(AddOwnerInfo... owners) {
        AddOwnerPage addOwnerPage = new AddOwnerPage(driver);

        for (AddOwnerInfo owner : owners) {
            addOwnerPage.visit();
            addOwnerPage.add(owner);
        }
    }
}
