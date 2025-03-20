package global.util;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private static Container instance;
    private Map<String, Object> objects;

    private Container() {
        objects = new HashMap<>();
    }

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }

        return instance;
    }

    public void register(String key, Object obj) {
        objects.put(key, obj);
    }

    public Object get(String key) {
        return objects.get(key);
    }
}
