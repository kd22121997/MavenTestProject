package tests.othertests

import org.testng.annotations.Test
import utils.CSVUtils
import utils.CommonUtils
import utils.Constants
import utils.ExcelUtils
import utils.apiutils.JsonContext
import utils.base.UITestBase

class ExcelTest extends UITestBase {
    @Test
    void readingExcel(){
        ExcelUtils utils = new ExcelUtils()
        def data = utils.readExcel(Constants.TESTDATAPATH + "/ExcpelFile.xlsx","Sheet1")
        println(data)
        aut.logger.logInfoTable([["all","all"],["all","all"]])
    }

    @Test
    void "Reading CSV"()
    {
        CSVUtils csvUtils = new CSVUtils()
        def csvdata =  csvUtils.getCSVData("C:\\Users\\lenovo\\Desktop\\Book1.csv")
        println(csvdata)
    }
}