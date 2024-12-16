package zilchtest.user.steps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import zilchtest.utils.PropReader;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zilchtest.user.models.UserBuilder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class ApiTest {

    private Response response;
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserBuilder userBuilder;

    private static final Logger apiTestLog = LoggerFactory.getLogger(ApiTest.class);

    @Given("I call the jsonplaceholder api with posts route")
    public void i_call_the_jsonplaceholder_api_with_posts_route() {
        try {
            apiTestLog.info("API Get Test for Posts with user id");
            response =  given()
                    .headers("Accept", "application/json")
                    .get( PropReader.getProperty("api.baseurl")+ "/posts/1")
                    .then()
                    .extract()
                    .response();


        } catch (Exception e) {
            apiTestLog.error("Failed to execute JSON Placehoder test for user " + e.getMessage());

        }
    }

    @Given("I validate the reponse code")
    public void i_validate_response_Code() {
        apiTestLog.info("Verifying response code ");
        Assert.assertEquals(200, response.getStatusCode());

    }

    @Given("I verify output contains valid user id")
    public void i_verify_userid() {
        try {
         userBuilder = objectMapper.readValue(response.getBody().print(), new TypeReference<UserBuilder>() {
         });
         assertTrue(userBuilder.getuserId() == 1);

        } catch (Exception e) {
            apiTestLog.error("Unable to parse the reponse ");

        }
    }


}

