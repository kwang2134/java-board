package util.filter;

import util.request.Request;

import java.util.List;

public class FilterChainImpl implements FilterChain {
    private List<Filter> filters;
    private int index;

    public FilterChainImpl(List<Filter> filters) {
        this.filters = filters;
        this.index = 0;
    }

    @Override
    public boolean doFilter(Request request) {
        if(index < filters.size()) {
            Filter filter = filters.get(index++);
            if(!filter.doFilter(request, this)) {
                return false;
            }
            return doFilter(request);
        }
        return true;
    }
}
