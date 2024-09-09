package at.upstream_mobility.itacademy.bored.Exceptions;

public class ActivityConversionFailedException extends RuntimeException{

    public ActivityConversionFailedException() {
        super("Conversion from JSON to FetchedActivity failed");
    }
}
