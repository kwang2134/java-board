package exception.accounts;

import domain.accounts.Account;

public class InvalidPasswordException {
    public InvalidPasswordException(String message) {
        System.out.println(message);
    }
}
