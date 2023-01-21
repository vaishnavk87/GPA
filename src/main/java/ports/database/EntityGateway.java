package ports.database;

import entities.account.Account;

public interface EntityGateway {
    /**
     * Check whether an account exists by its username.
     *
     * @param username the username to query for.
     * @return true if the account exists, false otherwise.
     */
    boolean existsAccount(String username);

    /**
     * Load an account from the database.
     *
     * @param username the username of the account.
     * @return The account associated with username
     * @throws AccountNotFoundException if the account does not exist in the database.
     */
    Account loadAccount(String username) throws AccountNotFoundException;

    /**
     * Save an account to the database. If a user with the same username already exists, overwrite it.
     * Precondition: account.getUsername() is not null.
     *
     * @param account the account to save.
     */
    void saveAccount(Account account);

    /**
     * Remove an account and all of its data from the database. Does nothing if the account does not exist in the
     * database.
     *
     * @param username the username of the account.
     */
    void deleteAccount(String username);

    class AccountNotFoundException extends RuntimeException {
    }
}
