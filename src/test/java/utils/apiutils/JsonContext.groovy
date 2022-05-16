package utils.apiutils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import org.apache.poi.ss.formula.functions.T
import utils.generics.ApplicationUnderTest

import java.lang.reflect.Type

class JsonContext {

    ApplicationUnderTest aut
    DocumentContext context

    JsonContext(ApplicationUnderTest aut, File file) {
        this.aut = aut
        context = JsonPath.parse(file)
        aut.logger.logInfo("Parsing JSON file '${file.getPath()}'")
    }

    void set(String path, Object value) {
        aut.logger.logInfo("Setting value '$value' in path '$path' of the JSON ")
        context.set(path, value)
    }

    void add(String path, Object value) {
        aut.logger.logInfo("Adding a new data '$value' in path '$path' of the JSON ")
        context.add(path, value)
    }

    String read(String path) {
        def data = context.read(path)
        aut.logger.logInfo("Data Retreived from path '$path of the the JSON : '$data'")
        return data
    }

    void delete(String path) {
        context.delete(path)
    }

    def json() {
        return context.json()
    }

    String toJsonString(Boolean prettyPrint = false) throws JsonProcessingException {
        if (prettyPrint)
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(json())
        else
            return new ObjectMapper().writeValueAsString(json())
    }

    def deSerialize(Type t = Object)
    {
        new Gson().fromJson(toJsonString(), t)
    }
}
