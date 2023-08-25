package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class P_2_SearchResult {
    private WebDriver d;
    private  List<WebElement> product_list;
    public P_2_SearchResult(WebDriver d) {
        this.d=d;
    }

    private By manual_filed = By.xpath("//div[@id=\"x-refine__group_1__0\"]//input[@aria-label=\"Manual\"]");
    private By result_count = By.xpath("(//*[text()=\" results for \"]//span[@class=\"BOLD\"])[1]");
    private By Search_results_fileds = By.xpath("//ul[@class=\"srp-results srp-list clearfix\"]//li[@data-viewport] //div[@class=\"s-item__title\"]");

    public void press_manual_OPT(){
        d.findElement(manual_filed).click();
    }
    public boolean assertion_result_count(){
        product_list=d.findElements(Search_results_fileds);
//        System.out.println(Integer.parseInt(d.findElement(result_count).getText())+"___________>1");
//        System.out.println(d.findElements(Search_results_fileds).size()+"___________>1");
        if(d.findElements(Search_results_fileds).size()==Integer.parseInt(d.findElement(result_count).getText())){return true;} else {return false;}
    }
    public boolean assertion_result_contains(String search_keyWord1,String search_keyWord2) throws InterruptedException {
//       //iterate webelement for while loop
//        Iterator<WebElement> variable = product_list.iterator();
        Boolean x=true;
//        while(variable.hasNext()) {
//            System.out.println(variable.next().getText());
//            //if any value is false x will be false
//            if(variable.next().getText().toLowerCase().contains(search_keyWord)==false){x=false;}else {x=true;}
//            Thread.sleep(100);
//        }
        for (int a=0;a<= product_list.size(); a++) {
//            System.out.println(product_list.get(a).getText().toLowerCase());
//            System.out.println(product_list.get(a).getText().toLowerCase().contains("mazda"))
            if(!product_list.get(a).getText().toLowerCase().contains(search_keyWord1)){x=false;break;}
            else if(!product_list.get(a).getText().toLowerCase().contains(search_keyWord2)){x=false;break;}
            else  {x=true;}
            Thread.sleep(100);
        }
        return x;
    }
    public String Print_log_results(String searching_keyWord1,String searching_keyWord2 ) throws InterruptedException {

        ArrayList<String> productlist=new  ArrayList<>();
        for (int a=0;a< product_list.size(); a++) {
            productlist.add(product_list.get(a).getText().toLowerCase()+" : "+product_list.get(a).getText().toLowerCase().contains(searching_keyWord1)+"(1) "+" : "+product_list.get(a).getText().toLowerCase().contains(searching_keyWord2)+"(1) ");
            Thread.sleep(100);
        }
        return String.valueOf(productlist).replace(",","\n").replace("[","").replace("]","");
    }
    public Wait waitf() {
        Wait wait = new FluentWait(d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }
}
