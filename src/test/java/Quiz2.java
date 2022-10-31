import io.qameta.allure.Epic;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;

@Epic("Quiz Tests")
public class Quiz2 {
    String userURI="https://bookstore.toolsqa.com/Account/v1/User";
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());




    @Test(description = "Pass empty username and password")
    public void test1(){
        int statusCode=
                given().contentType(ContentType.JSON).body("{\n" +
                                "  \"userName\": \"\",\n" +
                                "  \"password\": \"\"\n" +
                                "}")
                        .when().post(userURI).getStatusCode();
        Assert.assertEquals(statusCode,400);
    }
    @Test(description = "Pass correct username and easy password")
    public void test2(){
        String message =  given().contentType(ContentType.JSON).body("{\n" +
                        "  \"userName\": \"annat\",\n" +
                        "  \"password\": \"123\"\n" +
                        "}")
                .when().post(userURI).then().
                log().all()
                .extract()
                .jsonPath().getString("message");
        Assert.assertEquals(message,"Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
    }
    @Test(description = "Pass correct username and strong password")
    public void test3(){
        int responseCode =  given().contentType(ContentType.JSON).body("{\n" +
                        "  \"userName\": \"annat"+timeStamp+"\",\n" +
                        "  \"password\": \"Aa@"+timeStamp+"\"\n" +
                        "}")
                .when().post(userURI).getStatusCode();
        Assert.assertEquals(responseCode,201);
    }

}
