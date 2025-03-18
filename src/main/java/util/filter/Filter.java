package util.filter;


import util.request.Request;

public interface Filter {
    boolean doFilter(Request request, FilterChain chain);
}
