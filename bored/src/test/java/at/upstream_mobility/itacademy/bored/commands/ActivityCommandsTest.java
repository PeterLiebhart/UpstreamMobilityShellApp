package at.upstream_mobility.itacademy.bored.commands;

import at.upstream_mobility.itacademy.bored.service.BoredService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@ShellTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ActivityCommandsTest {

    @Autowired
    private ShellTestClient client;

    @MockBean
    private BoredService boredService;

    @Test
    void testShell() {
        ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("shell");
        });
    }

    @Test
    void testRandomCommand() {

    }
}