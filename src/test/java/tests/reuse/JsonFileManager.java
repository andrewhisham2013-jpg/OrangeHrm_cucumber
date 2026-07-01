package tests.reuse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.invoke.MethodHandles.lookup;

public class JsonFileManager {

    private LinkedHashMap<String, Object> data;
    private final Type type;
    private static final Logger log = LogManager.getLogger(lookup().lookupClass());

    public JsonFileManager(String jsonPath) {
        this.type = new TypeToken<LinkedHashMap<String, Object>>() {}.getType();

        if (jsonPath == null || jsonPath.isEmpty()) {
            log.warn("The json path is empty, please provide the path");
            return;
        }
        try (FileReader reader = new FileReader(jsonPath)) {
            this.data = new Gson().fromJson(reader, this.type);
        } catch (Exception e) {
            log.warn("Error loading json file from path: {}", jsonPath);
        }
    }

    public String getValueByKey(String key) {
        if (data != null && data.containsKey(key)) {
            Object value = data.get(key);
            return value != null ? value.toString() : null;
        }
        return null;
    }

    public String getNestedValue(String sectionKey, String childKey) {
        if (data != null && data.containsKey(sectionKey)) {
            Object value = data.get(sectionKey);
            try {
                Map<?, ?> rawMap = (Map<?, ?>) value;
                if (rawMap != null && rawMap.containsKey(childKey)) {
                    Object innerValue = rawMap.get(childKey);
                    return innerValue != null ? innerValue.toString() : null;
                }
            } catch (Exception e) {
                log.error("The section value for '{}' is not a nested map object", sectionKey);
            }
        }
        return null;
    }
}