package tests.reuse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JsonFileManager {
    public LinkedHashMap<String,Object> jsonData;
    public JsonFileManager (String path){
        try
        {
            jsonData = new Gson().fromJson(new FileReader(path),new TypeToken<LinkedHashMap<String, Object>>() {}.getType());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Object getValue(String key){
        return jsonData.get(key);
    }
    public ArrayList<String> getArrayList(String key){
        return (ArrayList<String>) jsonData.get(key);
    }
}
