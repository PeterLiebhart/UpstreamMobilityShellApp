package at.upstream_mobility.itacademy.bored.service;

import at.upstream_mobility.itacademy.bored.data.FetchedActivity;
import at.upstream_mobility.itacademy.bored.web.clients.BoredClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BoredServiceTest {

    @InjectMocks
    private BoredService boredService;

    @Mock
    private BoredClient boredClient;

    @Test
    void getRandomActivity_EmptyInput() {

        FetchedActivity randomActivity = new FetchedActivity("random", 0, "random", 0, 0, "random", "random", true, "random", "random");

        Mockito.when(boredClient.fetchRandomActivity()).thenReturn(randomActivity);

        String actual = boredService.getRandomActivity(Optional.empty(), Optional.empty());
        String expected = randomActivity.activity();

        Mockito.verify(boredClient, Mockito.times(1)).fetchRandomActivity();
        assertEquals(actual, expected);
    }

    @Test
    void getRandomActivity_WithCategory() {
        FetchedActivity categoryActivity = new FetchedActivity("category", 0, "category", 0, 0, "category", "category", true, "category", "category");
        FetchedActivity randomActivity = new FetchedActivity("random", 0, "random", 0, 0, "random", "random", true, "random", "random");

        FetchedActivity[] activities = new FetchedActivity[]{categoryActivity, randomActivity};
        Mockito.when(boredClient.fetchAllActivitiesFromCategory("category")).thenReturn(activities);

        String actual = boredService.getRandomActivity(Optional.of("category"), Optional.empty());
        String expected = randomActivity.activity();
        String expected2 = categoryActivity.activity();

        Mockito.verify(boredClient, Mockito.times(1)).fetchAllActivitiesFromCategory("category");
        assertTrue(actual.equals(expected) || actual.equals(expected2));;

    }

    @Test
    void getRandomActivity_WithCategoryAndParticipants() {
        FetchedActivity categoryAndParticipantsActivity = new FetchedActivity("categoryAndParticipants", 0, "categoryAndParticipants", 0, 0, "categoryAndParticipants", "categoryAndParticipants", true, "categoryAndParticipants", "categoryAndParticipants");
        FetchedActivity randomActivity = new FetchedActivity("random", 0, "random", 0, 0, "random", "random", true, "random", "random");

        FetchedActivity[] activities = new FetchedActivity[]{categoryAndParticipantsActivity, randomActivity};

        Mockito.when(boredClient.fetchAllActivitiesByCategoryAndParticipants("category", 1)).thenReturn(activities);

        String actual = boredService.getRandomActivity(Optional.of("category"), Optional.of(1));
        String expected = randomActivity.activity();
        String expected2 = categoryAndParticipantsActivity.activity();

        Mockito.verify(boredClient, Mockito.times(1)).fetchAllActivitiesByCategoryAndParticipants("category", 1);
        assertTrue(actual.equals(expected) || actual.equals(expected2));;

    }
}