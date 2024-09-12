package at.upstream_mobility.itacademy.bored.commands;

import at.upstream_mobility.itacademy.bored.Exceptions.InvalidCategoryException;
import at.upstream_mobility.itacademy.bored.Exceptions.InvalidNumberOfParticipantsException;
import at.upstream_mobility.itacademy.bored.config.CategoryValuesProvider;
import at.upstream_mobility.itacademy.bored.config.ParticipantsValueProvider;
import at.upstream_mobility.itacademy.bored.service.BoredService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;
import java.util.Set;

@ShellComponent
public class ActivityCommands {

    private final BoredService boredService;

    private final Set<Integer> validParticipants;
    private final Set<String> validCategories;

    public ActivityCommands(BoredService boredService, Set<Integer> validParticipants, Set<String> validCategories) {
        this.boredService = boredService;
        this.validParticipants = validParticipants;
        this.validCategories = validCategories;
    }

    @ShellMethod(key = "random", value = "Returns a random activity. Specify a category using options.")
    public String randomCommand(
            @ShellOption(valueProvider = CategoryValuesProvider.class, defaultValue = ShellOption.NULL, help = "Specify the category of the activity") String category,
            @ShellOption(valueProvider = ParticipantsValueProvider.class, defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants)
    {

        return getActivity(Optional.ofNullable(category), Optional.ofNullable(numberOfParticipants));

    }

    private String getActivity(Optional<String> optionalCategory, Optional<Integer> optionalNumberOfParticipants) {

        optionalNumberOfParticipants.ifPresent(this::validateParticipants);
        optionalCategory.ifPresent(this::validateCategory);

        return boredService.getRandomActivity(optionalCategory, optionalNumberOfParticipants);
    }

    private void validateParticipants(Integer numberOfParticipants) {
        if (!validParticipants.contains(numberOfParticipants)) {
            throw new InvalidNumberOfParticipantsException(numberOfParticipants, validParticipants);
        }
    }

    private void validateCategory(String category) {
        if (!validCategories.contains(category)) {
            throw new InvalidCategoryException(category, validCategories);
        }
    }
}
