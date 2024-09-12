package at.upstream_mobility.itacademy.bored.config;

import org.springframework.shell.ResultHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler implements ResultHandler<Exception> {

    @Override
    public void handleResult(Exception e) {

        // ANSI escape code for red text
        String redText = "\033[31m" + e.getMessage() + "\033[0m";
        System.out.println(redText);
    }
}