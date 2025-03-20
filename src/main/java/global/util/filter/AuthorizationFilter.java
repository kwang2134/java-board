package global.util.filter;

import domain.accounts.Account;
import domain.accounts.Role;
import global.exception.UnauthorizedAccessException;
import global.util.request.Request;

public class AuthorizationFilter implements Filter {
    @Override
    public boolean doFilter(Request request, FilterChain chain) {
        if (requiresAuth(request.getUrl())) {
            if (!hasPermission(request)) {
                new UnauthorizedAccessException("접근 권한이 없습니다.");
                return false;
            }
        }
        return chain.doFilter(request);
    }

    private boolean requiresAuth(String path) {
        return path.equals("/boards/add") || path.equals("/boards/edit") || path.equals("/boards/remove");
    }

    private boolean hasPermission(Request request) {
        Object authInfo = request.getSession().getAuthInfo();
        if (authInfo instanceof Account) {
            return ((Account) authInfo).getRole() == Role.ADMIN;
        }
        return false;
    }
}
