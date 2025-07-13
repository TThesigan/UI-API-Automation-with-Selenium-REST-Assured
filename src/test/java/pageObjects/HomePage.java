package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(), 'Create new board')]")
    WebElement btnCreateBoard;

    @FindBy(xpath = "//input[@placeholder='Add board title']")
    WebElement txtAddBoard;


    public void createNewBoard(String boardName) {
        btnCreateBoard.click();
        txtAddBoard.sendKeys(boardName + Keys.ENTER);
    }
}
