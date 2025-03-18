package util.request;

import util.Session;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String url;
    private Session session;
    private Map<String, String> params;

    public Request(String url) {
        this.url = url;
        this.session = Session.getInstance();
        this.params = getParams(url);
    }

    public String getUrl() {
        return url;
    }

    public Session getSession() {
        return session;
    }

    private Map<String, String> getParams(String url) {
        Map<String, String> params = new HashMap<>();
        String[] parts = url.split("\\?");

        if (parts.length > 1) {
            String query = parts[1];
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }

        return params;
    }

    public String getParam(String name) {
        return params.get(name);
    }
}
