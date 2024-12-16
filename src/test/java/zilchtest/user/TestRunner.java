package zilchtest.user;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import zilchtest.utils.DriverManager;
import zilchtest.utils.GlobalParams;
import zilchtest.utils.ServerManager;
import org.apache.logging.log4j.ThreadContext;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/zilchtest/user/features",
        glue="zilchtest.user.steps",
       plugin={"pretty","html:target/cucumber-reports.html"})


public class TestRunner {

    @BeforeClass
    public static void initialize() throws Exception {
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_"
                + params.getDeviceName());

        new ServerManager().startServer();
        new DriverManager().initializeDriver();
    }

    @AfterClass
    public static void quit(){
        DriverManager driverManager = new DriverManager();
        if(driverManager.getDriver() != null){
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
        ServerManager serverManager = new ServerManager();
        if(serverManager.getServer() != null){
            serverManager.getServer().stop();
        }
    }
}