package DataDrivenTesting;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class apachi_POI {

@DataProvider(name="exceldata")
    public Object[][] dataexcelm(){
    //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
    //1- read file
       File x=new File(System.getProperty("user.dir")+"\\Resources\\DDT\\Data.xlsx");
    //2-read sheet
    XSSFSheet sheet= null;
    try {
        sheet = new XSSFWorkbook(x).getSheet("sheet1");
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (InvalidFormatException e) {
        throw new RuntimeException(e);
    }
    //3-define number of Rows and coulums
      int rows=sheet.getPhysicalNumberOfRows();
      int colums= sheet.getRow(0).getLastCellNum();
    //4- define Two dimentianl array
      Object [][] data;
      data= new String [rows-1][colums];
    //5- read valuse form cells rows an coulums
      for(int x1=1;x1<rows;x1++){
          for(int y=0;y<colums;y++){
              XSSFRow R_IN=sheet.getRow(x1);
              data[x1-1][y]=R_IN.getCell(y).getStringCellValue();
          }
      }
    return data;
}

 }
