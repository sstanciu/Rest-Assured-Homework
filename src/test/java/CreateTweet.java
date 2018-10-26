import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class CreateTweet {

    String consumerKey = "r1SktL1rdaFe3jsF7uaC46uky";
    String consumerSecret = "M3Y72JtfLgNruQMXJloFlXPIpd61QGV2rERgNe9ZmSMlPLyO62";
    String accesTokenSecret = "4923063747-u2L19lo0t5L5k6I5XLGlMGVN6cq2qzMWDsahCVR";
    String secretToken = "O28DGNswrwhdcLD9ehv97TJPMqHZ7OyMZxVpOjsmTbgCQ";

    long id;

    @Test
    public void createTweet() {

        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";

        Response response = given().
                auth().
                oauth(consumerKey, consumerSecret, accesTokenSecret, secretToken).
                queryParam("status","Simona Stanciu").
                when().
                post("/update.json").
                then().log().body().extract().response();

        id  = response.path("id");
        System.out.println(id);


    }

}
