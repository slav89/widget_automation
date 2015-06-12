//package sta.appium.android;
//
///**
// * Created by slavbukhal on 3/30/15.
// */
//
//import org.apache.commons.logging.LogFactory;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.rules.TestRule;
//import org.junit.rules.TestWatcher;
//import org.junit.runner.Description;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.net.URL;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.concurrent.TimeUnit;
//
//import io.appium.java_client.android.AndroidDriver;
//import sta.appium.android.util.Helpers;
//import sta.appium.android.util.Parallelized;
//
//
//@RunWith(Parallelized.class)
//public class BaseTest {
//
//    static {
//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
//    }
//
//    private String port;
//
//
//    /** wait wraps Helpers.wait **/
//    public static WebElement wait(By locator) {
//        return Helpers.wait(locator);
//    }
//
//    @Rule
//    public TestRule printTests = new TestWatcher() {
//        protected void starting(Description description) {
//            System.out.print("  test: " + description.getMethodName());
//        }
//
//        protected void finished(Description description) {
//            final String session = getSessionId();
//
//            System.out.println(session);
//        }
//    };
//
//    private String sessionId;
//
//    /** Run before each test **/
//    @Before
//    public void setUp() throws Exception {
//        System.out.println("Current port: " + port);
//            }
//
//    /** Run after each test **/
//    @After
//    public void tearDown() throws Exception {
//        System.out.println("End of the test");
//    }
//
//    /** If we're not on Sauce then return null otherwise SauceOnDemandTestWatcher will error. **/
//    public String getSessionId() {
//        return sessionId;
//    }
//
//    @Parameterized.Parameters
//    public static Collection ports() {
//        return Arrays.asList(new Object[][]{
//                {"4273"},
//                {"4274"}
//        });
//    }
//
//    public BaseTest(String port)
//    {
//        this.port = port;
//    }
//
//}
