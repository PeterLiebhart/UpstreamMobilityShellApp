package at.upstream_mobility.itacademy.bored.Exceptions;

import java.util.Set;

public class InvalidCategoryException extends RuntimeException {

    public InvalidCategoryException(String category, Set<String> validCategories) {
        super(String.format(
                "Invalid category: '%s'. Allowed categories: %s",
                category,
                validCategories
        ));
    }
}
