package ports.usecases;

public class PathNotFoundError extends Error {
    public PathNotFoundError(String path) {
        super("Path not found: " + path);
    }

    public PathNotFoundError() {
        super("Empty Error");
    }
}
