package Test;
import Base.Setup;
import DataDrivenTesting.apachi_POI;
import Pages.P_2_SearchResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class T1 extends Setup {

    @Test(dataProvider = "exceldata",dataProviderClass = apachi_POI.class)
    public void Test1(String productname,String search_keyWord) throws InterruptedException {
        Reporter.log("________________Start testcase__________");
        //1- Instansiate soft assertion
        SoftAssert ass = new SoftAssert();
        //1- assert we are in home page
        ass.assertTrue(Home_P1.Assertion_Current_On_Homepage());
        //2-search for "productname"
        Home_P1.Searchfor(productname);
        //3-press botton to serach
        P_2_SearchResult P2_Search_Result=Home_P1.Press_search_botom();
        //4-From the left hand side filter using "Transmission" -> "Manual"
        P2_Search_Result.press_manual_OPT();
        //5-Validate the results obtained.
                //a- validate if number pf resulst equals to number of products
                 ass.assertTrue( P2_Search_Result.assertion_result_count());
                //b-validate if all element in results contains the search keyword
                ass.assertTrue( P2_Search_Result.assertion_result_contains(search_keyWord));
        //Print/log the number of obtained search results. and if not contain keySearch Word : false
        Reporter.log(P2_Search_Result.Print_log_results(search_keyWord));

        Reporter.log("_________end testcase___________");
        ass.assertAll();
}

}

