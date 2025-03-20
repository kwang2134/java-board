package global.util.filter;


import global.util.request.Request;

public interface Filter {
    boolean doFilter(Request request, FilterChain chain);
}
