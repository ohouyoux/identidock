package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts the hello world application.
 *
 * @author olivier.houyoux@gmail.com
 */
@SpringBootApplication
public class Application {

    /**
     * Starting point of the application.
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
