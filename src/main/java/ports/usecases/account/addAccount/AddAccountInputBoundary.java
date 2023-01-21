package ports.usecases.account.addAccount;

public interface AddAccountInputBoundary {
    void execute(AddAccountRequest request) throws UsernameAlreadyInUseError, PasswordsDoNotMatchError;

    class UsernameAlreadyInUseError extends Error {
        public UsernameAlreadyInUseError(String username) {
            super("Username already in use: " + username);
        }
    }

    class PasswordsDoNotMatchError extends Error {
        public PasswordsDoNotMatchError() {
            super("Password don't match");
        }
    }

    class PasswordNotValidError extends Error {
        public PasswordNotValidError() {
            super("Password isn't valid");
        }
    }

    class UsernameNotValidError extends Error {
        public UsernameNotValidError() {
            super("Username isn't valid");
        }
    }
}
