package tests.apitest

import org.testng.annotations.Test
import utils.CommonUtils
import utils.Constants
import utils.ExcelUtils

class ExcelTest {
    @Test
    void readingExcel(){
        ExcelUtils utils = new ExcelUtils();
        def data = utils.readExcel(Constants.TESTDATAPATH + "/ExcelFile.xlsx","Sheet1")
        println(data);
    }
}
