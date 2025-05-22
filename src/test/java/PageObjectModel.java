
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class PageObjectModel {
    private WebDriver driver;

    public PageObjectModel(WebDriver driver) {
        this.driver = driver;
    }

    public void addToList(String[] items) {
        for (String item : items) {
            WebElement input = driver.findElement(By.className("new-todo"));
            input.sendKeys(item + Keys.ENTER);
        }
    }

    public int listSize() {
        List<WebElement> itemList = driver.findElements(By.cssSelector("ul.todo-list li"));
        return itemList.size();
    }

    public void setCheckboxState(int index, boolean shouldBeChecked) {
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".todo-list li .toggle"));
        WebElement checkBox = checkBoxes.get(index);
        if (checkBox.isSelected() != shouldBeChecked) {
            checkBox.click();
        }
    }

    public void setAllCheckboxesState(boolean shouldBeChecked) {
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".todo-list li .toggle"));
        for (WebElement checkBox : checkBoxes) {
            if (checkBox.isSelected() != shouldBeChecked) {
                checkBox.click();
            }
        }
    }

    public void deleteOneItem() {
        WebElement item = driver.findElement(By.cssSelector(".todo-list li"));
        Actions actions = new Actions(driver);
        actions.moveToElement(item).perform();

        WebElement deleteButton = driver.findElement(By.cssSelector(".todo-list li .destroy"));
        deleteButton.click();
    }

    public void deleteAllItems() {
        List<WebElement> items = driver.findElements(By.cssSelector(".todo-list li .destroy"));
        for (WebElement item : items) {
            item.click();
        }
    }

    public void filterSetup() {
        String[] itemsToAdd = {"test1", "test2"};
        addToList(itemsToAdd);
        setAllCheckboxesState(true);
        String[] itemsToAdd2 = {"test3", "test4"};
        addToList(itemsToAdd2);
    }

    public String getStatusBarCount() {
        WebElement statusMessage = driver.findElement(By.cssSelector(".todo-count"));
        return statusMessage.getText();
    }

    public void locateDownArrow() {
        WebElement downArrow = driver.findElement(By.cssSelector(".toggle-all"));
        downArrow.click();
    }
}
