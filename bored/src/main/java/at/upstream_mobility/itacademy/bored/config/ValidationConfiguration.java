package at.upstream_mobility.itacademy.bored.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ValidationConfiguration {

    @Bean
    public Set<Integer> validParticipants() {
        return Set.of(1,2,3,4,5,6,8);
    }

    @Bean
    public Set<String> validCategories() {
        return Set.of("education", "social", "recreational", "cooking", "charity", "relaxation", "busywork");
    }
}
