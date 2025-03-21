package repository.accounts;

import domain.accounts.Account;
import repository.MemoryRepositoryImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountRepositoryImpl extends MemoryRepositoryImpl<Account, Long> implements AccountRepository {
    private Long accountIdSequence = 1L;
    private final Map<String, Account> loginAccountIndex = new HashMap<>();

    @Override
    public Account save(Account account) {
        if (account == null) {
            return null;
        }

        if (account.getAccountId() == null) {
            account.setAccountId(accountIdSequence++);
        }

        Account savedAccount = super.save(account);
        loginAccountIndex.put(account.getLoginAccount(), account);
        return savedAccount;
    }

    @Override
    public Optional<Account> findByLoginAccount(String loginAccount) {
        return Optional.ofNullable(loginAccountIndex.get(loginAccount));
    }
}
