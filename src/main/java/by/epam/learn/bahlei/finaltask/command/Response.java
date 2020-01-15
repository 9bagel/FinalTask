package by.epam.learn.bahlei.finaltask.command;

public class Response {
    private String path;
    private ResponseType type;

    private static final String REDIRECT = "redirect";
    private static final String FORWARD = "forward";

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
        FORWARD;
    }
}
