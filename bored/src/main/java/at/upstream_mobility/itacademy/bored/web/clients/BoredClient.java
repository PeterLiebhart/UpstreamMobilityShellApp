package at.upstream_mobility.itacademy.bored.web.clients;

import at.upstream_mobility.itacademy.bored.Exceptions.ActivityConversionFailedException;
import at.upstream_mobility.itacademy.bored.data.FetchedActivity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
        return Optional.ofNullable(
                restTemplate.getForObject(baseUrl + "/random", FetchedActivity.class))
                .orElseThrow(ActivityConversionFailedException::new);
    }

    public FetchedActivity[] fetchAllActivitiesFromCategory(String category) {
        return Optional.ofNullable(
                restTemplate.getForObject(baseUrl + "/filter?type=" + category, FetchedActivity[].class))
                .orElseThrow(ActivityConversionFailedException::new);
    }

}
