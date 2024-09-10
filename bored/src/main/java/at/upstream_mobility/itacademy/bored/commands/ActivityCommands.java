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

    @ShellMethod(key = "education", value = "Returns an education-related activity")
    public String educationCommand(
            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {
        return getActivityFromCategory("education", Optional.ofNullable(numberOfParticipants));
    }

    @ShellMethod(key = "social", value = "Returns a social activity")
    public String socialCommand(
            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {
        return getActivityFromCategory("social", Optional.ofNullable(numberOfParticipants));
    }

    @ShellMethod(key = "recreational", value = "Returns a recreational activity")
    public String recreationalCommand(
            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {
        return getActivityFromCategory("recreational", Optional.ofNullable(numberOfParticipants));
    }

    @ShellMethod(key = "cooking", value = "Returns a cooking activity")
    public String cookingCommand(
            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {
        return getActivityFromCategory("cooking", Optional.ofNullable(numberOfParticipants));
    }

    @ShellMethod(key = "charity", value = "Returns a charity activity")
    public String charityCommand(
            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {
        return getActivityFromCategory("charity", Optional.ofNullable(numberOfParticipants));
    }

    @ShellMethod(key = "relaxation", value = "Returns a relaxation activity")
    public String relaxationCommand(
            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {
        return getActivityFromCategory("relaxation", Optional.ofNullable(numberOfParticipants));
    }

    @ShellMethod(key = "busywork", value = "Returns a busywork activity")
    public String busyworkCommand(
            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants") Integer numberOfParticipants) {
        return getActivityFromCategory("busywork", Optional.ofNullable(numberOfParticipants));
    }

    private String getActivityFromCategory(String category, Optional<Integer> numberOfParticipants) {
        numberOfParticipants.ifPresent(this::validateNumberOfParticipants);
        return boredService.getRandomActivityFromCategory(category.toLowerCase(), numberOfParticipants);
    }

    private void validateNumberOfParticipants(Integer numberOfParticipants) {
        if (!validNumbersOfParticipants.contains(numberOfParticipants)) {
            throw new InvalidNumberOfParticipantsException(numberOfParticipants, validNumbersOfParticipants);
        }
    }
}
