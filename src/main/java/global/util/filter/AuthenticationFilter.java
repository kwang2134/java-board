package global.util.filter;

import global.exception.UnauthorizedException;
import global.util.request.Request;

public class AuthenticationFilter implements Filter {
    @Override
    public boolean doFilter(Request request, FilterChain chain) {
        if (requiresAuth(request.getUrl())) {
            if (!request.getSession().isLoggedIn()) {
                new UnauthorizedException("로그인이 필요합니다.");
                return false;
            }
        }
        return chain.doFilter(request);
    }

    private boolean requiresAuth(String path) {
        return path.equals("/boards/add") || path.equals("/boards/edit") || path.equals("/boards/remove")
                || path.equals("/posts/add") || path.equals("/posts/edit") || path.equals("/posts/remove")
                || path.equals("/accounts/signout") || path.equals("/accounts/detail") || path.equals("/accounts/edit") || path.equals("/accounts/remove");
    }
}
