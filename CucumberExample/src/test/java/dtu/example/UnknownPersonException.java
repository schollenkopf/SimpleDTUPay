package dtu.example;

public class UnknownPersonException extends Exception {

    private static final long serialVersionUID = 2L;

    public UnknownPersonException(String message) {
        super(message);
    }
}
