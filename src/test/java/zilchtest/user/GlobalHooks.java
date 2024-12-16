package zilchtest.user;
import com.fasterxml.jackson.databind.ObjectMapper;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.openqa.selenium.OutputType;
import zilchtest.user.pages.BasePage;
import zilchtest.utils.DriverManager;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GlobalHooks {

    @Before
    public void initialize() throws Exception {
        BasePage basepage = new BasePage();
        basepage.closeApp();
        basepage.launchApp();
    }
    @After
    public void quit(Scenario scenario) throws IOException {
        if(scenario.isFailed()) {
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
