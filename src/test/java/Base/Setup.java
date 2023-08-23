package Base;

import Additional_package.Event_report;
import Pages.P_1_Home;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class Setup {
    protected EventFiringWebDriver d;
    protected P_1_Home Home_P1;
    @BeforeMethod
    public void start() throws InterruptedException {
        d= new EventFiringWebDriver(new EdgeDriver());
        d.navigate().to("https://www.ebay.com/");
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Home_P1 =new P_1_Home(d);
        d.register(new Event_report());

    }
    @AfterMethod
    public void end(ITestResult result) throws InterruptedException {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = ((TakesScreenshot) d);
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot.toPath(), new File("resources/screenshots/" + result.getName() + ".png").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        d.quit();
    }
}
