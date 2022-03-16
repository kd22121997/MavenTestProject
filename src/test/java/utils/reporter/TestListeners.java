package utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Constants;
import utils.base.BasePage;

import java.util.Arrays;

public class TestListeners extends BasePage implements ITestListener, Constants {

    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        var test = extent.createTest(result.getTestClass().getName() + "==>" + result.getMethod().getMethodName());
        test.assignCategory(result.getTestClass().getName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        String logTest = "<b>Test Method " + result.getMethod().getMethodName() + " passed </b>";
        var m = MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
    }

    public void onTestFailure(ITestResult result) {
        String exception = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><font color=red>Exception Occured, click to see the details:</font></summary>" +
                exception.replaceAll(",", "<br>") + "</details>\n");
/*        try{
            String screenshotpath = result.getAttribute("fail_screenshot").toString();
            System.out.println(screenshotpath);
            extentTest.get().fail("<b><font color=red>" + "Screenshot of Failure" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
        }
        catch (Exception ex){
            extentTest.get().fail("Test Failed, Cannot attach Screenshot");
        }*/
        String logTest = "<b>Test Method " + result.getMethod().getMethodName() + " Failed </b>";
        var m = MarkupHelper.createLabel(logTest, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
    }

    public void onTestSkipped(ITestResult result) {
        String logTest = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped </b>";
        var m = MarkupHelper.createLabel(logTest, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

/*    public static synchronized List<String> getOutput(ITestResult tr) {
        List<String> result = Lists.newArrayList();
        if (tr == null) {
            //guard against a possible NPE in scenarios wherein the test result object itself could be a null value.
            return result;
        }
        List<Integer> lines = m_methodOutputMap.get(tr.hashCode());
        if (lines != null) {
            for (Integer n : lines) {
                result.add(getOutput().get(n));
            }
        }

        return result;
    }*/
}
