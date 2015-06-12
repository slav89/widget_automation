package sta.appium.android;

import org.apache.xpath.operations.String;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import sta.appium.android.util.AppiumTest;
import sta.appium.android.util.Helpers;

import static sta.appium.android.util.Helpers.back;
import static sta.appium.android.util.Helpers.for_text;

public class WidgetsDemoTest extends AppiumTest {

    @Before
    public void goToWidgetsScreen(){
        // Go to the widget screen
        wait(for_text("Starting test..."));
        back();
    }

    @org.junit.Test
    public void testTaskList() throws Exception {

        final String STRING_1 = "This is very important task #1";
        final String STRING_2 = "This is not so important task #2";
        final String STRING_3 = "This is extremely important task #3";


        // Resize tasks widget
        TouchAction ta = new TouchAction(Helpers.driver);
        ta.longPress(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/left_part_item"))).perform();
        wait(By.className("android.appwidget.AppWidgetHostView"));
        ta.press(918, 740).moveTo(1207, 740).release().perform();
        // Verify the new size of the widget
        WebElement taskWidget = Helpers.driver.findElement(By.className("android.appwidget.AppWidgetHostView"));
        int width = taskWidget.getSize().getWidth();
        Assert.assertEquals("The width of the object is incorrect", 1068, width);

        // Input the first task
        ta.tap(681, 375).perform();
        Helpers.driver.findElement(By.id("com.lucky.notewidget:id/etText")).sendKeys(STRING_1);
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/ok"))).perform();
        // Assert the test is present
        Assert.assertTrue("Expected text is not displayed", Helpers.find(STRING_1).isDisplayed());

        // Input the second task
        ta.tap(681, 434).perform();
        Helpers.driver.findElement(By.id("com.lucky.notewidget:id/etText")).sendKeys(STRING_2);
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/ok"))).perform();
        // Assert the test is present
        Assert.assertTrue("Expected text is not displayed", Helpers.find(STRING_2).isDisplayed());

        // Input the third task
        ta.tap(681, 510).perform();
        Helpers.driver.findElement(By.id("com.lucky.notewidget:id/etText")).sendKeys(STRING_3);
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/ok"))).perform();
        // Assert the test is present
        Assert.assertTrue("Expected text is not displayed", Helpers.find(STRING_3).isDisplayed());

        // Select task #1  and task #3
        ta.tap(1141, 365).perform();
        wait(By.className("android.appwidget.AppWidgetHostView"));
        ta.tap(1141, 506).perform();

        // Clear selected tasks
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/title"))).perform();
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/checkBoxDeleteSelected"))).perform();
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/ok"))).perform();
        // Assert deleted tasks are not displayed anymore
        Assert.assertEquals("The text is still displayed", "", Helpers.driver.findElement(By.xpath("//android.widget.ListView[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[1]")).getText());
        Assert.assertEquals("The text is still displayed", "", Helpers.driver.findElement(By.xpath("//android.widget.ListView[1]/android.widget.FrameLayout[3]/android.widget.LinearLayout[1]/android.widget.TextView[1]")).getText());

        // Return widgets to the initial state
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/title"))).perform();
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/checkBoxDropTable"))).perform();
        ta.tap(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/ok"))).perform();

        ta.longPress(Helpers.driver.findElement(By.id("com.lucky.notewidget:id/left_part_item"))).perform();
        wait(By.className("android.appwidget.AppWidgetHostView"));
        ta.press(1207, 740).moveTo(918, 740).release().perform();
        ta.tap(681, 375).perform();

    }

    @org.junit.Test
    public void testVolSlider() throws Exception {

        // Set brightness to automatic
        TouchAction ta = new TouchAction(Helpers.driver);
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_1_icon"))).perform();
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/toggle_btn_auto"))).perform();
        // Assert brightness is set to automatic
        Assert.assertTrue("'Automatic' is not displayed", Helpers.find("automatic").isDisplayed());

        // Change Ringtone volume
        ta.tap(1500, 1000).perform();
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_2_icon"))).perform();
        ta.press(1158, 315).waitAction(300).moveTo(1549, 315).waitAction(200).release().perform();

        // Assert ringtone volume is changed correctly
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_2_icon"))).perform();
        String currentVolumeValue = Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/txtCurrentValue")).getText();
        Assert.assertTrue("The volume value is incorrect", currentVolumeValue.equals("7"));
        ta.tap(1500, 1000).perform();

        // Change System volume
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_6_icon"))).perform();
        ta.press(1457, 315).waitAction(300).moveTo(1177, 315).waitAction(200).release().perform();

        // Assert system volume is changed correctly
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_6_icon"))).perform();
        currentVolumeValue = Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/txtCurrentValue")).getText();
        Assert.assertTrue("The volume value is incorrect", currentVolumeValue.equals("2"));
        ta.tap(1500, 1000).perform();

        // Return widgets to the initial state
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_1_icon"))).perform();
        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/toggle_btn_auto"))).perform();
        ta.tap(1500, 1000).perform();

        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_2_icon"))).perform();
        ta.press(526, 315).moveTo(1158, 315).release().perform();

        ta.tap(Helpers.driver.findElement(By.id("de.hinterhofapps.sliderwidget:id/btn_6_icon"))).perform();
        ta.press(1177, 315).moveTo(1457, 315).release().perform();

    }


}