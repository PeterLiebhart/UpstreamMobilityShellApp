package at.upstream_mobility.itacademy.bored.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ValidationConfiguration {

    @Bean
    public Set<Integer> validNumbersOfParticipants() {
        return new HashSet<>(Set.of(1,2,3,4,5,6,8));
    }
}
