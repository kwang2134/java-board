package domain.accounts;

import domain.BaseEntity;
import util.annotation.Id;

public class Account extends BaseEntity {
    @Id
    private Long accountId;
    private String loginAccount;
    private String password;
    private String name;
    private String email;
    private Role role;

    private Account(AccountBuilder builder) {
        this.loginAccount = builder.loginAccount;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
        this.role = Role.USER;
        setCreatedAt();
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setAdmin(){
        this.role = Role.ADMIN;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void modifyAccount(String password, String email) {
        this.password = password;
        this.email = email;
        setUpdatedAt();
    }

    public static class AccountBuilder {
        private String loginAccount;
        private String password;
        private String name;
        private String email;

        public AccountBuilder loginAccount(String loginAccount) {
            this.loginAccount = loginAccount;
            return this;
        }

        public AccountBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AccountBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Account build() {
            if (loginAccount == null || password == null || name == null) {
                System.out.println("아이디, 비밀번호, 이름은 필수입니다.");
                return null;
            }
            return new Account(this);
        }
    }
}
