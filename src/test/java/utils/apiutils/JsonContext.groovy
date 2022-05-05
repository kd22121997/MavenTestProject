package utils.apiutils

import com.jayway.jsonpath.JsonPath
import utils.ApplicationUnderTest

class JsonContext {

    ApplicationUnderTest aut
    def context

    JsonContext(ApplicationUnderTest aut, File file){
        this.aut = aut
        context = JsonPath.parse(file)
        aut.logger.logInfo("Parsing JSON file '${file.getPath()}'")
    }

    void set(String path, Object value){
        aut.logger.logInfo("Setting value '$value' in path '$path' of the JSON ")
        context.set(path,value)
    }

    void add(String path, Object value){
        aut.logger.logInfo("Adding a new data '$value' in path '$path' of the JSON ")
        context.add(path, value)
    }

    String read(String path){
        def data = context.read(path)
        aut.logger.logInfo("Data Retreived from path '$path of the the JSON : '$data'")
        return data
    }

    void delete(String path){
        context.delete(path)
    }

    def json(){
        return context.json()
    }
}
