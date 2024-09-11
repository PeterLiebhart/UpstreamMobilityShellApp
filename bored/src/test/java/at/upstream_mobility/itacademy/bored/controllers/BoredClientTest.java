package at.upstream_mobility.itacademy.bored.controllers;

import at.upstream_mobility.itacademy.bored.data.FetchedActivity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class BoredClientTest {

    @InjectMocks
    BoredClient boredClient;

    @Mock
    RestTemplate restTemplate;

    @Value("${urls.baseUrl}")
    String baseUrl;

    @Test
    void fetchRandomActivity() {

        FetchedActivity randomActivity = new FetchedActivity("random", 0, "random", 0, 0, "random", "random", true, "random", "random");

        String specificUrl = "/random";
        Mockito.when(restTemplate.getForObject(baseUrl + specificUrl, FetchedActivity.class)).thenReturn(randomActivity);

        FetchedActivity actual = boredClient.fetchRandomActivity();

        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(baseUrl + specificUrl, FetchedActivity.class);
        Assertions.assertEquals(randomActivity, actual);

    }

    @Test
    void fetchAllActivitiesFromCategory() {

        FetchedActivity randomActivity = new FetchedActivity("random", 0, "random", 0, 0, "random", "random", true, "random", "random");
        FetchedActivity categoryActivity = new FetchedActivity("category", 0, "category", 0, 0, "category", "category", true, "category", "category");

        FetchedActivity[] activities = new FetchedActivity[]{randomActivity, categoryActivity};

        String specificUrl = "/filter?type=category";
        Mockito.when(restTemplate.getForObject(baseUrl + specificUrl, FetchedActivity[].class)).thenReturn(activities);

        FetchedActivity[] actual = boredClient.fetchAllActivitiesFromCategory("category");
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(baseUrl + specificUrl, FetchedActivity[].class);
        Assertions.assertEquals(actual, activities);

    }

    @Test
    void fetchAllActivitiesByCategoryAndParticipants() {

        FetchedActivity randomActivity = new FetchedActivity("random", 0, "random", 0, 0, "random", "random", true, "random", "random");
        FetchedActivity categoryAndParticipantsActivity = new FetchedActivity("categoryAndParticipants", 0, "categoryAndParticipants", 0, 0, "categoryAndParticipants", "categoryAndParticipants", true, "categoryAndParticipants", "categoryAndParticipants");

        FetchedActivity[] activities = new FetchedActivity[]{randomActivity, categoryAndParticipantsActivity};

        String specificUrl = "/filter?type=category&participants=1";
        Mockito.when(restTemplate.getForObject(baseUrl + specificUrl, FetchedActivity[].class)).thenReturn(activities);

        FetchedActivity[] actual = boredClient.fetchAllActivitiesByCategoryAndParticipants("category", 1);
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(baseUrl + specificUrl, FetchedActivity[].class);
        Assertions.assertEquals(actual, activities);

    }
}