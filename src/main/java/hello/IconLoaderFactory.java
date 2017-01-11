package hello;

/**
 * Defines how to create a concrete {@code IconLoader} instance.
 *
 * @author olivier.houyoux@gmail.com
 * @param <T> the type of icon to load
 * @param <E> the type of {@code Exception} that the created loader may throw when loading an icon
 */
public interface IconLoaderFactory<T, E extends Exception> {

  /**
   * Creates a new concrete {@code IconLoader}.
   *
   * @return the newly instantiated {@code IconLoader}
   */
  IconLoader<T, E> newInstance();
}
