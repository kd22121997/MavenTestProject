package JSON;

import com.fasterxml.jackson.core.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.filter.ValueNodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

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
