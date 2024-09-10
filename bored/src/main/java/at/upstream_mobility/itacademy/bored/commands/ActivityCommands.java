package at.upstream_mobility.itacademy.bored.commands;

import at.upstream_mobility.itacademy.bored.Exceptions.InvalidNumberOfParticipantsException;
import at.upstream_mobility.itacademy.bored.service.BoredService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;
import java.util.Set;

@ShellComponent
public class ActivityCommands {

    private final BoredService boredService;
    private final Set<Integer> validNumbersOfParticipants;

    public ActivityCommands(BoredService boredService, Set<Integer> validNumbersOfParticipants) {
        this.boredService = boredService;
        this.validNumbersOfParticipants = validNumbersOfParticipants;
    }

    @ShellMethod(key = "random", value = "Returns a random activity. Specify a category using options.")
    public String randomCommand(
            @ShellOption(value = {"education", "social", "recreational", "cooking", "charity", "relaxation", "busywork"}, defaultValue = ShellOption.NULL, help = "Specify the category of the activity") String category,
            @ShellOption(value = {"1", "2", "3", "4", "5", "6", "8"}, defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {

        if (category == null) {
            return boredService.getRandomActivity();
        }


        return getActivityFromCategory(category, Optional.ofNullable(numberOfParticipants));
    }

    private String getActivityFromCategory(String category, Optional<Integer> numberOfParticipants) {
        numberOfParticipants.ifPresent(this::validateNumberOfParticipants);
        return boredService.getRandomActivityFromCategory(category, numberOfParticipants);
    }

    private void validateNumberOfParticipants(Integer numberOfParticipants) {
        if (!validNumbersOfParticipants.contains(numberOfParticipants)) {
            throw new InvalidNumberOfParticipantsException(numberOfParticipants, validNumbersOfParticipants);
        }
    }

}
