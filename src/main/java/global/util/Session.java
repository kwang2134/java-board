package global.util;

public class Session {
    private static Session instance;
    private Object authInfo;

    private Session() {

    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return authInfo != null;
    }

    public void setAuthInfo(Object authInfo) {
        this.authInfo = authInfo;
    }

    public Object getAuthInfo() {
        return authInfo;
    }
}
