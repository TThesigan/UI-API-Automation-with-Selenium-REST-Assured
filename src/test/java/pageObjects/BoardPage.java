package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BoardPage extends BasePage{

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='board-title']")
    WebElement boardName;

    @FindBy(xpath = "//input[@data-cy='add-list-input']")
    WebElement txtAddList;

    @FindBy(css = "svg.cursor-pointer:nth-of-type(1)")
    WebElement firstListActionButton;

    @FindBy(xpath = "//input[@data-cy='list-name']")
    List<WebElement> listNames;

    @FindBy(xpath = "//div[@data-cy='delete-list']")
    WebElement btnDeleteList;


    public void createNewList(String listName) {
        txtAddList.sendKeys(listName + Keys.ENTER);
    }

    public void deleteList(){
        firstListActionButton.click();
        btnDeleteList.click();
    }

    public String getCurrentBoardName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(boardName));
        return boardName.getAttribute("value");
    }

    public String getListNameByIndex(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(listNames));
        return listNames.get(index).getAttribute("value");
    }

    public int getListNameCount() {
        return driver.findElements(By.xpath("//input[@data-cy='list-name']")).size();
    }

    public void waitUntilListNameCountDecreases(int previousCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> getListNameCount() == previousCount - 1);
    }
}
