package at.upstream_mobility.itacademy.bored.Exceptions;

public class ParticipantsWithoutCategoryException extends RuntimeException {

    public ParticipantsWithoutCategoryException() {
        super("Do not enter number of participants without specifying the category first!");
    }
}
