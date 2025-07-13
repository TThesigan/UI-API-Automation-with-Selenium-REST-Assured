package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BoardPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC1_UiTest extends BaseClass {

    @Test
    public void verifyTheUiOperation() throws InterruptedException {
        // Verify new Board creation
        HomePage home =new HomePage(driver);
        home.createNewBoard(testData.boardName);
        BoardPage board= new BoardPage(driver);
        Assert.assertEquals(board.getCurrentBoardName(),testData.boardName);

        // Verify new Lists creation
        board.createNewList(testData.listNames.get(0));
        board.createNewList(testData.listNames.get(1));
        Assert.assertEquals(board.getListNameByIndex(0), testData.listNames.get(0));
        Assert.assertEquals(board.getListNameByIndex(1), testData.listNames.get(1));

        // Verify the List deletion
        int beforeDelete = board.getListNameCount();
        board.deleteList();
        board.waitUntilListNameCountDecreases(beforeDelete);
        int afterDelete = board.getListNameCount();
        Assert.assertEquals(afterDelete, testData.listNames.size() - 1);
    }
}
