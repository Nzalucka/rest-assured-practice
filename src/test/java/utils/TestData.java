package utils;

import model.User;

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
    public static Map<String,String>loginBody(){
        Map<String,String>body=new HashMap<>();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "cityslicka");
        return body;
    }
    public static User createUserBodyPojo(){
        return new User("Natalia", "QA Engineer");
    }



}
