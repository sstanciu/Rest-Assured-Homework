import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;


    public class FindTweetHamcrest {
        String consumerKey = "r1SktL1rdaFe3jsF7uaC46uky";
        String consumerSecret = "M3Y72JtfLgNruQMXJloFlXPIpd61QGV2rERgNe9ZmSMlPLyO62";
        String accesTokenSecret = "4923063747-u2L19lo0t5L5k6I5XLGlMGVN6cq2qzMWDsahCVR";
        String secretToken = "O28DGNswrwhdcLD9ehv97TJPMqHZ7OyMZxVpOjsmTbgCQ";

        long id;
        String screen_name;

        @Test
        public void createTweet() {

            RestAssured.baseURI = "https://api.twitter.com";
            RestAssured.basePath = "/1.1/statuses";

            Response response = given().
                    auth().
                    oauth(consumerKey, consumerSecret, accesTokenSecret, secretToken).
                    queryParam("status","Simo Stanciu").
                    when().
                    post("/update.json").
                    then().log().body().extract().response();

            id  = response.path("id");
            screen_name = response.path("user.screen_name");
            System.out.println("response vars: " + id + " " + screen_name);

        }

        @Test
        public void findTweet_hamcrest(){
            RestAssured.baseURI = "https://api.twitter.com";
            RestAssured.basePath = "/1.1/statuses/user_timeline.json";

            given().
                    auth().
                    oauth(consumerKey, consumerSecret, accesTokenSecret, secretToken).
                    queryParam("screen_name", screen_name).
                    when().
                    get().
                    then().log().all().
                    assertThat().
                    statusCode(HttpStatus.SC_OK).
                    body("text", hasItems("Simo Stanciu")).log();

        }
}
