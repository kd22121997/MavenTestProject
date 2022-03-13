package JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RetriveAllJSONFromLog {

    public static void main(String[] args) throws IOException {

//        Pattern p = Pattern.compile("[{\\[]{1}([,:{}\\[\\]0-9.\\-+Eaeflnr-u \\n\\r\\t]|\".*?\")+[}\\]]{1}");
//        Matcher m = p.matcher(readUsingBufferedReader("C:/Users/lenovo/Desktop/log.log"));
//        while(m.find()) {
//            System.out.println(m.group());
//        }

        String fileData = readUsingBufferedReader("C:/Users/lenovo/Desktop/log.log");
        String splitter = "\"entity_list\":";
        var jsons = Arrays.stream(fileData.split(splitter)).toList();
        String lastjson = "{\n" + splitter + jsons.get(jsons.size()-1);
        lastjson = lastjson.split("\\[2022-")[0].trim();
        System.out.println(lastjson);



    }

    private static String
    readUsingBufferedReader(String fileName)
            throws IOException
    {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        // read file line by line
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb=new StringBuilder();

        String line;
        System.out.println(
                "Read text file using BufferedReader");
        while ((line = br.readLine()) != null) {
            // process the line
            sb.append(line);
            sb.append("\n");
        }
        // close resources
        br.close();

        fr.close();
        return sb.toString();
    }

}
