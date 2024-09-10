package at.upstream_mobility.itacademy.bored.Exceptions;

public class FetchedActivityIsNullException extends RuntimeException{

    public FetchedActivityIsNullException() {
        super("Activity fetched from BoredAPI is null. Please contact our development team for help.");
    }
}
