package by.epam.learn.bahlei.finaltask.command;

/**
 * Represents forward or redirect for desired JSP path
 */
public class Response {
    private final String path;
    private final ResponseType type;

    public Response(String path, ResponseType type) {
        this.path = path;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public ResponseType getType() {
        return type;
    }

    public enum ResponseType {
        REDIRECT,
        FORWARD
    }
}
