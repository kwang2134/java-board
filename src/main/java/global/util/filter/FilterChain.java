package global.util.filter;

import global.util.request.Request;

public interface FilterChain {
    boolean doFilter(Request request);
    void reset();
}
