package utils;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    public static Map<String,String>createUserBody(){
        Map<String,String>body=new HashMap<>();
        body.put("name","Natalia");
        body.put("job","QA Engineer");
        return body;
    }
    public static Map<String,String>updateUserBody(String name, String job){
        Map<String, String>body=new HashMap<>();
        body.put("name", name);
        body.put("job", job);
        return body;
    }

}
