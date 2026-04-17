package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonReader {
    private static final String FILE_PATH = "src/test/resources/testdata.json";

    public static String getData(String key) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new File(FILE_PATH));
        return node.get(key).asText();
    }
    public static Map<String, String> getCreateUserBody() throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("name", getData("name"));  // bez JsonReader. bo jesteś w tej klasie
        body.put("job", getData("job"));
        return body;
    }
    public static Object[][] getUsersData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode array = mapper.readTree(new File(FILE_PATH));

        Object[][] data = new Object[array.size()][2];
        for (int i = 0; i < array.size(); i++) {
            data[i][0] = array.get(i).get("name").asText();
            data[i][1] = array.get(i).get("job").asText();
        }
        return data;
    }
}
