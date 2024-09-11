package at.upstream_mobility.itacademy.bored.controllers;

import at.upstream_mobility.itacademy.bored.Exceptions.FetchedActivityIsNullException;
import at.upstream_mobility.itacademy.bored.Exceptions.ResponseNotFoundException;
import at.upstream_mobility.itacademy.bored.data.FetchedActivity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Controller
public class BoredClient {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    public BoredClient(RestTemplate restTemplate, @Value("${urls.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public FetchedActivity fetchRandomActivity() {

        return fetchSingularActivity(
                String.format(
                        "%s/random",
                        baseUrl));

    }

    public FetchedActivity[] fetchAllActivitiesFromCategory(String category) {

        return fetchActivityArray(
                String.format(
                        "%s/filter?type=%s",
                        baseUrl,
                        category)
        );

    }

    public FetchedActivity[] fetchAllActivitiesByCategoryAndParticipants(String category, int participants) {

        return fetchActivityArray(
                String.format(
                        "%s/filter?type=%s&participants=%s",
                        baseUrl,
                        category,
                        participants)
        );

    }

    private FetchedActivity fetchSingularActivity(String url) {

        try {

            return Optional.ofNullable(restTemplate.getForObject(url, FetchedActivity.class))
                    .orElseThrow(FetchedActivityIsNullException::new);

        } catch (HttpClientErrorException e) {

            throw new ResponseNotFoundException();

        }

    }

    private FetchedActivity[] fetchActivityArray(String url) {

        try {

            return Optional.ofNullable(restTemplate.getForObject(url, FetchedActivity[].class))
                    .orElseThrow(FetchedActivityIsNullException::new);

        } catch (HttpClientErrorException e) {

            throw new ResponseNotFoundException();

        }

    }
}
