package global.util.filter;

import domain.accounts.Account;
import domain.accounts.Role;
import global.exception.UnauthorizedAccessException;
import global.util.request.Request;

public class AuthorizationFilter implements Filter {
    @Override
    public boolean doFilter(Request request, FilterChain chain) {
        System.out.println("권한 필터 실행 url: " + request.getUrl());
        if (requiresAuth(request.getUrl())) {
            if (!hasPermission(request)) {
                new UnauthorizedAccessException("접근 권한이 없습니다.");
                return false;
            }
        }
        return chain.doFilter(request);
    }

    private boolean requiresAuth(String path) {
        return path.startsWith("/boards/add") || path.equals("/boards/edit") || path.equals("/boards/remove");
    }

    private boolean hasPermission(Request request) {
        Object authInfo = request.getSession().getAuthInfo();
        System.out.println("접근 권한 확인");
        if (authInfo instanceof Account) {
            System.out.println("사용자 권한: " + ((Account) authInfo).getRole());
            return ((Account) authInfo).getRole() == Role.ADMIN;
        }
        return false;
    }
}
