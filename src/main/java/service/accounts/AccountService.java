package service.accounts;

import domain.accounts.Account;
import global.exception.accounts.AccountNotFoundException;
import global.exception.accounts.InvalidPasswordException;
import repository.accounts.AccountRepository;

public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account signUp(Account account) {
        return repository.save(account);
    }

    public Account signIn(String loginAccount, String password) {
        Account account = repository.findByLoginAccount(loginAccount).orElseGet(() -> {
            new AccountNotFoundException("계정이 존재하지 않습니다.");
            return null;
        });

        if(account == null) {
            new AccountNotFoundException("계정이 존재하지 않습니다.");
            return null;
        }

        if(!account.getPassword().equals(password)) {
            new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return account;
    }

    public void removeAccount(Long accountId) {
        repository.deleteById(accountId);
    }

    public Account getDetail(Long accountId) {
        return repository.findById(accountId).orElseGet(() -> {
            new AccountNotFoundException("계정이 존재하지 않습니다.");
            return null;
        });
    }

    public Account editAccount(Long accountId, String password, String email) {
        Account account = repository.findById(accountId).orElseGet(() -> {
            new AccountNotFoundException("계정이 존재하지 않습니다.");
            return null;
        });

        account.modifyAccount(password, email);
        return account;
    }


}
