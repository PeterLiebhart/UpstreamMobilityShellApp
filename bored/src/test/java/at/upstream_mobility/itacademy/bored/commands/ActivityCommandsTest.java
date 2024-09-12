package at.upstream_mobility.itacademy.bored.commands;

import at.upstream_mobility.itacademy.bored.service.BoredService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@ShellTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ActivityCommandsTest {

    @Autowired
    private ShellTestClient client;

    @MockBean
    private BoredService boredService;

    private ShellTestClient.InteractiveShellSession shellSession;

    // This starts a new interactive shell for each test
    @BeforeEach
    void setup() {
        shellSession = client
                .interactive()
                .run();
    }

    // Test if the shell is even running properly (Displaying "shell" in the console)
    @Test
    void testShell() {

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(shellSession.screen())
                    .containsText("shell");
        });
    }

    // Test if the "help" command displays "AVAILABLE COMMANDS" properly. This could be a lot more detailed of course.
    @Test
    void testHelpCommand() {

        shellSession.write(shellSession.writeSequence().text("help").carriageReturn().build());
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(shellSession.screen())
                    .containsText("AVAILABLE COMMANDS");
        });

    }

    // Test if the "random" command is properly returning the result of boredService.getRandomActivity(). Also tests if Optional values are handled correctly.
    @Test
    void random() {

        Mockito.when(boredService.getRandomActivity(Optional.empty(), Optional.empty())).thenReturn("random activity");

        shellSession.write(shellSession.writeSequence().text("random").carriageReturn().build());
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(shellSession.screen())
                    .containsText("random activity");
        });
    }

    @Test
    void random_withCategory() {

        Mockito.when(boredService.getRandomActivity(Optional.of("example"), Optional.empty())).thenReturn("only category");

        shellSession.write(shellSession.writeSequence().text("random example").carriageReturn().build());
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(shellSession.screen())
                    .containsText("only category");
        });
    }

    @Test
    void random_withCategoryAndParticipant() {

        Mockito.when(boredService.getRandomActivity(Optional.of("example"), Optional.of(1))).thenReturn("category and participants");


        shellSession.write(shellSession.writeSequence().text("random example 1").carriageReturn().build());
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(shellSession.screen())
                    .containsText("category and participants");
        });
    }
}