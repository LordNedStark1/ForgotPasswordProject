package africa.semicolon.promeescuous.utils;

import africa.semicolon.promeescuous.exceptions.UserNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AppUtil {
    private static final String MAIL_TEMPLATE_LOCATION = "C:\\Users\\semicolon\\Documents\\spring_projects\\prom-scuous\\src\\main\\resources\\templates\\index.html";

    public static final String WELCOME_MAIL_SUBJECT = "Welcome to promiscuous inc.";

    public static final String EMPTY_STRING="";

    public static String getMailTemplate() {
        Path templateLocation = Paths.get(MAIL_TEMPLATE_LOCATION);
        try {
            List<String> fileContents = Files.readAllLines(templateLocation);
            String template = String.join(EMPTY_STRING, fileContents);
            return template;
        }catch (IOException exception){
            throw new UserNotFoundException(exception.getMessage());
        }
    }

}
