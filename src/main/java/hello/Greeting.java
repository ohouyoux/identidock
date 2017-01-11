package hello;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A user's greeting.
 *
 * @author olivier.houyoux@gmail.com
 */
public class Greeting {

    private static final int MIN = 0;

    private static final int MAX = 10;

    private String name;

    /**
     * Returns the name of the user who is currently beeing greeted.
     *
     * @return the user's name
     */
    public String getName() {
      return name;
    }

    /**
     * Modifies the user's name.
     *
     * @param name the name of the user who is using the
     */
    public void setName(final String name) {
      this.name = name;
    }

    /**
     * Finds an icon for the current user.
     *
     * @return the base64 encoded PNG icon
     */
    public String getIcon() {
      IconLoaderFactory<String, RuntimeException> factory = new DefaultIconLoaderFactory();
      IconLoader<String, RuntimeException> loader = factory.newInstance();

      // There is no such thing as gluing an icon to a user, so we randomly select an icon each time
      long id = ThreadLocalRandom.current().nextLong(MIN, MAX + 1);

      return loader.load(id);
    }
}
