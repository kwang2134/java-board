package util.filter;

import util.request.Request;

public interface FilterChain {
    boolean doFilter(Request request);
}
