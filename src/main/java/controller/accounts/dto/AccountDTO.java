package controller.accounts.dto;

public class AccountDTO {
    private String loginAccount;
    private String password;
    private String name;
    private String email;

    public AccountDTO(String loginAccount, String password, String name, String email) {
        this.loginAccount = loginAccount;
        this.password = password;
        this.name = name;
        this.email = email;
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

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
