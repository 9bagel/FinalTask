package by.epam.learn.bahlei.finaltask.command;

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
