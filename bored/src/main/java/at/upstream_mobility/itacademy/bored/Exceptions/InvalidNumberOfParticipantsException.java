package at.upstream_mobility.itacademy.bored.Exceptions;

import java.util.Set;

public class InvalidNumberOfParticipantsException extends RuntimeException {

    public InvalidNumberOfParticipantsException(Integer numberOfParticipants, Set<Integer> validNumbersOfParticipants) {
        super(String.format(
                "Invalid number of participants: '%d'. Allowed numbers: %s",
                numberOfParticipants,
                validNumbersOfParticipants
        ));
    }
}