package at.upstream_mobility.itacademy.bored.service;

import at.upstream_mobility.itacademy.bored.data.FetchedActivity;
import at.upstream_mobility.itacademy.bored.web.clients.BoredClient;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class BoredService {

    private final BoredClient boredClient;
    private final Random random;

    public BoredService(BoredClient boredClient, Random random) {
        this.boredClient = boredClient;
        this.random = random;
    }

    public String getRandomActivity() {
        return boredClient.fetchRandomActivity().activity();
    }

    public String getRandomActivityFromCategory(String category) {

        FetchedActivity[] activities = boredClient.fetchAllActivitiesFromCategory(category);

        return activities[random.nextInt(activities.length)].activity();
    }
}
