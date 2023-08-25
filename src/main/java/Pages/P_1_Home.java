package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class P_1_Home {
    private WebDriver d;
    public P_1_Home(WebDriver d) {this.d=d;}
    public void print1(){System.out.println("We are in p1_m");}
    private By Search_filed = By.id("gh-ac");
    private By click_Search_filed = By.id("gh-btn");
    public  void Searchfor(String search_word){
        d.findElement(Search_filed).sendKeys(search_word);}
    public P_2_SearchResult Press_search_botom() {

        waitf().until(ExpectedConditions.visibilityOfElementLocated(click_Search_filed));

        d.findElement(click_Search_filed).click();

        try {
            Alert alert = d.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert data: " + alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
        return new P_2_SearchResult(d);
    }
    public boolean Assertion_Current_On_Homepage() {
            return d.getCurrentUrl().equalsIgnoreCase("https://www.ebay.com/");
    }
    public Wait waitf() {
        Wait wait = new FluentWait(d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }
}
