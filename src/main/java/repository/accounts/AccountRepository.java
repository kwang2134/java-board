package repository.accounts;

import domain.accounts.Account;
import repository.MemoryRepository;

import java.util.Optional;

public interface AccountRepository extends MemoryRepository<Account, Long> {
    public Optional<Account> findByLoginAccount(String loginAccount);
}
