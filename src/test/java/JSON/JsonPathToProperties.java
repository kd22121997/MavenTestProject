package JSON;

import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class JsonPathToProperties {

    public static void main(String[] args) throws IOException {
        String filePath = "./src/main/resources/JSONFiles/BasicJsonfile.json";
        var object =
                JsonPath.parse(new File(filePath));
        String jsonString = object.jsonString();
        Json_Parser j = new Json_Parser(jsonString);

        WritePropertiesFile(j.getPathList(),jsonString);

    }

    public static void WritePropertiesFile(List<String> Paths, String jsonString) throws IOException {
        Properties newConfigProperty = new Properties();
        Properties existingConfigProperty = new Properties();

        File file = new File("./src/main/resources/Properties/JsonPathRepository.properties");
        FileInputStream fileIn = new FileInputStream(file);
        existingConfigProperty.load(fileIn);
        for (String jPath: Paths)
        {
            System.out.println(jPath);
            String value = JsonPath.read(jsonString,jPath).toString();
            System.out.println(value);

            if(existingConfigProperty.containsKey(jPath)) {
                existingConfigProperty.remove(jPath);
                existingConfigProperty.setProperty(jPath,value);
            }
            else {
                newConfigProperty.setProperty(jPath, value);
            }
        }
        FileOutputStream fileOut = new FileOutputStream(file);
        existingConfigProperty.store(fileOut,null);
        newConfigProperty.store(fileOut, null);
        fileOut.close();
    }
}
