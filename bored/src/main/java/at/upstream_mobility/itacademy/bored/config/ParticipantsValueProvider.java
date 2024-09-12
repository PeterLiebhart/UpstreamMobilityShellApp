package at.upstream_mobility.itacademy.bored.config;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class ParticipantsValueProvider implements ValueProvider {

    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {
        return Stream.of("1","2","3","4","5","6","8")
                .map(CompletionProposal::new)
                .toList();
    }
}
