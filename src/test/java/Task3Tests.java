import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task3Tests {
    String booksURI="https://bookstore.toolsqa.com/BookStore/v1/Books";

    @Test
    public void checkStatusCode(){
        int statusCode= given()
                        .when()
                        .get(booksURI)
                        .getStatusCode();
        System.out.println("The status code is"+statusCode);
        Assert.assertEquals(statusCode,200);

    }

    @Test
    public void checkIsbn(){
       given()
               .when()
               .get(booksURI)
               .then()
               .assertThat()
               .body("books[-1].isbn",equalTo("9781593277574"));

    }

    @Test
    public void checkPages(){
        given()
                .when()
                .get(booksURI)
                .then()
                .assertThat()
                .body("books[0].pages", equalTo(234),
                "books[1].pages", equalTo(254));

    }
}

