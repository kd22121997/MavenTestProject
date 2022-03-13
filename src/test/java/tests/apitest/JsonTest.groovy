package tests.apitest

import com.jayway.jsonpath.JsonPath
import org.testng.annotations.Test
import utils.CommonUtils

class JsonTest {

    @Test
    void jsonActions() throws IOException {
        var jsonContext = JsonPath.parse(new File("./src/test/resources/JSONFiles/BasicJsonfile.json"))

        //Set value of a particular node of a Json File using Json path
        jsonContext.set("\$.Married",true)

        //Add new record to the List of the Node
        jsonContext.add("\$.JobAcrossCareer", ["NameOfCompany":"Oracle","Location":"Hyderabad"])
        jsonContext.add("\$.JobAcrossCareer", ["NameOfCompany":"Microsoft","Location":"Bangalore"])

        System.out.println("JSON After adding new values to JobsAcrossCareer: \n" + CommonUtils.prettyPrintJson(jsonContext.json()))

        //Read value of a particular node using Json path
        String highSchool = jsonContext.read("\$.TotalEducation.HighSchool")
        System.out.println("HighSchool Retrieved in Json File is: " + highSchool)

        //Delete value of the List
        jsonContext.delete("\$.JobAcrossCareer[0]")
        System.out.println("JSON After deleting first value of the array: \n" + CommonUtils.prettyPrintJson(jsonContext.json()))

        //Complex Scenarios #1: Get the company Names located in Hyderabad
        var FirmNames = jsonContext.read("\$.JobAcrossCareer[?(@.Location=='Hyderabad')].NameOfCompany")
        System.out.println("Firms having location as Hyderabad: " + FirmNames);

        //Complex Scenarios #2: retrieving from nested json node using relative Json Path
        var Skills = jsonContext.read("\$.Skills..[?(@.Status == 'Bad')]")
        System.out.print(Skills)

    }
}
