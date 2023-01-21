package entities.account;

// TODO: create an abstract class to represent course collections that Archive and Semester can inherit from.

public abstract class Account {
    public abstract String getUsername();

    public abstract String getPassword();

    public abstract Archive getArchive();

    public abstract Semester getSemester();

    public abstract void setUsername(String username);

    public abstract void setPassword(String password);

    public abstract void setArchive(Archive archive);

    public abstract void setSemester(Semester semester);

    /**
     * Checks whether password is valid
     *
     * @param password Takes string representing password
     * @return a boolean if password is valid
     */
    public static boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    public static boolean isUsernameValid(String username) {
        return username.length() >= 2;
    }

    public interface AccountFactory {
        Account createAccount();
    }
}

