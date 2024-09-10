package at.upstream_mobility.itacademy.bored.Exceptions;

public class InvalidNumberOfParticipantsException extends RuntimeException {

    public InvalidNumberOfParticipantsException(Integer numberOfParticipants, Iterable<Integer> validNumbersOfParticipants) {
        super(String.format(
                "Invalid number of participants: '%d'. Allowed numbers: %s",
                numberOfParticipants,
                validNumbersOfParticipants
        ));
    }
}