package at.upstream_mobility.itacademy.bored.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CustomExceptionHandlerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testHandleResult() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

        String testMessage = "Test Exception Message";
        Exception exception = new Exception(testMessage);

        customExceptionHandler.handleResult(exception);

        String output = outputStream.toString();

        String expectedOutput = "\033[31m" + testMessage + "\033[0m\n";

        Assertions.assertEquals(expectedOutput, output,
                "Output should match the expected ANSI escape code formatted message.");
    }
}