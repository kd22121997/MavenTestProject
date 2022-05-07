package tests.othertests

import org.testng.annotations.Test
import utils.CommonUtils
import utils.Constants
import utils.ExcelUtils
import utils.base.UITestBase

class ExcelTest extends UITestBase {
    @Test
    void readingExcel(){
        ExcelUtils utils = new ExcelUtils()
        def data = utils.readExcel(Constants.TESTDATAPATH + "/ExcelFile.xlsx","Sheet1")
        println(data)
        aut.logger.logInfoTable([["all","all"],["all","all"]])
    }
}