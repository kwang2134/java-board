package controller.accounts;

import domain.accounts.Account;
import service.accounts.AccountService;
import util.Session;
import util.request.Request;

import java.util.Scanner;

public class AccountController {
    private final AccountService accountService;
    private Scanner scanner;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        this.scanner = new Scanner(System.in);
    }

    public void signUp(Request request) {
        Session session = request.getSession();

        if (session.isLoggedIn()) {
            System.out.println("이미 로그인 되어 있습니다.");
            return;
        }

        Account account = new Account.AccountBuilder()
                .loginAccount(request.getParam("loginAccount"))
                .password(request.getParam("password"))
                .name(request.getParam("name"))
                .email(request.getParam("email"))
                .build();

        Account signedUpAccount = accountService.signUp(account);
        System.out.println("가입에 성공하였습니다. 아이디: " + signedUpAccount.getLoginAccount());
    }

    public void signIn(Request request) {
        Session session = request.getSession();

        if(session.isLoggedIn()) {
            System.out.println("이미 로그인 되어 있습니다.");
            return;
        }

        Account account = accountService.signIn(request.getParam("loginAccount"), request.getParam("password"));

        if (account != null) {
            session.setAuthInfo(account);
        }

        System.out.println("로그인 되었습니다.");
    }

    public void signOut(Request request) {
        Session session = request.getSession();

        if(!session.isLoggedIn()) {
            System.out.println("로그인 되어 있지 않습니다.");
            return;
        }

        session.setAuthInfo(null);

        System.out.println("로그아웃 되었습니다.");
    }

    public void getDetail(Request request, long accountId) {
        Session session = request.getSession();
        if(session.isLoggedIn()) {
            Account authInfo = (Account) session.getAuthInfo();
            if (authInfo.getAccountId() == accountId) {
                System.out.println("["+ accountId + "]번 회원" );
                System.out.println("계정: " + authInfo.getLoginAccount());
                System.out.println("이메일: " + authInfo.getEmail());
                System.out.println("가입일: " + authInfo.getCreatedAt());
            }
        }
    }

    public void editAccount(Request request, long accountId) {
        Account account = accountService.editAccount(accountId, request.getParam("password"), request.getParam("email"));
        if(account != null) {
            System.out.println("회원 정보가 수정되었습니다.");
        }
    }

    public void removeAccount(Request request, long accountId) {
        Session session = request.getSession();
        if (session.isLoggedIn()) {
            session.setAuthInfo(null);
        }

        accountService.removeAccount(accountId);
        System.out.println("계정이 삭제되었습니다.");
    }
}
