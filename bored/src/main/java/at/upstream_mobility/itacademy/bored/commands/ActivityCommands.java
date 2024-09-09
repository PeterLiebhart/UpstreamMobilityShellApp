package at.upstream_mobility.itacademy.bored.commands;

import at.upstream_mobility.itacademy.bored.service.BoredService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;
import java.util.Set;

@ShellComponent
public class ActivityCommands {

    private final BoredService boredService;
    private final Set<String> validCategories;
    private final Set<String> validNumbersOfParticipants;

    public ActivityCommands(BoredService boredService, Set<String> validCategories, Set<String> validNumbersOfParticipants) {
        this.boredService = boredService;
        this.validCategories = validCategories;
        this.validNumbersOfParticipants = validNumbersOfParticipants;
    }

    @ShellMethod(key = "random", value = "Returns a random activity")
    public String getActivity() {
        return boredService.getRandomActivity();
    }

    @ShellMethod(key = "category", value = "Returns an activity from a certain category")
    public String getActivityFromCategory(

            @ShellOption(help = "Desired category")
            String category,

            @ShellOption(defaultValue = ShellOption.NULL, help = "Number of participants")
            Optional<Integer> numberOfParticipants) {

        if (!validCategories.contains(category)) {
            throw new IllegalArgumentException("Invalid category: " + category + ". Pick one of: " + validCategories);
        }

        if (numberOfParticipants.isPresent()) {

            if (!validNumbersOfParticipants.contains((numberOfParticipants.map(String::valueOf).get()))) {
                throw new IllegalArgumentException("Invalid number of participants: " + numberOfParticipants + ". Pick one of: " + validNumbersOfParticipants);
            }

        }

        return boredService.getRandomActivityFromCategory(category.toLowerCase());
    }

}
