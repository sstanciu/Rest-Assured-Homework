import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class FindTweetAssert {
    String consumerKey = "r1SktL1rdaFe3jsF7uaC46uky";
    String consumerSecret = "M3Y72JtfLgNruQMXJloFlXPIpd61QGV2rERgNe9ZmSMlPLyO62";
    String accesTokenSecret = "4923063747-u2L19lo0t5L5k6I5XLGlMGVN6cq2qzMWDsahCVR";
    String secretToken = "O28DGNswrwhdcLD9ehv97TJPMqHZ7OyMZxVpOjsmTbgCQ";

    ValidatableResponse id;
    String screen_name;
    String text;


    @Test
    public void createTweet() {

        RestAssured.baseURI = "https://api.twitter.com/1.1";
        RestAssured.basePath = "/statuses";

        Response response = given().
                auth().
                oauth(consumerKey, consumerSecret, accesTokenSecret, secretToken).
                queryParam("status", "Simona_Stanciu").
                when().
                post("/update.json").
                then().log().body().extract().response();

        id = response.path("id");
        screen_name = response.path("user.screen_name");
        text = response.path("text");
        System.out.println("response vars: " + id + " " + screen_name + " " + text);

    }
    @Test
    public void getTweet_assertion(){
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses/user_timeline.json";

        Response response =  given().log().all().
                auth().
                oauth(consumerKey, consumerSecret, accesTokenSecret, secretToken).
                queryParam("text", text).
                when().
                get().
                then().log().all().extract().response();

        List<String> tweets = response.path("text");
        System.out.println(tweets.get(tweets.indexOf("Simona_Stanciu")));

    }

}

