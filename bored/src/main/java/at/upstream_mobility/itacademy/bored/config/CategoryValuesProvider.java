package at.upstream_mobility.itacademy.bored.config;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CategoryValuesProvider implements ValueProvider {

    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {
        return Stream.of("education", "social", "recreational", "cooking", "charity", "relaxation", "busywork")
                .map(CompletionProposal::new)
                .toList();
    }
}
