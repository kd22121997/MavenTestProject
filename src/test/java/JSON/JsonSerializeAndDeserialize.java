package JSON;

import JSON.Pojo.BasicJsonfile.BasicJsonFileRoot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Scanner;

public class JsonSerializeAndDeserialize {
    public static void main(String[] args) throws IOException {
        var obj = (BasicJsonFileRoot)deserialize(
                "./src/main/resources/JSONFiles/BasicJsonfile.json",
                BasicJsonFileRoot.class);
    System.out.println(obj.getTotalEducation().getElementary());
    }

    public static String serialize(Object object){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return  gson.toJson(object);
    }

    public static Object deserialize(String filePath, Type typeOfT) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()){
            sb.append(sc.nextLine());
        }
        Gson gson = new Gson();
       return gson.fromJson(sb.toString() , typeOfT);
    }
}
