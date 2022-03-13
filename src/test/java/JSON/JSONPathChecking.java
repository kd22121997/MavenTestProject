package JSON;

import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

public class JSONPathChecking {

    public static void main(String[] args) throws IOException {
        var object =
                JsonPath.parse(new File("./src/main/resources/JSONFiles/BasicJsonfile.json"));
    String jsonString = object.jsonString();
    var ElementaryEducation = JsonPath.read(jsonString,"$['TotalEducation']['Elementary']");
    System.out.println("Only Name retrieved in Json File $['TotalEducation']['Elementary']: " + ElementaryEducation);
    System.out.println("Whole JSON File Retrieved is: \n" + jsonString);
    //Update JSON
        object.set("['Name of Person']","Aman");
        System.out.println("Whole JSON File after update made :\n  "+ object.jsonString());
    }
}
