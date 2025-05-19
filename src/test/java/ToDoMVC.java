import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToDoMVC {
    //private static ChromeDriver driver;
    private static ChromeDriver driver;

    public void addToList(String[] items) {
        for (String item : items) {
            WebElement input = driver.findElement(By.className("new-todo"));
            input.sendKeys(item + Keys.ENTER);
        }
    }

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void shouldLoadHomepage() throws InterruptedException {
        driver.get("https://todomvc.com/examples/react/dist/");
        String[] items = {"test1", "test2", "test3", "test4"};
        addToList(items);
    }
    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
