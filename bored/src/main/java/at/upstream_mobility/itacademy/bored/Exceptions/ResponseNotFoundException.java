package at.upstream_mobility.itacademy.bored.Exceptions;

public class ResponseNotFoundException extends RuntimeException {

    public ResponseNotFoundException() {
        super("No activities found with the given filter");
    }
}
