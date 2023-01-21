package inMemoryDB;

import entities.account.Account;
import ports.database.EntityGateway;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

//TODO: Implement querying for paths of the form (username, courseCode, assessmentTitle, instanceTitle) and sub-paths
// of the same form.

public class InMemoryEntityGateway implements EntityGateway {
    private final String databaseFilePath;
    private HashMap<String, Account> accountsByUsername = new HashMap<>();

    public InMemoryEntityGateway() {
        this.databaseFilePath = null;
    }

    @SuppressWarnings("unchecked")
    public InMemoryEntityGateway(String databaseFilePath) {
        this.databaseFilePath = databaseFilePath;
        try {
            accountsByUsername = (HashMap<String, Account>) Serializer.deserialize(databaseFilePath);
        } catch (FileNotFoundException e) {
            // THE DATABASE FILE WAS NOT FOUND AT THE SPECIFIED PATH
            // TODO: as it stands, the program will run as though no database exists.
        } catch (IOException e) {
            // THE PROGRAM CANNOT READ FROM THE FILE (POSSIBLY PERMISSION LOCKED)
            // TODO: as it stands, the program will run as though no database exists.
        } catch (ClassNotFoundException e) {
            // This error just means the database is empty, which is fine in our case.
            accountsByUsername = new HashMap<>();
        }
    }

    @Override
    public boolean existsAccount(String username) {
        return accountsByUsername.containsKey(username);
    }

    @Override
    public Account loadAccount(String username) throws AccountNotFoundException {
        Account account = accountsByUsername.get(username);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    @Override
    public void saveAccount(Account account) {
        accountsByUsername.put(account.getUsername(), account);
        if (databaseFilePath == null) return;
        try {
            Serializer.serialize(accountsByUsername, databaseFilePath);
        } catch (FileNotFoundException e) {
            // THE DATABASE FILE WAS NOT FOUND AT THE SPECIFIED PATH
            // TODO: as it stands, the program will silently fail to save changes.
        } catch (java.io.IOException e) {
            // THE PROGRAM CANNOT WRITE TO THE FILE (POSSIBLY PERMISSION LOCKED)
            // TODO: as it stands, the program will silently fail to save changes.
        }
    }

    @Override
    public void deleteAccount(String username) {
        accountsByUsername.remove(username);
    }
}
