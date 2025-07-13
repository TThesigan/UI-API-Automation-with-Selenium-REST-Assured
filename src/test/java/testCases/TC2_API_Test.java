package testCases;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.List;
import static io.restassured.RestAssured.given;

public class TC2_API_Test {
    String baseUrl = "http://localhost:3000/api";
    int boardId = 2;

    @BeforeClass
    public void apiSetup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void createAndDeleteListThenVerify() {
        // Get existing list IDs
        List<Integer> beforeListIds = given()
                .header("Accept", "application/json")
                .when()
                .get("/boards/" + boardId + "/lists")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("id");


        // Create new list
        String listName = "List By RestAssured";
        String requestBody = String.format("{\"boardId\":%d,\"name\":\"%s\"}", boardId, listName);

        int newListId = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/lists")
                .then()
                .statusCode(201)
                .body("name", equalTo(listName))
                .extract()
                .jsonPath()
                .getInt("id");


        // Delete the new list
        given()
                .when()
                .delete("/lists/" + newListId)
                .then()
                .statusCode(200);

        // Verify the deletion
        List<Integer> afterListIds = given()
                .header("Accept", "application/json")
                .when()
                .get("/boards/" + boardId + "/lists")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("id");

        Assert.assertFalse(afterListIds.contains(newListId), "Deleted list should not be in the list");
    }
}
