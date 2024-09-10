package at.upstream_mobility.itacademy.bored.service;

import at.upstream_mobility.itacademy.bored.data.FetchedActivity;
import at.upstream_mobility.itacademy.bored.web.clients.BoredClient;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;

@Service
public class BoredService {

    private final BoredClient boredClient;

    public BoredService(BoredClient boredClient) {
        this.boredClient = boredClient;
    }

    public String getRandomActivity() {
        return boredClient.fetchRandomActivity().activity();
    }

    public String getRandomActivityFromCategory(String category, Optional<Integer> numberOfParticipants) {

        FetchedActivity[] activities = numberOfParticipants
                .map(participants -> boredClient.fetchAllActivitiesByCategoryAndParticipants(category, participants))
                .orElseGet(() -> boredClient.fetchAllActivitiesFromCategory(category));

        int randomIndex = new Random().nextInt(activities.length);

        return activities[randomIndex].activity();
    }
}
