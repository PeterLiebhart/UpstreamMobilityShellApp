package at.upstream_mobility.itacademy.bored.service;

import at.upstream_mobility.itacademy.bored.Exceptions.ParticipantsWithoutCategoryException;
import at.upstream_mobility.itacademy.bored.data.FetchedActivity;
import at.upstream_mobility.itacademy.bored.Clients.BoredClient;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class BoredService {

    private final BoredClient boredClient;

    public BoredService(BoredClient boredClient) {
        this.boredClient = boredClient;
    }

    public String getRandomActivity(Optional<String> category, Optional<Integer> numberOfParticipants) {

        if (category.isEmpty() && numberOfParticipants.isEmpty()) {
            return boredClient.fetchRandomActivity().activity();
        }

        FetchedActivity[] activities;

        if (category.isPresent() && numberOfParticipants.isPresent()) {

            activities = boredClient.fetchAllActivitiesByCategoryAndParticipants(category.get(), numberOfParticipants.get());

        } else if (category.isPresent()) {

            activities = boredClient.fetchAllActivitiesFromCategory(category.get());

        } else {
            throw new ParticipantsWithoutCategoryException();
        }

        if (activities.length == 0) {
            throw new NoSuchElementException("No activities found for the provided criteria.");
        }

        int randomIndex = new Random().nextInt(activities.length);
        return activities[randomIndex].activity();
    }
}
